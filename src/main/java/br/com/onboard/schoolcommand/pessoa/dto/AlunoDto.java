package br.com.onboard.schoolcommand.pessoa.dto;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AlunoDto {

    private String id;
    @NotNull
    @Length(min = 1, max = 100)
    private String nome;
    @NotNull
    @Length(min = 1, max = 150)
    private String email;
    @NotNull
    @Length(min = 1, max = 11)
    private String cpf;
    @NotNull
    private int matricula;
    @NotNull
    private FormaIngresso formaIngresso;

    private Set<String> turmas;


    public AlunoDto(Aluno Aluno) {
        this.id = Aluno.getId();
        this.nome = Aluno.getNome();
        this.email = Aluno.getEmail();
        this.cpf = Aluno.getCpf();
        this.formaIngresso = Aluno.getFormaIngresso();
        this.matricula = Aluno.getMatricula();
        this.turmas = (Set) Aluno.getTurmas();
    }

    public Aluno converter() {
        return Aluno.builder().nome(nome).cpf(cpf).email(email).formaIngresso(formaIngresso).turmas(turmas).build();
    }

    public Aluno atualiza(Aluno Aluno) {
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

}
