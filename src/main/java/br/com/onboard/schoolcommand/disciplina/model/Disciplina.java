package br.com.onboard.schoolcommand.disciplina.model;

import br.com.onboard.schoolcommand.disciplina.model.event.DisciplinaCriadaEvent;
import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import br.com.onboard.schoolcommand.utils.GeneratedUUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "disciplina")
public class Disciplina extends DomainCommandEvents {

    @Id
    private String id;
    @NotNull
    @Length(min = 1, max = 100)
    private String descricao;
    @NotNull
    @Length(min = 1, max = 2)
    private String sigla;
    @NotNull
    private Integer cargaHoraria;

    private String professorId;

    @Builder
    public Disciplina(String descricao, String sigla, Integer cargaHoraria, String professorId) {
        this.id = GeneratedUUID.getUUID();
        this.descricao = descricao;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.professorId = professorId;
        this.addEvent(DisciplinaCriadaEvent.from(this));
    }

}
