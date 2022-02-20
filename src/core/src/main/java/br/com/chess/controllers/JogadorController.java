package br.com.chess.controllers;

import br.com.chess.domain.Jogador;
import br.com.chess.dto.BuscaJogadorDto;
import br.com.chess.dto.JogadorDto;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BuscarJogadoresController {

    private final JogadorService jogadorService;

    @Autowired
    public BuscarJogadoresController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/api/v1/player/{user}/platform/{type}")
    public ResponseEntity<JogadorDto> getJogadorPlataforma(@PathVariable String user, @PathVariable String type) throws NotFoundError, IntegrationError {
        return ResponseEntity.ok().body(new JogadorDto(this.jogadorService.buscarJogador(user, type)));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/api/v1/player")
    public ResponseEntity<List<JogadorDto>> getJogadoresTitulado(@PathVariable String title,
                                                                 @PathVariable String type,
                                                                 @RequestParam(name="pageSize", required=false) Integer pageSize,
                                                                 @RequestParam(name="pagina", required=false) Integer page){
        page = page != null ? page : 0;
        pageSize = pageSize != null ? pageSize : 50;
        BuscaJogadorDto busca = new BuscaJogadorDto(page, pageSize, title, type);
        List<Jogador> listJogadores = this.jogadorService.listarJogadores(busca);
        List<JogadorDto> retorno = new ArrayList<>();
        
    }

}
