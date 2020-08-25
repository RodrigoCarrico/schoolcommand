package br.com.onboard.schoolcommand.pessoa.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.google.gson.Gson;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.repository.AlunoRepository;
import br.com.onboard.schoolcommand.turma.dto.TurmaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AlunoDto {

	private String id;
	@NotNull @Length(min=1, max = 100)
	private String nome;
	@NotNull @Length(min=1, max = 150)
	private String email; 
	@NotNull @Length(min=1, max = 11)
	private String cpf;
	@NotNull
	private int matricula;
	@NotNull
	private FormaIngresso formaIngresso;
	
	private List<TurmaDto> turmas = new ArrayList<TurmaDto>();
	
	
	public AlunoDto(Aluno Aluno) {
		this.id = Aluno.getId();
		this.nome = Aluno.getNome();
		this.email = Aluno.getEmail();
		this.cpf = Aluno.getCpf();
		this.formaIngresso = Aluno.getFormaIngresso();
		this.matricula = Aluno.getMatricula();
		this.turmas = TurmaDto.converter(Aluno.getTurmas());
	}
	
	public Aluno converter() {
		Aluno Aluno = new Aluno(this.nome, this.email, this.cpf, this.matricula, this.formaIngresso);
		return Aluno;
	}

	public Aluno atualiza(Aluno Aluno, AlunoRepository AlunoRepository) {
		Aluno.setNome(this.nome);
		Aluno.setEmail(this.email);
		Aluno.setCpf(this.cpf);
		Aluno.setMatricula(this.matricula);
		Aluno.setFormaIngresso(this.formaIngresso);
		return Aluno;
	}

	public static Page<AlunoDto> from(Page<Aluno> Alunoes) {
		return Alunoes.map(AlunoDto::new);
	}
	
	public static String toGsonString(Aluno Aluno) {
		return new Gson().toJson(new AlunoDto(Aluno), AlunoDto.class);
	}
	
}
