package br.com.chess.controllers;

import br.com.chess.dto.EnderecoCepDTO;
import br.com.chess.dto.MunicipioDTO;
import br.com.chess.dto.UfDTO;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.services.LocalidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LocalidadeController {

    private final LocalidadeService localidadeService;

    public LocalidadeController(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @GetMapping("/api/v1/municipio")
    public ResponseEntity<List<MunicipioDTO>> list(
            @RequestParam(required = false, name = "uf") String uf,
            @RequestParam(required = false, name = "municipio") String municipio,
            @RequestParam(required = false, name = "codigoMunicipio") String codigoMunicipio,
            @RequestParam(required = false, name = "pageSize") Long pageSize,
            @RequestParam(required = false, name = "page") Long page
    ) {
        return new ResponseEntity<>(this.localidadeService.find(uf, municipio, codigoMunicipio, pageSize, page).stream().map(MunicipioDTO::new).toList(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/uf")
    public ResponseEntity<List<UfDTO>> list() {
        return new ResponseEntity<>(this.localidadeService.findUfs().stream().map(UfDTO::new).toList(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/cep/{numeroCep}")
    public ResponseEntity<EnderecoCepDTO> findByCep(@PathVariable("numeroCep") String numeroCep) throws NotFoundError {
        return new ResponseEntity<>(new EnderecoCepDTO(this.localidadeService.findByCep(numeroCep)), HttpStatus.OK);
    }
}
