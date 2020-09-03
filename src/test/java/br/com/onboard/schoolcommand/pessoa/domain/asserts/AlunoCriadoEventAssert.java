package br.com.onboard.schoolcommand.pessoa.domain.asserts;

import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoCriadoEvent;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class AlunoCriadoEventAssert extends AbstractAssert<AlunoCriadoEventAssert, AlunoCriadoEvent> {

    public AlunoCriadoEventAssert(AlunoCriadoEvent actual) {
        super(actual, AlunoCriadoEventAssert.class);
    }

    public AlunoCriadoEventAssert hasInformationAccordingTo(Aluno event) {
        isNotNull();

        Assertions.assertThat(actual.getCpf()).isEqualTo(event.getCpf());
        Assertions.assertThat(actual.getNome()).isEqualTo(event.getNome());
        Assertions.assertThat(actual.getEmail()).isEqualTo(event.getEmail());
        Assertions.assertThat(actual.getFormaIngresso()).isEqualTo(event.getFormaIngresso());
        Assertions.assertThat(actual.getMatricula()).isEqualTo(event.getMatricula());
        Assertions.assertThat(actual.getNome()).isEqualTo(event.getNome());
        Assertions.assertThat(actual.getTurmas().size()).isEqualTo(event.getTurmas().size());

        return this;
    }

    public static AlunoCriadoEventAssert assertThat(AlunoCriadoEvent actual) {
        return new AlunoCriadoEventAssert(actual);
    }
}
