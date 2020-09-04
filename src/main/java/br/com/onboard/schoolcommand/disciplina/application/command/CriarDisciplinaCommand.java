package br.com.onboard.schoolcommand.disciplina.application.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarDisciplinaCommand {
    private String descricao;
    private String sigla;
    private Integer cargaHoraria;
    private String professorId;
}
