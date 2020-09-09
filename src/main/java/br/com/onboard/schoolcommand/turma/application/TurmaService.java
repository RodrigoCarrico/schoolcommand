package br.com.onboard.schoolcommand.turma.application;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.turma.application.command.CriaTurmaCommand;
import br.com.onboard.schoolcommand.turma.dto.TurmaDto;
import br.com.onboard.schoolcommand.turma.model.Turma;
import br.com.onboard.schoolcommand.turma.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    SCHOOLPublisher publisher;

    @Transactional
    public TurmaDto handle(CriaTurmaCommand cmd) {
        Turma turma = Turma.builder()
                .anoLetivo(cmd.getAnoLetivo())
                .descricao(cmd.getDescricao())
                .disciplinas(cmd.getDisciplinas())
                .numeroVagas(cmd.getNumeroVagas())
                .periodoLetivo(cmd.getPeriodoLetivo())
                .build();

        turmaRepository.save(turma);

        publisher.dispatch(turma);

        return TurmaDto.builder()
                .anoLetivo(turma.getAnoLetivo())
                .descricao(turma.getDescricao())
                .id(turma.getId())
                .disciplinas((Set<String>) turma.getDisciplinas())
                .numeroVagas(turma.getNumeroVagas())
                .periodoLetivo(turma.getPeriodoLetivo())
                .build();
    }
}
