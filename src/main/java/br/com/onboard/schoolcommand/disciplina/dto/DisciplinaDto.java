package br.com.onboard.schoolcommand.disciplina.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DisciplinaDto {
    private String id;
    private String descricao;
    private String sigla;
    private Integer cargaHoraria;
    private String professorId;

}
