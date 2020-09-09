package br.com.onboard.schoolcommand.turma.application.command;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CriaTurmaCommand {
    private String descricao;
    private Integer anoLetivo;
    private Integer periodoLetivo;
    private Integer numeroVagas;
    private Set<String> disciplinas;
}
