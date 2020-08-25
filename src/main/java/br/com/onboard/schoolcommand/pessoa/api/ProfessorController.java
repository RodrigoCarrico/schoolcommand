package br.com.onboard.schoolcommand.pessoa.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.onboard.schoolcommand.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.service.ProfessorService;

@RestController
@RequestMapping(path = ProfessorController.PATH)
public class ProfessorController {
	
	public static final String PATH = "/api/v1/professor";
	
	@Autowired
	ProfessorService professorService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProfessorDto> cadastrar(@RequestBody @Valid ProfessorDto professorDto,
			UriComponentsBuilder uriBuilder) {
		professorDto = professorService.cadastrar(professorDto);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(professorDto.getId()).toUri();

		return ResponseEntity.created(uri).body(professorDto);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDto> atualizar(@PathVariable String id,
			@RequestBody @Valid ProfessorDto professorDto, UriComponentsBuilder uriBuilder) {
		try {
			professorDto = professorService.atualizar(id, professorDto);
			return ResponseEntity.ok(professorDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}

}
