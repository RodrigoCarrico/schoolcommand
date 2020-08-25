package br.com.onboard.schoolcommand.disciplina.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.turma.model.Turma;

@ToString
@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull @NotEmpty @Length(min=1, max = 100)
	private String descricao;
	@NotNull @NotEmpty @Length(min=1, max = 2)
	private String sigla;
	@NotNull @NotEmpty
	private int cargaHoraria;
	
	@ManyToOne
	private Professor professor;
	
	@ManyToMany(mappedBy = "disciplinas")
	private List<Turma> turmas;

	public Disciplina(@NotNull @NotEmpty @Length(min = 1, max = 100) String descricao,
			@NotNull @NotEmpty @Length(min = 1, max = 2) String sigla, @NotNull @NotEmpty int cargaHoraria,
			Professor professor) {
		this.descricao = descricao;
		this.sigla = sigla;
		this.cargaHoraria = cargaHoraria;
		this.professor = professor;
	}
	
	
	

}
