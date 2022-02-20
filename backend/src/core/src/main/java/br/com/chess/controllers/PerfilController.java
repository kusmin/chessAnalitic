package br.com.chess.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.chess.domain.Perfil;
import br.com.chess.domain.Permissao;
import br.com.chess.dto.PerfilEdit;
import br.com.chess.dto.PerfilResponse;
import br.com.chess.dto.PermissaoResponse;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.repositories.PermissaoRepository;
import br.com.chess.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@GetMapping("/api/v1/roles")
	@Secured({"ROLE_ADMIN", "ROLE_PROFILE_CREATE", "ROLE_PROFILE_UPDATE"})
	public ResponseEntity<List<PermissaoResponse>> permissoes() {
		List<Permissao> result = permissaoRepository.findAll();
		List<PermissaoResponse> saida = new ArrayList<>();
		for (Permissao perm : result) {
			saida.add(new PermissaoResponse(perm));
		}
		return new ResponseEntity<>(saida, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/profile/{id}")
	@Secured({"ROLE_PROFILE_LIST", "ROLE_ADMIN"})
	public ResponseEntity<PerfilResponse> getOne(@PathVariable("id") String uuid) throws NotFoundError {
		Perfil perfil = perfilService.findByUuid(uuid);
		return new ResponseEntity<>(new PerfilResponse(perfil), HttpStatus.OK);
	}
	
	@PutMapping("/api/v1/profile/{id}")
	@Secured({"ROLE_PROFILE_UPDATE", "ROLE_ADMIN"})
	public ResponseEntity<PerfilResponse> update(@RequestBody PerfilEdit dto, @PathVariable("id") String uuid) throws NotFoundError {
		Perfil resultado = perfilService.update(uuid, dto);
		return new ResponseEntity<>(new PerfilResponse(resultado), HttpStatus.OK);
	}
	
	@Secured({"ROLE_LIST_PROFILE", "ROLE_ADMIN"})
	@GetMapping("/api/v1/profile")
	public ResponseEntity<List<PerfilResponse>> list(
			@RequestParam(required=false, name="name") String name, 
			@RequestParam(required=false, name="active") Boolean active, 
			@RequestParam(required=false, name="pageSize") Long pageSize, 
			@RequestParam(required=false, name="page") Long page
		) {
		
		List<Perfil> perfis = perfilService.find(name, active, pageSize, page);
		List<PerfilResponse> result = new ArrayList<>();
		for (Perfil perfil : perfis) {
			result.add(new PerfilResponse(perfil));
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({"ROLE_CREATE_PROFILE", "ROLE_ADMIN"})
	@PostMapping("/api/v1/profile")
	public ResponseEntity<PerfilResponse> create(@RequestBody PerfilEdit dto) {
		Perfil perfil = perfilService.create(dto);
		return new ResponseEntity<>(new PerfilResponse(perfil), HttpStatus.CREATED);
	}

}
