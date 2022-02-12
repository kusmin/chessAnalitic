package br.com.chess.services;

import br.com.chess.UtilConstantes;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.TipoPlataforma;
import br.com.chess.dto.JogadorDto;
import br.com.chess.exceptions.ServiceError;
import br.com.chess.repositories.JogadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JogadorService {

    private final CamelContext camelContext;
    private final JogadorRepository jogadorRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${atualizar.jogadorl}")
    private int prazoAtualizar;

    @Autowired
    public JogadorService(CamelContext camelContext, JogadorRepository jogadorRepository) {
        this.camelContext = camelContext;
        this.jogadorRepository = jogadorRepository;
    }


    public Jogador buscarJogador(String user, String type) {
        TipoPlataforma tipoPlataforma = this.confirmarTipoPlataforma(type);

        Jogador jogador = this.jogadorRepository.findByUsernameAndTipo(user, tipoPlataforma);

        if(jogador != null && this.confirmarTempoAtualizacao(jogador.getDataUltimaAtualizacao())){
            return jogador;
        }

        JogadorDto jogadorDto = (JogadorDto) camelContext.createFluentProducerTemplate().to("direct://buscar-jogador")
                .withHeader("plataforma", tipoPlataforma).withBody(user)
                .request();

        try {
            System.out.println(mapper.writeValueAsString(jogadorDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jogador;
    }

    private boolean confirmarTempoAtualizacao(Date dataUltimaAtualizacao) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, prazoAtualizar);
        return dataUltimaAtualizacao.before(calendar.getTime());
    }

    private TipoPlataforma confirmarTipoPlataforma(String type) {
        try{
            return TipoPlataforma.valueOf(type);
        }catch (Exception e) {
            throw new ServiceError(UtilConstantes.TIPO_PLATAFORMA_DESCONHECIDO);
        }

    }


}
