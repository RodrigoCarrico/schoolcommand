package br.com.onboard.schoolcommand.pessoa.domain.model;

import br.com.onboard.schoolcommand.pessoa.domain.asserts.AlunoCriadoEventAssert;
import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoAlteradoEvent;
import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoCriadoEvent;
import br.com.onboard.schoolcommand.pessoa.factory.AlunoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;


class AlunoTest {

    @Test
    @DisplayName("Teste Criação Aluno")
    void testeDeCriacaoAluno() {
        Aluno aluno = AlunoFactory.generate();
        var event = (AlunoCriadoEvent) aluno.getEvents().get(0);
        assertThat(aluno).isNotNull();
        assertThat(aluno.getTurmas().size()).isNotZero();

        AlunoCriadoEventAssert.assertThat(event).hasInformationAccordingTo(aluno);

    }

    @Test
    @DisplayName("Teste Alteração Aluno")
    void testeDeAlteracaoDeAluno() {

/*
        Aluno aluno = AlunoFactoryTest.generate();
        aluno.altera(aluno.getId(), "Altera", "teste@Altera.com.br", "0001", 1234567, FormaIngresso.VESTIBULAR, new HashSet<>(Arrays.asList("1", "2", "3")));
*/


        Aluno alunoSpy = Mockito.spy(AlunoFactory.generate());
        alunoSpy.altera(alunoSpy.getId(), "Altera", "teste@Altera.com.br", "0001", 1234567, FormaIngresso.VESTIBULAR, new HashSet<>(Arrays.asList("1", "2", "3")));
        Mockito.verify(alunoSpy).addEvent(AlunoAlteradoEvent.from(alunoSpy));

      /*  var event = aluno.getEvents().stream().filter(s -> s.getClass().equals(AlunoAlteradoEvent.class)).collect(Collectors.toList());
        AlunoAlteradoEvent alunoAlteradoEvent = (AlunoAlteradoEvent) event.get(0);

        assertThat(aluno).isNotNull();
        assertThat(aluno.getTurmas().size()).isEqualTo(3);*/



       // AlunoAlteradoEventAssert.assertThat(alunoAlteradoEvent).hasInformationAccordingTo(aluno);

    }

}