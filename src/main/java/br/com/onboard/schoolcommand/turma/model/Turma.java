package br.com.onboard.schoolcommand.turma.model;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 1, max = 255)
    private String descricao;
    @NotNull
    private int anoLetivo;
    @NotNull
    private int periodoLetivo;
    @NotNull
    private int numeroVagas;

    @ManyToMany(mappedBy = "turmas")
    private List<Aluno> alunos;

    @ManyToMany
    @JoinTable(
            name = "turma_disciplina",
            joinColumns =
            @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "ID")
    )
    private List<Disciplina> disciplinas;

    public Turma(@NotNull @NotEmpty @Length(min = 1, max = 255) String descricao, @NotNull @NotEmpty int anoLetivo,
                 @NotNull @NotEmpty int periodoLetivo, @NotNull @NotEmpty int numeroVagas) {
        super();
        this.descricao = descricao;
        this.anoLetivo = anoLetivo;
        this.periodoLetivo = periodoLetivo;
        this.numeroVagas = numeroVagas;
    }

}
