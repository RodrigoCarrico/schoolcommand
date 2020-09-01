package br.com.onboard.schoolcommand.pessoa.api;

import java.net.URI;

import javax.validation.Valid;

import br.com.onboard.schoolcommand.pessoa.application.command.aluno.AlteraAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.CriarAlunoCommand;
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

import br.com.onboard.schoolcommand.pessoa.api.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.application.service.AlunoApplicationService;

@RestController
@RequestMapping(path = AlunoController.PATH)
public class AlunoController {
	public static final String PATH = "/api/v1/aluno";
	
	@Autowired
	AlunoApplicationService alunoService;
	
	@PostMapping
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoDto alunoDto,
			UriComponentsBuilder uriBuilder) {
		var cmd = CriarAlunoCommand.builder()
				.cpf(alunoDto.getCpf())
				.email(alunoDto.getEmail())
				.formaIngresso(alunoDto.getFormaIngresso())
				.matricula(alunoDto.getMatricula())
				.nome(alunoDto.getNome())
				.turmas(alunoDto.getTurmas())
				.build();

		alunoDto = alunoService.handle(cmd);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(alunoDto.getId()).toUri();

		return ResponseEntity.created(uri).body(alunoDto);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoDto> atualizar(@PathVariable String id,
			@RequestBody @Valid AlunoDto alunoDto) {

		var cmd = AlteraAlunoCommand.builder()
				.cpf(alunoDto.getCpf())
				.email(alunoDto.getEmail())
				.formaIngresso(alunoDto.getFormaIngresso())
				.matricula(alunoDto.getMatricula())
				.nome(alunoDto.getNome())
				.turmas(alunoDto.getTurmas())
				.build();
		try {
			alunoDto = alunoService.handle(id, cmd);
			return ResponseEntity.ok(alunoDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}

}
