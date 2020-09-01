package br.com.onboard.schoolcommand.pessoa.application.service;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.pessoa.api.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.api.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.AlteraAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.CriarAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.exception.PessoaException;
import br.com.onboard.schoolcommand.pessoa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoApplicationService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    SCHOOLPublisher publisher;

    @Transactional
    public AlunoDto handle(@Valid CriarAlunoCommand cmd) {
        Aluno aluno = Aluno.builder().cpf(cmd.getCpf())
                .email(cmd.getEmail())
                .formaIngresso(cmd.getFormaIngresso())
                .matricula(cmd.getMatricula())
                .nome(cmd.getNome())
                .turmas(cmd.getTurmas())
                .build();

        Aluno alunoRetorno = alunoRepository.save(aluno);

        publisher.dispach(alunoRetorno);

        return AlunoDto.builder()
                .cpf(alunoRetorno.getCpf())
                .turmas((Set<String>) alunoRetorno.getTurmas())
                .email(alunoRetorno.getEmail())
                .id(alunoRetorno.getId())
                .nome(alunoRetorno.getNome())
                .formaIngresso(alunoRetorno.getFormaIngresso())
                .matricula(alunoRetorno.getMatricula()).build();

    }

    @Transactional
    public AlunoDto handle(String id, @Valid AlteraAlunoCommand cmd) {
        Optional<Aluno> optional = alunoRepository.findById(id);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            aluno.altera(cmd.getId(), cmd.getNome(), cmd.getEmail(), cmd.getCpf(),cmd.getMatricula(),cmd.getFormaIngresso(),cmd.getTurmas());

            Aluno alunoRetorno = alunoRepository.save(aluno);

            publisher.dispach(aluno);

            return AlunoDto.builder().cpf(alunoRetorno.getCpf())
                    .turmas((Set<String>) alunoRetorno.getTurmas())
                    .email(alunoRetorno.getEmail())
                    .id(alunoRetorno.getId())
                    .nome(alunoRetorno.getNome())
                    .formaIngresso(alunoRetorno.getFormaIngresso())
                    .matricula(alunoRetorno.getMatricula())
                    .build();
        }
        throw new PessoaException("Não foi possivel encontrar o Aluno com o id: " + id);
    }

}
