package br.com.onboard.schoolcommand.turma.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.onboard.schoolcommand.turma.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurmaDto {
	private Long id;
	private String descricao;
	private int anoLetivo;
	private int periodoLetivo;
	private int numeroVagas;

	public static List<TurmaDto> converter(List<Turma> turmas) {
		return turmas.stream().map(TurmaDto::new).collect(Collectors.toList());
	}

	public TurmaDto(Turma turma) {
		this.id = turma.getId();
		this.descricao = turma.getDescricao();
		this.anoLetivo = turma.getAnoLetivo();
		this.periodoLetivo = turma.getPeriodoLetivo();
		this.numeroVagas = turma.getNumeroVagas();
	}

}
