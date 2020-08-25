package br.com.onboard.schoolcommand.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

}
