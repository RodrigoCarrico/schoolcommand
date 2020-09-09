package br.com.onboard.schoolcommand.turma.model;

import br.com.onboard.schoolcommand.turma.model.event.TurmaCriadaEvent;
import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import br.com.onboard.schoolcommand.utils.GeneratedUUID;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor
@Document(collection = "turma")
public class Turma extends DomainCommandEvents {
    @Id
    private String id;
    @NotNull
    @Length(min = 1, max = 255)
    private String descricao;
    @NotNull
    private Integer anoLetivo;
    @NotNull
    private Integer periodoLetivo;
    @NotNull
    private Integer numeroVagas;

    private Collection<String> alunos = new HashSet<>();

    private Collection<String> disciplinas = new HashSet<>();

    @Builder
    public Turma(String descricao, Integer anoLetivo,
                 Integer periodoLetivo, Integer numeroVagas,
                 Set<String> disciplinas) {
        this.id = GeneratedUUID.getUUID();
        this.descricao = descricao;
        this.anoLetivo = anoLetivo;
        this.periodoLetivo = periodoLetivo;
        this.numeroVagas = numeroVagas;
        this.disciplinas = disciplinas;
        this.addEvent(TurmaCriadaEvent.from(this));
    }
}
