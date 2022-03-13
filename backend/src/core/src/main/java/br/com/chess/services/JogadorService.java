package br.com.chess.services;

import br.com.chess.UtilConstantes;
import br.com.chess.UtilData;
import br.com.chess.domain.estaticas.EstatisticaJogador;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.ListaJogadoresTitulados;
import br.com.chess.domain.enums.TipoMaestria;
import br.com.chess.domain.enums.TipoPlataforma;
import br.com.chess.dto.BuscaJogadorDto;
import br.com.chess.dto.JogadorDto;
import br.com.chess.dto.PlayersTituladosDto;
import br.com.chess.dto.estatisticas.EstatisticasDto;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.exceptions.ServiceError;
import br.com.chess.repositories.EstatisticaJogadorRepository;
import br.com.chess.repositories.JogadorRepository;
import br.com.chess.repositories.ListaJogadoresTituladosRepository;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.*;

import static br.com.chess.UtilConstantes.DATA_INVALIDA;
import static br.com.chess.UtilConstantes.TIME_ZONE;
import static br.com.chess.UtilMetodo.*;

@Component
public class JogadorService {

    private final ProducerTemplate producerTemplate;
    private final JogadorRepository jogadorRepository;
    private final EntityManager entityManager;
    private final ListaJogadoresTituladosRepository listaJogadoresTituladosRepository;
    private final EstatisticaJogadorRepository estatisticaJogadorRepository;

    @Value("${atualizar.jogador}")
    private int prazoAtualizar;

    private static final Logger logger = LoggerFactory.getLogger("JogadorService");

    @Autowired
    public JogadorService(ProducerTemplate producerTemplate, JogadorRepository jogadorRepository, EntityManager entityManager, ListaJogadoresTituladosRepository lIstaJogadoresTituladosRepository, EstatisticaJogadorRepository estatisticaJogadorRepository) {
        this.producerTemplate = producerTemplate;
        this.jogadorRepository = jogadorRepository;
        this.entityManager = entityManager;
        this.listaJogadoresTituladosRepository = lIstaJogadoresTituladosRepository;
        this.estatisticaJogadorRepository = estatisticaJogadorRepository;
    }


    public Jogador buscarJogador(String user, String type) throws NotFoundError, IntegrationError {
        TipoPlataforma tipoPlataforma = confirmarTipoPlataforma(type);
        user = user.toLowerCase();
        Jogador jogador = this.jogadorRepository.findByUsernameAndTipo(user, tipoPlataforma);

        if(jogador != null && this.confirmarTempoAtualizacao(jogador.getDataUltimaAtualizacao())){
            return jogador;
        }
        JogadorDto jogadorDto = getJogadorDto(user, tipoPlataforma);

        if((jogador == null) && (jogadorDto == null)){
            throw new NotFoundError(UtilConstantes.JOGADOR_DESCONHECIDO);
        }
        EstatisticasDto estatisticas = this.buscarEstatisticasJogador(user, tipoPlataforma);

        if(jogador == null){
            return this.criarJogador(jogadorDto, tipoPlataforma, estatisticas);
        }
        return this.updateJogador(jogadorDto, jogador, estatisticas);
    }

    private EstatisticasDto buscarEstatisticasJogador(String user, TipoPlataforma type) throws IntegrationError {
        EstatisticasDto estatisticasDto = null;
        try{
            estatisticasDto = (EstatisticasDto)producerTemplate.requestBodyAndHeader("direct://buscar-jogador-estatisticas",user,"plataforma", type );
        }catch(Exception e){
            throw new IntegrationError(UtilConstantes.CHESS_COM, e.getMessage());
        }
        return estatisticasDto;
    }

    private JogadorDto getJogadorDto(String user, TipoPlataforma tipoPlataforma) throws IntegrationError {
        JogadorDto jogadorDto = null;
        try{
            jogadorDto = (JogadorDto) producerTemplate.requestBodyAndHeader("direct://buscar-jogador",user,"plataforma", tipoPlataforma );
        }catch(Exception e){
            throw new IntegrationError(UtilConstantes.CHESS_COM, e.getMessage());
        }
        return jogadorDto;
    }


    private Jogador updateJogador(JogadorDto jogadorDto, Jogador jogador, EstatisticasDto estatisticas) {
        if(jogadorDto.getAvatar() != null) jogador.setAvatarUrl(jogadorDto.getAvatar());
        jogador.setJogadorId(jogadorDto.getJogadorId());
        if(jogadorDto.getUrlId() != null) jogador.setAvatarUrl(jogador.getUrlId());
        if(jogadorDto.getUrl() != null) jogador.setAvatarUrl(jogador.getUrl());
        if(jogadorDto.getName() != null)jogador.setNome(jogadorDto.getName());
        jogador.setSeguidores(jogadorDto.getFollowers());
        if(jogadorDto.getLocation() != null)jogador.setLocalidade(jogadorDto.getLocation());
        if(jogadorDto.getCountry() != null)jogador.setPais(jogadorDto.getCountry());
        jogador.setUltimaVezOnline(UtilData.getDateFromTimestamp(Long.parseLong(jogadorDto.getUltimaVezOnline())));
        jogador.setStatusConta(retornarStatusContaPlataforma(jogadorDto.getStatus()));
        if(jogadorDto.getTitle() != null) jogador.setTitulo(retornarTipoMaestria(jogadorDto.getTitle()));
        jogador.setFide(jogadorDto.getFide());
        jogador.setStreamer(jogadorDto.isStreamer());
        jogador.setUltimaVezOnline(new Date());
        if(jogadorDto.getTwitchUrl() != null)jogador.setTwitchUrl(jogador.getTwitchUrl());
        jogador.setEstatisticaJogador(estatisticaJogadorRepository.save(new EstatisticaJogador(jogador,estatisticas)));
        return this.jogadorRepository.save(jogador);
    }

    private Jogador criarJogador(JogadorDto jogadorDto, TipoPlataforma tipoPlataforma, EstatisticasDto estatisticas) {
        Jogador jogador = jogadorRepository.save(new Jogador(jogadorDto, tipoPlataforma));
        EstatisticaJogador estatisticaJogador = estatisticaJogadorRepository.save(new EstatisticaJogador(jogador, estatisticas));
        jogador.setEstatisticaJogador(estatisticaJogador);
        return jogadorRepository.save(jogador);
    }

    private boolean confirmarTempoAtualizacao(Date dataUltimaAtualizacao) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, prazoAtualizar);
        return dataUltimaAtualizacao.before(calendar.getTime());
    }



    public List<Jogador> listarJogadores(BuscaJogadorDto busca) {
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Jogador> query = builder.createQuery(Jogador.class);
            Root<Jogador> root = query.from(Jogador.class);
            query.select(root);
            List<Predicate> predicados = new ArrayList<>();
            if(busca != null){
                condicoesBusca(busca, builder, root, predicados);
                query.where(predicados.toArray(new Predicate[] {}));
                query.orderBy(builder.asc(root.get("username")));
                TypedQuery<Jogador> retorno = entityManager.createQuery(query);
                retorno.setMaxResults(busca.getPageSize());
                retorno.setFirstResult(busca.getPageSize() * busca.getPage());

                return retorno.getResultList();
            }else {
                return new ArrayList<>();
            }
        }catch (Exception e){
            throw new ServiceError(DATA_INVALIDA);
        }


    }

    private void condicoesBusca(BuscaJogadorDto busca, CriteriaBuilder builder, Root<Jogador> root, List<Predicate> predicados) throws ParseException {
        if ((busca.getTitle() != null) && !busca.getTitle().trim().isEmpty())
            predicados.add(builder.equal(root.get("titulo"), retornarTipoMaestria(busca.getTitle().trim().toUpperCase())));
        if(busca.getType() != null && !busca.getType().trim().isEmpty())
            predicados.add(builder.equal(root.get("tipo"), confirmarTipoPlataforma(busca.getType().trim().toUpperCase())));
        if(busca.getUsername() != null && !busca.getUsername().trim().isEmpty())
            predicados.add(builder.like(builder.lower(root.get("username")), "%" + busca.getUsername().trim().toLowerCase() + "%"));
        if(busca.isStreamer() != null) predicados.add(builder.equal(root.get("streamer"), busca.isStreamer()));
        if(busca.getStatus() != null && !busca.getStatus().trim().isEmpty())
            predicados.add(builder.equal(root.get("statusConta"), retornarStatusContaPlataforma(busca.getStatus().trim().toUpperCase())));
        if(busca.getNome() != null && !busca.getNome().trim().isEmpty())
            predicados.add(builder.like(builder.lower(root.get("nome")), "%" + busca.getNome().trim().toLowerCase() + "%"));
        if(busca.getFideInicial() != null)
            predicados.add(builder.greaterThanOrEqualTo(root.get("fide"), busca.getFideFinal()));
        if(busca.getFideFinal() != null)
            predicados.add(builder.lessThanOrEqualTo(root.get("fide"), busca.getFideFinal()));
        if(busca.getDataRegistroInicial() != null && !busca.getDataRegistroInicial().trim().isEmpty())
            predicados.add(builder.greaterThanOrEqualTo(root.get("dataRegistroPlataforma"), UtilData.formatoData().parse(busca.getDataRegistroInicial().trim())));
        if(busca.getDataRegistroFinal() != null && !busca.getDataRegistroFinal().trim().isEmpty())
            predicados.add(builder.lessThanOrEqualTo(root.get("dataRegistroPlataforma"), UtilData.formatoData().parse(busca.getDataRegistroFinal().trim())));
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = TIME_ZONE)
    public void atualizarListaJogadoresTitulados() throws IntegrationError {
        logger.info("Começando job de atualização de lista jogadores titulados");
        for(TipoPlataforma plataforma:TipoPlataforma.values()){
            logger.info("Busca na plataforma : {}", plataforma);
            for(TipoMaestria titulo:TipoMaestria.values()){
                logger.info("Titulo : {}", titulo);
                PlayersTituladosDto playersTituladosDto = getBuscarListaTitulados(plataforma, titulo);
                logger.info("Foram encontrados {} jogadores ", playersTituladosDto.getPlayers().size());
                if(!playersTituladosDto.getPlayers().isEmpty()){
                    this.updateOrCreateListaJogadoresTitulados(plataforma, titulo, playersTituladosDto);
                }
            }
        }

    }

    @Scheduled(cron = "0 0 3 * * ?", zone = TIME_ZONE)
    private void UtilizarDadosJogadores() throws NotFoundError, IntegrationError {
        List<ListaJogadoresTitulados> lista = this.listaJogadoresTituladosRepository.findAll();
        for (ListaJogadoresTitulados listaJogador : lista) {
            logger.info("Vao ser atualizados {} de titularidade {} da plataforma {}", listaJogador.getJogadores().size(), listaJogador.getTitulo(), listaJogador.getTipo());
            for (String jogador : listaJogador.getJogadores()) {
                buscarJogador(jogador, listaJogador.getTitulo().toString());
            }
        }
    }

    private void updateOrCreateListaJogadoresTitulados(TipoPlataforma plataforma, TipoMaestria titulo, PlayersTituladosDto playersTituladosDto) {
        ListaJogadoresTitulados lista = this.listaJogadoresTituladosRepository.findByTituloAndTipo(titulo, plataforma);
        if(lista == null){
            lista = new ListaJogadoresTitulados(titulo, plataforma, playersTituladosDto);
            this.listaJogadoresTituladosRepository.save(lista);
        }else{
            lista.setJogadores(new HashSet<>(playersTituladosDto.getPlayers()));
            lista.setDataUltimaAtualizacao(new Date());
            this.listaJogadoresTituladosRepository.save(lista);
        }
    }

    private PlayersTituladosDto getBuscarListaTitulados(TipoPlataforma plataforma, TipoMaestria titulo) throws IntegrationError {
        PlayersTituladosDto player = null;
        try{
            player =  (PlayersTituladosDto) producerTemplate.requestBodyAndHeader("direct://jogador-titulados",titulo,"plataforma", plataforma );
        }catch(Exception e){
            throw new IntegrationError(UtilConstantes.CHESS_COM, e.getMessage());
        }
        return player;
    }
}
