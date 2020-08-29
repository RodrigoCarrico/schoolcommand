package br.com.onboard.schoolcommand.pessoa.repository;

import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {

}
