package br.com.onboard.schoolcommand.disciplina.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class DisciplinaDto {
	private Long id;
	private String descricao;
	private String sigla;
	private int cargaHoraria;
	
	public DisciplinaDto(Disciplina disciplina) {
		this.id = disciplina.getId();
		this.descricao = disciplina.getDescricao();
		this.sigla = disciplina.getSigla();
		this.cargaHoraria = disciplina.getCargaHoraria();
	}
	
	public static List<DisciplinaDto> converter(List<Disciplina> disciplinas) {
		return disciplinas.stream().map(DisciplinaDto::new).collect(Collectors.toList());
	}

}
