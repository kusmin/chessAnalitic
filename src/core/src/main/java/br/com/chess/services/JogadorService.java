package br.com.chess.services;

import br.com.chess.UtilConstantes;
import br.com.chess.UtilData;
import br.com.chess.UtilMetodo;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.TipoPlataforma;
import br.com.chess.dto.JogadorDto;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.exceptions.ServiceError;
import br.com.chess.repositories.JogadorRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JogadorService {

    private final CamelContext camelContext;
    private final JogadorRepository jogadorRepository;

    @Value("${atualizar.jogador}")
    private int prazoAtualizar;

    @Autowired
    public JogadorService(CamelContext camelContext, JogadorRepository jogadorRepository) {
        this.camelContext = camelContext;
        this.jogadorRepository = jogadorRepository;
    }


    public Jogador buscarJogador(String user, String type) throws NotFoundError, IntegrationError {
        TipoPlataforma tipoPlataforma = this.confirmarTipoPlataforma(type);
        user = user.toLowerCase();
        Jogador jogador = this.jogadorRepository.findByUsernameAndTipo(user, tipoPlataforma);

        if(jogador != null && this.confirmarTempoAtualizacao(jogador.getDataUltimaAtualizacao())){
            return jogador;
        }
        JogadorDto jogadorDto = getJogadorDto(user, tipoPlataforma);

        if((jogador == null) && (jogadorDto == null)){
            throw new NotFoundError(UtilConstantes.JOGADOR_DESCONHECIDO);
        }
        if(jogador == null){
            return this.criarJogador(jogadorDto, tipoPlataforma);
        }
        return this.updateJogador(jogadorDto, jogador);
    }

    private JogadorDto getJogadorDto(String user, TipoPlataforma tipoPlataforma) throws IntegrationError {
        JogadorDto jogadorDto;
        try(FluentProducerTemplate fluent = camelContext.createFluentProducerTemplate()){
            jogadorDto = (JogadorDto) fluent.to("direct://buscar-jogador")
                    .withHeader("plataforma", tipoPlataforma).withBody(user).request();

        }catch(Exception e){
            throw new IntegrationError(UtilConstantes.CHESS_COM, e.getMessage());
        }
        return jogadorDto;
    }


    private Jogador updateJogador(JogadorDto jogadorDto, Jogador jogador) {
        if(jogadorDto.getAvatar() != null) jogador.setAvatarUrl(jogadorDto.getAvatar());
        jogador.setJogadorId(jogadorDto.getJogadorId());
        if(jogadorDto.getUrlId() != null) jogador.setAvatarUrl(jogador.getUrlId());
        if(jogadorDto.getUrl() != null) jogador.setAvatarUrl(jogador.getUrl());
        if(jogadorDto.getName() != null)jogador.setNome(jogadorDto.getName());
        jogador.setSeguidores(jogadorDto.getFollowers());
        if(jogadorDto.getLocation() != null)jogador.setLocalidade(jogadorDto.getLocation());
        if(jogadorDto.getCountry() != null)jogador.setPais(jogadorDto.getCountry());
        jogador.setUltimaVezOnline(UtilData.getDateFromTimestamp(Long.parseLong(jogadorDto.getUltimaVezOnline())));
        jogador.setStatusConta(UtilMetodo.retornarStatusContaPlataforma(jogadorDto.getStatus()));
        if(jogadorDto.getTitle() != null) jogador.setTitulo(jogadorDto.getTitle());
        jogador.setFide(jogadorDto.getFide());
        jogador.setStreamer(jogadorDto.isStreamer());
        jogador.setUltimaVezOnline(new Date());
        if(jogadorDto.getTwitchUrl() != null)jogador.setTwitchUrl(jogador.getTwitchUrl());
        return this.jogadorRepository.save(jogador);
    }

    private Jogador criarJogador(JogadorDto jogadorDto, TipoPlataforma tipoPlataforma) {
        return jogadorRepository.save(new Jogador(jogadorDto, tipoPlataforma));
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
