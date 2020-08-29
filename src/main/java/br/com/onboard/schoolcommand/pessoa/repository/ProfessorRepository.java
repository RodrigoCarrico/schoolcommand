package br.com.onboard.schoolcommand.pessoa.repository;

import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {

    Professor insert(Professor professor);

}
