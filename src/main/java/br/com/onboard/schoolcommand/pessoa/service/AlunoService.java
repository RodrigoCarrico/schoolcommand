package br.com.onboard.schoolcommand.pessoa.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.onboard.schoolcommand.pessoa.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.exception.PessoaException;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;

	/*@Autowired
	SchoolQueueSender<Serializable> schoolQueueSender;
*/
	@Transactional
	public AlunoDto atualizar(String id, @Valid AlunoDto alunoDto) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			Aluno aluno = alunoDto.atualiza(optional.get());
			//schoolQueueSender.send(AlunoDto.toGsonString(aluno), PropertiesClass.getName(aluno));
			return new AlunoDto(aluno);
		}
		throw new PessoaException("Não foi possivel encontrar o Aluno com o id: " + id);
	}

	@Transactional
	public AlunoDto cadastrar(@Valid AlunoDto alunoDto) {
		Aluno aluno = alunoDto.converter();
		alunoRepository.save(aluno);
		//schoolQueueSender.send(AlunoDto.toGsonString(aluno), PropertiesClass.getName(aluno));
		return new AlunoDto(aluno);

	}
}
