package br.com.onboard.schoolcommand.pessoa.api;

import br.com.onboard.schoolcommand.pessoa.api.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.application.command.professor.AlteraProfessorCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.professor.CriarProfessorCommand;
import br.com.onboard.schoolcommand.pessoa.application.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = ProfessorController.PATH)
public class ProfessorController {

    public static final String PATH = "/api/v1/professor";

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDto> criar(@RequestBody @Valid ProfessorDto professorDto,
                                              UriComponentsBuilder uriBuilder) {
        var cmd = CriarProfessorCommand.builder().cpf(professorDto.getCpf())
                .email(professorDto.getEmail()).nome(professorDto.getNome())
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
                    .cpf(professorDto.getCpf())
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
