package br.com.onboard.schoolcommand.pessoa.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.turma.model.Turma;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Aluno extends Pessoa {

	private static final long serialVersionUID = -7320945749817753922L;
	@NotNull
	private int matricula;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FormaIngresso formaIngresso;

	@ManyToMany
	@JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"))
	private List<Turma> turmas= new ArrayList<Turma>();

	public Aluno(String nome, String email, String cpf, Integer matricula, FormaIngresso formaIngresso) {
		super(nome, email, cpf);
		this.formaIngresso = formaIngresso;
		this.matricula = matricula;
	}

}
