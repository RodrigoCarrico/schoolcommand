package br.com.onboard.schoolcommand.turma.dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TurmaDto {
    private String id;
    private String descricao;
    private Integer anoLetivo;
    private Integer periodoLetivo;
    private Integer numeroVagas;
    private Set<String> disciplinas;
}
