package br.com.chess.services;

import br.com.chess.domain.Cep;
import br.com.chess.domain.Municipio;
import br.com.chess.domain.Uf;
import br.com.chess.dto.EnderecoCepDTO;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.exceptions.ServiceError;
import br.com.chess.repositories.CepRepository;
import br.com.chess.repositories.MunicipioRepository;
import br.com.chess.repositories.UfRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocalidadeService {

    private final EntityManager entityManager;

    private final UfRepository ufRepository;

    private final CepRepository cepRepository;

    private final MunicipioRepository municipioRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public LocalidadeService(EntityManager entityManager, UfRepository ufRepository, CepRepository cepRepository, MunicipioRepository municipioRepository) {
        this.entityManager = entityManager;
        this.ufRepository = ufRepository;
        this.cepRepository = cepRepository;
        this.municipioRepository = municipioRepository;
    }

    public List<Municipio> find(String estado, String municipio, String codigoMunicipio, Long pageSize, Long page) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Municipio> query = builder.createQuery(Municipio.class);

        Root<Municipio> root = query.from(Municipio.class);
        query.select(root);

        List<Predicate> predicados = new ArrayList<>();
        if (estado != null && !estado.trim().equals("")) {
            Predicate pEstado = builder.or(
                    builder.like(builder.lower(root.get("uf").get("nomeExtenso")), "%" + estado.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("uf").get("uf")), "%" + estado.toLowerCase() + "%")
            );
            predicados.add(pEstado);
        }

        if (municipio != null && !municipio.trim().equals("")) {
            Predicate pMunicipio = builder.like(builder.lower(root.get("nome")), "%" + municipio.toLowerCase() + "%");
            predicados.add(pMunicipio);
        }

        if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
            Predicate pCodigo = builder.like(builder.lower(root.get("codigo")), "%" + codigoMunicipio.toLowerCase() + "%");
            query = query.where(pCodigo);
            predicados.add(pCodigo);
        }

        if (!predicados.isEmpty()) {
            query.where(predicados.toArray(new Predicate[]{}));
        }

        query.orderBy(builder.asc(root.get("nome")));
        TypedQuery<Municipio> busca = entityManager.createQuery(query);
        busca.setMaxResults(pageSize != null ? pageSize.intValue() : 50);
        int pagina = page != null ? page.intValue() : 0;
        busca.setFirstResult(pagina > 0 ? (int) (page * busca.getMaxResults()) : 0);

        return busca.getResultList();
    }

    public List<Uf> findUfs() {
        return this.ufRepository.findAllByOrderBySiglaAsc();
    }

    public Cep findByCep(String cep) throws NotFoundError {

        if (cep != null) {
            cep = cep.replace("\\-", "");
            Cep doBanco = this.cepRepository.findByCodigo(cep);
            if (doBanco == null) {
                doBanco = buscaCepServicoExterno(cep);
            }
            return doBanco;
        }
        return null;
    }

    private Cep buscaCepServicoExterno(String cep) throws NotFoundError {
        String address = String.format("viacep.com.br/ws/%s/json/", cep);
        URL url;
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            connection.getResponseCode();
            EnderecoCepDTO enderecoCepDTO = mapper.readValue(connection.getInputStream(),
                    new TypeReference<>() {
                    });
            return this.salvarEnderecoBanco(enderecoCepDTO);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ServiceError("Cep com parametros invalidos");
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundError("CEP - " + cep + " não encontrado");
        } catch (IOException e) {
            throw new ServiceError("Erro pesquisando o cep no gateway");
        }
    }

    private Cep salvarEnderecoBanco(EnderecoCepDTO dto) {
        Municipio cidade = this.municipioRepository.findByNome(dto.getLocalidade());
        Uf uf = this.ufRepository.findBySigla(dto.getSiglaUf());
        Cep doBanco = new Cep();
        doBanco.setBairro(dto.getBairro());
        doBanco.setCodigo(dto.getCep());
        doBanco.setLogradouro(dto.getLogradouro());
        doBanco.setMunicipio(cidade);
        doBanco.setNomeCidade(dto.getLocalidade());
        doBanco.setOrigem("viacep.com.br");
        doBanco.setSiglaUf(dto.getSiglaUf());
        doBanco.setUf(uf);
        doBanco.setIbge(dto.getIbge());
        doBanco.setGia(dto.getGia());
        doBanco.setDdd(dto.getDdd());
        doBanco.setSiafi(dto.getSiafi());
        return this.cepRepository.save(doBanco);
    }
}
