package br.com.onboard.schoolcommand.pessoa.domain.model;

import br.com.onboard.schoolcommand.pessoa.domain.asserts.AlunoAlteradoEventAssert;
import br.com.onboard.schoolcommand.pessoa.domain.asserts.AlunoCriadoEventAssert;
import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoAlteradoEvent;
import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoCriadoEvent;
import br.com.onboard.schoolcommand.pessoa.factory.AlunoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class AlunoTest {

    @Test
    @DisplayName("Teste Criação Aluno")
    void testeDeCriacaoAluno() {
        Aluno aluno = AlunoFactory.generate();
        var event = (AlunoCriadoEvent) aluno.getEvents().get(0);
        assertThat(aluno).isNotNull();
        assertThat(aluno.getCpf()).isNotNull();
        assertThat(aluno.getNome()).isNotNull();
        assertThat(aluno.getMatricula()).isNotNull();
        assertThat(aluno.getFormaIngresso()).isNotNull();
        assertThat(aluno.getEvents()).isNotNull();
        assertThat(aluno.getTurmas().size()).isNotZero();
        assertThat(aluno.getEvents().size()).isNotZero();

        AlunoCriadoEventAssert.assertThat(event).hasInformationAccordingTo(aluno);

    }

    @Test
    @DisplayName("Teste Alteração Aluno")
    void testeDeAlteracaoDeAluno() {

        Aluno aluno = AlunoFactory.generate();
        aluno.altera(aluno.getId(), "Altera", "teste@Altera.com.br", "0001", 1234567, FormaIngresso.VESTIBULAR, new HashSet<>(Arrays.asList("1", "2", "3")));

        var event = aluno.getEvents().stream().filter(s -> s.getClass().equals(AlunoAlteradoEvent.class)).collect(Collectors.toList());
        AlunoAlteradoEvent alunoAlteradoEvent = (AlunoAlteradoEvent) event.get(0);

        assertThat(aluno).isNotNull();
        assertThat(aluno.getTurmas().size()).isGreaterThan(0);

        AlunoAlteradoEventAssert.assertThat(alunoAlteradoEvent).hasInformationAccordingTo(aluno);

    }

}