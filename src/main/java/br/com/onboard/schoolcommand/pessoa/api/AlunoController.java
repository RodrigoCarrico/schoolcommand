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

import br.com.onboard.schoolcommand.pessoa.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.service.AlunoService;

@RestController
@RequestMapping(path = AlunoController.PATH)
public class AlunoController {
	public static final String PATH = "/api/v1/aluno";
	
	@Autowired
	AlunoService alunoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoDto alunoDto,
			UriComponentsBuilder uriBuilder) {
		alunoDto = alunoService.cadastrar(alunoDto);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(alunoDto.getId()).toUri();

		return ResponseEntity.created(uri).body(alunoDto);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoDto> atualizar(@PathVariable String id,
			@RequestBody @Valid AlunoDto alunoDto) {
		try {
			alunoDto = alunoService.atualizar(id, alunoDto);
			return ResponseEntity.ok(alunoDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}

}
