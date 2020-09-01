package br.com.onboard.schoolcommand.pessoa.domain.model;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoAlteradoEvent;
import br.com.onboard.schoolcommand.pessoa.domain.events.Aluno.AlunoCriadoEvent;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
@Document(collection = "aluno")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Aluno extends Pessoa {

    private static final long serialVersionUID = -7320945749817753922L;
    @NotNull
    private Integer matricula;
    @NotNull
    private FormaIngresso formaIngresso;

    private Collection<String> turmas = new HashSet<>();

    @Builder
    public Aluno(String nome, String email, String cpf, Integer matricula, FormaIngresso formaIngresso, Set<String> turmas) {
        super(nome, email, cpf);
        this.formaIngresso = formaIngresso;
        this.matricula = matricula;
        this.turmas = turmas;
        this.addEvent(AlunoCriadoEvent.from(this));
    }

    public void altera (String id,String nome, String email, String cpf, Integer matricula, FormaIngresso formaIngresso, Set<String> turmas) {
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.formaIngresso = formaIngresso;
        this.matricula = matricula;
        this.turmas = turmas;
        this.addEvent(AlunoAlteradoEvent.from(this));
    }

}
