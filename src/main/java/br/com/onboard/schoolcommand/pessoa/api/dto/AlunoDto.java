package br.com.onboard.schoolcommand.pessoa.api.dto;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class AlunoDto {

    private String id;
    @NotNull
    @Length(min = 1, max = 100)
    private String nome;
    @NotNull
    @Length(min = 1, max = 150)
    private String email;
    @NotNull
    @Length(min = 1, max = 11)
    private String cpf;
    @NotNull
    private int matricula;
    @NotNull
    private FormaIngresso formaIngresso;

    private Set<String> turmas;

}
