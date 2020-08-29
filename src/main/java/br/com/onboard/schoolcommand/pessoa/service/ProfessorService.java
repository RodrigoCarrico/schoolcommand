package br.com.onboard.schoolcommand.pessoa.service;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.exception.PessoaException;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    SCHOOLPublisher publisher;

    public ProfessorDto atualizar(String id, @Valid ProfessorDto professorDto) {
        Optional<Professor> optional = professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = professorDto.alterar(id, optional.get());
            Professor prof = professorRepository.save(professor);
            publisher.dispach(professor);
            return new ProfessorDto(prof);
        }
        throw new PessoaException("Não foi possivel encontrar o professor com o id: " + id);

    }

    public ProfessorDto cadastrar(@Valid ProfessorDto professorDto) {
        Professor professor = professorDto.build();
        professorRepository.insert(professor);
        publisher.dispach(professor);
        return new ProfessorDto(professor);

    }

}
