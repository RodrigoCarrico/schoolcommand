package br.com.onboard.schoolcommand.turma.model;

import br.com.onboard.schoolcommand.utils.GenerateUUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Document
public class Turma {
    @Id
    private String id;
    @NotNull
    @Length(min = 1, max = 255)
    private String descricao;
    @NotNull
    private int anoLetivo;
    @NotNull
    private int periodoLetivo;
    @NotNull
    private int numeroVagas;

    //@ManyToMany(mappedBy = "turmas")
    private Collection<String> alunos;

    /*@ManyToMany
    @JoinTable(
            name = "turma_disciplina",
            joinColumns =
            @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "ID")
    )*/
    private Collection<String> disciplinas;

    public Turma(String id, @NotNull @NotEmpty @Length(min = 1, max = 255) String descricao, @NotNull @NotEmpty int anoLetivo,
                 @NotNull @NotEmpty int periodoLetivo, @NotNull @NotEmpty int numeroVagas) {
        this.id = GenerateUUID.generate();
        this.descricao = descricao;
        this.anoLetivo = anoLetivo;
        this.periodoLetivo = periodoLetivo;
        this.numeroVagas = numeroVagas;
    }

}
