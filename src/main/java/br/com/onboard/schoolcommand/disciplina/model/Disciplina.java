package br.com.onboard.schoolcommand.disciplina.model;

import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collation = "disciplina")
public class Disciplina extends DomainCommandEvents {

    @Id
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 100)
    private String descricao;
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 2)
    private String sigla;
    @NotNull
    @NotEmpty
    private int cargaHoraria;

    private String professorId;

    private Collection<String> turmas;

    @Builder
    public Disciplina(String id, @NotNull @NotEmpty @Length(min = 1, max = 100) String descricao,
                      @NotNull @NotEmpty @Length(min = 1, max = 2) String sigla, @NotNull @NotEmpty int cargaHoraria, Collection<String> turmas) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;

    }
}
