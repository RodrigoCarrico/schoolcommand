package br.com.onboard.schoolcommand.pessoa.application.command.aluno;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AlteraAlunoCommand {
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private Integer matricula;
    private FormaIngresso formaIngresso;
    private Set<String> turmas;
}
