package br.com.onboard.schoolcommand.pessoa.domain.events;

import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.utils.DomainCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class ProfessorCriadoEvent implements DomainCommand {
    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final Titulacao titulacao;
    private final Set<ProfessorDisciplinaEvent> disciplinas;

    public static ProfessorCriadoEvent from(Professor professor) {
        return ProfessorCriadoEvent.builder()
                .id(professor.getId())
                .cpf(professor.getCpf())
                .email(professor.getEmail()).id(professor.getId())
                .nome(professor.getNome()).titulacao(professor.getTitulacao())
                .disciplinas(
                        professor.getDisciplinas()
                                .stream().map(ProfessorDisciplinaEvent::from)
                                .collect(Collectors.toSet()))
                .build();
    }

    @Data
    @AllArgsConstructor(staticName = "from")
    public static final class ProfessorDisciplinaEvent {
        private final String disciplinaId;
    }
}
