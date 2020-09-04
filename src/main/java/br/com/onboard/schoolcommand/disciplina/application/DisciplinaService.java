package br.com.onboard.schoolcommand.disciplina.application;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.disciplina.application.command.CriarDisciplinaCommand;
import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.disciplina.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    SCHOOLPublisher publisher;


    public DisciplinaDto handler(CriarDisciplinaCommand cmd) {

        var disciplina = Disciplina.builder()
                .cargaHoraria(cmd.getCargaHoraria())
                .descricao(cmd.getDescricao())
                .professorId(cmd.getProfessorId())
                .sigla(cmd.getSigla())
                .build();

        disciplinaRepository.save(disciplina);

        publisher.dispatch(disciplina);

        return DisciplinaDto.builder()
                .id(disciplina.getId())
                .cargaHoraria(disciplina.getCargaHoraria())
                .descricao(disciplina.getDescricao())
                .professorId(disciplina.getProfessorId())
                .sigla(disciplina.getSigla())
                .build();
    }
}
