package br.com.onboard.schoolcommand.pessoa.domain.events.Aluno;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.utils.DomainCommand;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AlunoCriadoEvent implements DomainCommand {
    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final Integer matricula;
    private final FormaIngresso formaIngresso;
    private final Set<AlunoTurmaCriadoEvent> turmas;

    public static AlunoCriadoEvent from(Aluno aluno) {
        return AlunoCriadoEvent.builder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .email(aluno.getEmail()).id(aluno.getId())
                .nome(aluno.getNome()).matricula(aluno.getMatricula())
                .formaIngresso(aluno.getFormaIngresso())
                .turmas(
                        aluno.getTurmas()
                                .stream().map(AlunoTurmaCriadoEvent::from)
                                .collect(Collectors.toSet()))
                .build();
    }

    @Data
    @AllArgsConstructor(staticName = "from")
    public static final class AlunoTurmaCriadoEvent {
        private final String turmaId;
    }
}

