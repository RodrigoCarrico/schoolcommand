package br.com.onboard.schoolcommand.pessoa.domain.model;

import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.events.ProfessorAlteradoEvent;
import br.com.onboard.schoolcommand.pessoa.domain.events.ProfessorCriadoEvent;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
@Document(collection = "professor")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Professor extends Pessoa {

    private static final long serialVersionUID = -5090539717521874770L;

    @NotNull
    private Titulacao titulacao;

    private Collection<String> disciplinas ;

    @Builder
    public Professor(String nome, String email, String cpf, Titulacao titulacao, Set<String> disciplinas) {
        super( nome, email, cpf);
        this.titulacao = titulacao;
        this.disciplinas = disciplinas;
        this.addEvent(ProfessorCriadoEvent.from(this));
    }

    public void alterar(String id, String nome, String email, String cpf, Titulacao titulacao, Set<String> disciplinas){
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.titulacao = titulacao;
        this.disciplinas = disciplinas;
        this.addEvent(ProfessorAlteradoEvent.from(this));
    }
}
