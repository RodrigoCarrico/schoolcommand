package br.com.onboard.schoolcommand.turma.api;

import br.com.onboard.schoolcommand.turma.application.TurmaService;
import br.com.onboard.schoolcommand.turma.application.command.CriaTurmaCommand;
import br.com.onboard.schoolcommand.turma.dto.TurmaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = TurmaController.PATH)
public class TurmaController {

    public static final String PATH = "/api/v1/turma";

    @Autowired
    TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaDto> cadastrar(@RequestBody @Valid TurmaDto turmaDto,
                                         UriComponentsBuilder uriBuilder) {
        var cmd = CriaTurmaCommand.builder()
                .anoLetivo(turmaDto.getAnoLetivo())
                .descricao(turmaDto.getDescricao())
                .disciplinas(turmaDto.getDisciplinas())
                .numeroVagas(turmaDto.getNumeroVagas())
                .periodoLetivo(turmaDto.getPeriodoLetivo())
                .build();

        turmaDto = turmaService.handle(cmd);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(turmaDto.getId()).toUri();

        return ResponseEntity.created(uri).body(turmaDto);
    }
}
