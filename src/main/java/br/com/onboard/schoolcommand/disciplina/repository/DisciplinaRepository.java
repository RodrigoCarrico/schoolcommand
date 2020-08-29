package br.com.onboard.schoolcommand.disciplina.repository;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DisciplinaRepository  extends MongoRepository<Disciplina, Long> {
}

