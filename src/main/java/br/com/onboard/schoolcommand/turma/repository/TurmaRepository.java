package br.com.onboard.schoolcommand.turma.repository;

import br.com.onboard.schoolcommand.turma.model.Turma;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TurmaRepository extends MongoRepository<Turma, String> {
}
