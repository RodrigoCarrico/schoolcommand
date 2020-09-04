package br.com.onboard.schoolcommand.disciplina.model.event;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.utils.DomainCommand;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class DisciplinaCriadaEvent implements DomainCommand {

    private String id;
    private String descricao;
    private String sigla;
    private Integer cargaHoraria;
    private String professorId;

    public static DisciplinaCriadaEvent from(Disciplina disciplina) {
        return DisciplinaCriadaEvent.builder()
                .cargaHoraria(disciplina.getCargaHoraria())
                .descricao(disciplina.getDescricao())
                .id(disciplina.getId())
                .professorId(disciplina.getProfessorId())
                .sigla(disciplina.getSigla())
                .build();
    }
}
