package br.com.chess.controllers;

import br.com.chess.dto.BuscaJogadorDto;
import br.com.chess.dto.JogadorDto;
import br.com.chess.dto.JogadorResponseDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JogadorController {

    private final JogadorService jogadorService;

    @Autowired
    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/api/v1/player/{user}/platform/{type}")
    public ResponseEntity<JogadorResponseDto> getJogadorPlataforma(@PathVariable String user, @PathVariable String type) throws NotFoundError, IntegrationError {
        return ResponseEntity.ok().body(new JogadorResponseDto(this.jogadorService.buscarJogador(user, type)));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/api/v1/player")
    public ResponseEntity<List<JogadorDto>> getJogadoresTitulado(@RequestParam(name="title", required=false) String title,
                                                                 @RequestParam(name="type", required=false) String type,
                                                                 @RequestParam(name="username", required=false) String username,
                                                                 @RequestParam(name="streamer", required=false) Boolean streamer,
                                                                 @RequestParam(name="status", required=false) String status,
                                                                 @RequestParam(name="name", required=false) String name,
                                                                 @RequestParam(name="fideInicial", required=false) Integer fideInicial,
                                                                 @RequestParam(name="fideFinal", required=false) Integer fideFinal,
                                                                 @RequestParam(name="dataRegistroInicial", required=false) String dataRegistroInicial,
                                                                 @RequestParam(name="dataRegistroFinal", required=false) String dataRegistroFinal,
                                                                 @RequestParam(name="pageSize", required=false) Integer pageSize,
                                                                 @RequestParam(name="pagina", required=false) Integer page){
        page = page != null ? page : 0;
        pageSize = pageSize != null ? pageSize : 50;
        BuscaJogadorDto busca = new BuscaJogadorDto(page, pageSize, title, type,
                username, streamer, status, name, fideInicial,
                fideFinal, dataRegistroInicial, dataRegistroFinal);

        return ResponseEntity.ok().body(this.jogadorService.listarJogadores(busca).stream().map(JogadorDto::new).collect(Collectors.toList()));
    }

}
