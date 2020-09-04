package br.com.onboard.schoolcommand.pessoa.domain.events.Aluno;

import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.factory.AlunoFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AlunoCriadoEventTest {
    @Test
    public void deveCriarOAlunoCriadoEventAPartirdeUmAluno(){
        Aluno aluno = AlunoFactory.generate();
        var event = AlunoCriadoEvent.from(aluno);

        assertThat(aluno.getEvents().size()).isNotZero();

        assertThat(event.getCpf()).isEqualTo(aluno.getCpf());
        assertThat(event.getNome()).isEqualTo(aluno.getNome());
        assertThat(event.getEmail()).isEqualTo(aluno.getEmail());
        assertThat(event.getFormaIngresso()).isEqualTo(aluno.getFormaIngresso());
        assertThat(event.getId()).isEqualTo(aluno.getId());
        assertThat(event.getMatricula()).isEqualTo(aluno.getMatricula());
        assertThat(event.getTurmas().size()).isEqualTo(aluno.getTurmas().size());


    }
}