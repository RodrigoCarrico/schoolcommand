package br.com.onboard.schoolcommand.disciplina.application;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.disciplina.application.command.CriarDisciplinaCommand;
import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolcommand.pessoa.api.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.CriarAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DisciplinaServiceTest {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @SpyBean
    SCHOOLPublisher schoolPublisher;

    @Autowired
    DisciplinaService disciplinaService;

    @Test
    @DisplayName("Teste Evento de Criação da Disciplina")
    void deveFazerCriacaoDaDisciplina() {
        String descricao = "Matematica";
        String professorId = "1";
        String sigla = "M";
        Integer cargaHoraria = 100;

        var cmd = CriarDisciplinaCommand.builder()
               .cargaHoraria(cargaHoraria)
                .descricao(descricao)
                .professorId(professorId)
                .sigla(sigla)
                .build();


        DisciplinaDto result = disciplinaService.handler(cmd);

        var eventArgument = ArgumentCaptor.forClass(DomainCommandEvents.class);
        Mockito.verify(schoolPublisher).dispatch(eventArgument.capture());

        Optional<Disciplina> disciplinaFinded =disciplinaRepository.findById(result.getId());

        assertThat(disciplinaFinded).isPresent();
        Disciplina disciplinaf = disciplinaFinded.get();
        assertThat(disciplinaf.getId()).isEqualTo(result.getId());
        assertThat(disciplinaf.getDescricao()).isEqualTo(result.getDescricao());
        assertThat(disciplinaf.getSigla()).isEqualTo(result.getSigla());
        assertThat(disciplinaf.getProfessorId()).isEqualTo(result.getProfessorId());
        assertThat(disciplinaf.getCargaHoraria()).isEqualTo(result.getCargaHoraria());
    }
}