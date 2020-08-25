package br.com.onboard.schoolcommand.pessoa.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.google.gson.Gson;

import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProfessorDto {
	private String id;
	@NotNull @Length(min=1, max = 100)
	private String nome;
	@NotNull @Length(min=1, max = 150)
	private String email; 
	@NotNull @Length(min=1, max = 11)
	private String cpf;
	@NotNull
	private Titulacao titulacao;
	
	private List<DisciplinaDto> disciplinas = new ArrayList<>();
	
	public ProfessorDto(Professor professor) {
		this.id = professor.getId();
		this.nome = professor.getNome();
		this.email = professor.getEmail();
		this.cpf = professor.getCpf();
		this.titulacao = professor.getTitulacao();
		this.disciplinas = DisciplinaDto.converter(professor.getDisciplinas());
	}
	
	public Professor converter() {
		Professor professor = new Professor(this.nome, this.email, this.cpf, this.titulacao);
		return professor;
	}

	public Professor atualiza(Professor professor, ProfessorRepository professorRepository) {
		professor.setNome(this.nome);
		professor.setEmail(this.email);
		professor.setCpf(this.cpf);
		professor.setTitulacao(this.titulacao);
		return professor;
	}

	public static Page<ProfessorDto> from(Page<Professor> professores) {
		return professores.map(ProfessorDto::new);
	}
	
	public static String toGsonString(Professor professor) {
		return new Gson().toJson(new ProfessorDto(professor), ProfessorDto.class);
	}

}
