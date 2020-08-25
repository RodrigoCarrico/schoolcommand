package br.com.onboard.schoolcommand.pessoa.service;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.onboard.schoolcommand.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.exception.PessoaException;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import br.com.onboard.schoolcommand.utils.PropertiesClass;
import br.com.onboard.schoolcommand.utils.SchoolQueueSender;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	SchoolQueueSender<Serializable> schoolQueueSender;

	@Transactional
	public ProfessorDto atualizar(String id, @Valid ProfessorDto professorDto) {
		Optional<Professor> optional = professorRepository.findById(id);
		if (optional.isPresent()) {
			Professor professor = professorDto.atualiza(optional.get(), professorRepository);
			schoolQueueSender.send(ProfessorDto.toGsonString(professor), PropertiesClass.getName(professor));
			return new ProfessorDto(professor);
		}
		throw new PessoaException("Não foi possivel encontrar o professor com o id: " + id);
	}

	@Transactional
	public ProfessorDto cadastrar(@Valid ProfessorDto professorDto) {
		Professor professor = professorDto.converter();
		professorRepository.save(professor);
		schoolQueueSender.send(ProfessorDto.toGsonString(professor), PropertiesClass.getName(professor));
		return new ProfessorDto(professor);

	}

}
