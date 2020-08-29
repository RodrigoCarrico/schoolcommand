package br.com.onboard.schoolcommand.pessoa.application.service;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.pessoa.api.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.application.command.AlteraProfessorCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.CriarProfessorCommand;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.exception.PessoaException;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorApplicationService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    SCHOOLPublisher publisher;

    //Handle de criação de Professor
    public ProfessorDto handle(CriarProfessorCommand cmd) {

        Professor professor = Professor.builder()
                .cpf(cmd.getCpf())
                .disciplinas(cmd.getDisciplinas())
                .email(cmd.getEmail())
                .nome(cmd.getNome())
                .titulacao(cmd.getTitulacao())
                .build();
        Professor professorRetorno = professorRepository.save(professor);
        publisher.dispach(professorRetorno);

        return ProfessorDto.builder().cpf(professorRetorno.getCpf())
                .disciplinas((Set<String>) professorRetorno.getDisciplinas())
                .email(professorRetorno.getEmail())
                .id(professorRetorno.getId())
                .nome(professorRetorno.getNome())
                .titulacao(professorRetorno.getTitulacao()).build();

    }

    //Handle de Alteração de Professor
    public ProfessorDto handle(AlteraProfessorCommand cmd) {

        Optional<Professor> optional = professorRepository.findById(cmd.getId());
        if (optional.isPresent()) {
            Professor professor = optional.get();
            professor.alterar(cmd.getId(), cmd.getNome(), cmd.getEmail(), cmd.getCpf(), cmd.getTitulacao(),cmd.getDisciplinas());
            Professor professorRetorno = professorRepository.save(professor);
            publisher.dispach(professor);

            return ProfessorDto.builder().cpf(professorRetorno.getCpf())
                    .disciplinas((Set<String>) professorRetorno.getDisciplinas())
                    .email(professorRetorno.getEmail())
                    .id(professorRetorno.getId())
                    .nome(professorRetorno.getNome())
                    .titulacao(professorRetorno.getTitulacao()).build();
        }
        throw new PessoaException("Não foi possivel encontrar o professor com o id: " + cmd.getId());

    }

}