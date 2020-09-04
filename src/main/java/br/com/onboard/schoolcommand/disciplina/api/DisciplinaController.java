package br.com.onboard.schoolcommand.disciplina.api;

import br.com.onboard.schoolcommand.disciplina.application.DisciplinaService;
import br.com.onboard.schoolcommand.disciplina.application.command.CriarDisciplinaCommand;
import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = DisciplinaController.PATH)
public class DisciplinaController {

    public static final String PATH = "/api/v1/disciplina";


    @Autowired
    DisciplinaService disciplinaService;


    @PostMapping
    public ResponseEntity<DisciplinaDto> cadastrar(@RequestBody @Valid DisciplinaDto disciplinaDto,
                                                   UriComponentsBuilder uriBuilder) {
        var cmd = CriarDisciplinaCommand.builder()
                .cargaHoraria(disciplinaDto.getCargaHoraria())
                .descricao(disciplinaDto.getDescricao())
                .professorId(disciplinaDto.getProfessorId())
                .sigla(disciplinaDto.getSigla())
                .build();

        disciplinaDto = disciplinaService.handler(cmd);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(disciplinaDto.getId()).toUri();

        return ResponseEntity.created(uri).body(disciplinaDto);
    }

}
