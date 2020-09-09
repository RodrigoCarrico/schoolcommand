package br.com.onboard.schoolcommand.turma.application;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.turma.application.command.CriaTurmaCommand;
import br.com.onboard.schoolcommand.turma.dto.TurmaDto;
import br.com.onboard.schoolcommand.turma.model.Turma;
import br.com.onboard.schoolcommand.turma.repository.TurmaRepository;
import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import br.com.onboard.schoolcommand.utils.GeneratedUUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TurmaServiceTest {
    @Autowired
    TurmaRepository turmaRepository;

    @SpyBean
    SCHOOLPublisher schoolPublisher;

    @Autowired
    TurmaService turmaService;

    @Test
    @DisplayName("Teste Evento de Criação da Turma")
    void deveFazerCriacaoDaTurma() {
        String id = GeneratedUUID.getUUID();
        Integer anoLetivo = 2020;
        String descricao = "MATUTINO";
        Integer numeroVagas = 60;
        Integer periodoLetivo = 1;


        var cmd = CriaTurmaCommand.builder()
                .anoLetivo(anoLetivo)
                .descricao(descricao)
                .numeroVagas(numeroVagas)
                .periodoLetivo(periodoLetivo)
                .disciplinas(new HashSet<>(Arrays.asList("1", "2")))
                .build();


        TurmaDto result = turmaService.handle(cmd);

        var eventArgument = ArgumentCaptor.forClass(DomainCommandEvents.class);
        Mockito.verify(schoolPublisher).dispatch(eventArgument.capture());

        Optional<Turma> turmaFinded = turmaRepository.findById(result.getId());

        assertThat(turmaFinded).isPresent();

        Turma turmaf = turmaFinded.get();
        assertThat(turmaf.getId()).isEqualTo(result.getId());
        assertThat(turmaf.getDescricao()).isEqualTo(result.getDescricao());
        assertThat(turmaf.getAnoLetivo()).isEqualTo(result.getAnoLetivo());
        assertThat(turmaf.getNumeroVagas()).isEqualTo(result.getNumeroVagas());
        assertThat(turmaf.getPeriodoLetivo()).isEqualTo(result.getPeriodoLetivo());
        assertThat(turmaf.getDisciplinas().size()).isEqualTo(result.getDisciplinas().size());

    }


}