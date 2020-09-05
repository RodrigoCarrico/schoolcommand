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
public class ProfessorCriadoEvent implements DomainCommand {
    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final Titulacao titulacao;

    public static ProfessorCriadoEvent from(Professor professor) {
        return ProfessorCriadoEvent.builder()
                .id(professor.getId())
                .cpf(professor.getCpf())
                .email(professor.getEmail()).id(professor.getId())
                .nome(professor.getNome()).titulacao(professor.getTitulacao())
                .build();
    }
}
