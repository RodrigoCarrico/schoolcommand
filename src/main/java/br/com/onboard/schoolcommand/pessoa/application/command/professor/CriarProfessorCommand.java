package br.com.onboard.schoolcommand.pessoa.application.command.professor;

import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CriarProfessorCommand {
    private String nome;
    private String email;
    private String cpf;
    private Titulacao titulacao;
    private Set<String> disciplinas;
}
