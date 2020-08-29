package br.com.onboard.schoolcommand.pessoa.domain.model;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@Document
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Aluno extends Pessoa {

    private static final long serialVersionUID = -7320945749817753922L;
    @NotNull
    private int matricula;
    @NotNull
    private FormaIngresso formaIngresso;

    private Collection<String> turmas = new HashSet<>();

    @Builder
    public Aluno(String nome, String email, String cpf, Integer matricula, FormaIngresso formaIngresso, Set<String> turmas) {
        super(nome, email, cpf);
        this.formaIngresso = formaIngresso;
        this.matricula = matricula;
        this.turmas = turmas;
    }

}
