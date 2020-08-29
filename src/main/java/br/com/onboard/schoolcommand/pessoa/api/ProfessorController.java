package br.com.onboard.schoolcommand.pessoa.api;

import java.net.URI;

import javax.validation.Valid;

import br.com.onboard.schoolcommand.pessoa.application.command.AlteraProfessorCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.CriarProfessorCommand;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
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

import br.com.onboard.schoolcommand.pessoa.api.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.application.service.ProfessorApplicationService;

@RestController
@RequestMapping(path = ProfessorController.PATH)
public class ProfessorController {
	
	public static final String PATH = "/api/v1/professor";
	
	@Autowired
	ProfessorApplicationService professorService;
	
	@PostMapping
	public ResponseEntity<ProfessorDto> criar(@RequestBody @Valid ProfessorDto professorDto,
			UriComponentsBuilder uriBuilder) {
		var cmd = CriarProfessorCommand.builder().cpf(professorDto.getCpf())
				.disciplinas(professorDto.getDisciplinas()).email(professorDto.getEmail()).nome(professorDto.getNome())
				.titulacao(professorDto.getTitulacao()).build();
		professorDto = professorService.handle(cmd);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(professorDto.getId()).toUri();

		return ResponseEntity.created(uri).body(professorDto);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDto> atualizar(@PathVariable String id,
			@RequestBody @Valid ProfessorDto professorDto) {
		try {
			var cmd = AlteraProfessorCommand.builder()
					.cpf(professorDto.getCpf()).disciplinas(professorDto.getDisciplinas())
					.email(professorDto.getEmail())
					.id(id)
					.nome(professorDto.getNome())
					.titulacao(professorDto.getTitulacao())
					.build();
			professorDto = professorService.handle(cmd);
			return ResponseEntity.ok(professorDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}

}
