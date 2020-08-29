package br.com.onboard.schoolcommand.pessoa.dto;

import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {
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
    private Titulacao titulacao;

    private Set<String> disciplinas;

    public ProfessorDto(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
        this.cpf = professor.getCpf();
        this.titulacao = professor.getTitulacao();
        this.disciplinas =  (Set) professor.getDisciplinas();
    }

    public Professor build() {
        return Professor.builder().cpf(cpf).disciplinas(disciplinas).email(email).titulacao(titulacao).nome(nome).id(id).build();
    }

    public Professor alterar(String id, Professor professor){
        professor.alterar(id, this.nome, this.email, this.cpf, this.titulacao, this.disciplinas);
        return professor;
    }

    public Professor transform(Professor professor) {
        professor.setNome(this.nome);
        professor.setEmail(this.email);
        professor.setCpf(this.cpf);
        professor.setTitulacao(this.titulacao);
        return professor;
    }

    public static Page<ProfessorDto> from(Page<Professor> professores) {
        return professores.map(ProfessorDto::new);
    }

}
