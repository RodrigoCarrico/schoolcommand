package br.com.onboard.schoolcommand.pessoa.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor()
public class Professor extends Pessoa {

	private static final long serialVersionUID = -5090539717521874770L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Titulacao titulacao;

	@OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
	private List<Disciplina> disciplinas = new ArrayList<>();

	public Professor(String nome, String email, String cpf, Titulacao titulacao) {
		super(nome, email, cpf);
		this.titulacao = titulacao;
	}


}
