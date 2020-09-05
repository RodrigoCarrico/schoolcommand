package br.com.onboard.schoolcommand.pessoa.api.dto;

import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ProfessorDto {
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
    private Titulacao titulacao;
}
