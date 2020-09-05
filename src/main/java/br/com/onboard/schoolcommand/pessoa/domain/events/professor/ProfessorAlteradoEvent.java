package br.com.onboard.schoolcommand.pessoa.domain.events.professor;

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
public class ProfessorAlteradoEvent implements DomainCommand {
    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final Titulacao titulacao;

    public static ProfessorAlteradoEvent from(Professor professor) {
        return ProfessorAlteradoEvent.builder()
                .cpf(professor.getCpf())
                .email(professor.getEmail()).id(professor.getId())
                .nome(professor.getNome()).titulacao(professor.getTitulacao())
                .build();
    }
}
