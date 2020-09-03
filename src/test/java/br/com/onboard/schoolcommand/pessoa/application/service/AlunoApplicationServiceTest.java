package br.com.onboard.schoolcommand.pessoa.application.service;

import br.com.onboard.schoolcommand.config.amqp.SCHOOLPublisher;
import br.com.onboard.schoolcommand.pessoa.api.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.AlteraAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.CriarAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.exception.PessoaException;
import br.com.onboard.schoolcommand.pessoa.factory.AlunoFactory;
import br.com.onboard.schoolcommand.pessoa.repository.AlunoRepository;
import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class AlunoApplicationServiceTest {
    @Autowired
    AlunoRepository alunoRepository;

    @SpyBean
    SCHOOLPublisher publisher;

    @Autowired
    AlunoApplicationService alunoApplicationService;

    private final String id = UUID.randomUUID().toString();
    private final String nome = "Teste Application Aluno";
    private final String email = "testealuno@teste.com.br";
    private final String cpf = "12312312312";
    private final Integer matricula = 1;
    private final FormaIngresso formaIngresso = FormaIngresso.ENADE;
    private final Set<String> turmas = new HashSet<>(Arrays.asList("1", "2"));

    @Test
    @DisplayName("Teste Evento de Criação de Aluno")
    void testHandle() {
        var cmd = CriarAlunoCommand.builder()
                .cpf(cpf)
                .email(email)
                .formaIngresso(formaIngresso)
                .matricula(matricula)
                .nome(nome)
                .turmas(turmas)
                .build();


        AlunoDto result = alunoApplicationService.handle(cmd);

        var eventArgument = ArgumentCaptor.forClass(DomainCommandEvents.class);
        Mockito.verify(publisher).dispatch(eventArgument.capture());

        Optional<Aluno> alunoFinded = alunoRepository.findById(result.getId());

        assertThat(alunoFinded.get().getId()).isEqualTo(result.getId());
        assertThat(alunoFinded.get().getCpf()).isEqualTo(result.getCpf());
        assertThat(alunoFinded.get().getNome()).isEqualTo(result.getNome());
        assertThat(alunoFinded.get().getEmail()).isEqualTo(result.getEmail());
        assertThat(alunoFinded.get().getMatricula()).isEqualTo(result.getMatricula());
        assertThat(alunoFinded.get().getFormaIngresso()).isEqualTo(result.getFormaIngresso());
        assertThat(alunoFinded.get().getTurmas().size()).isNotZero();

    }

    @Test
    @DisplayName("Teste Evento de Alteração de Aluno")
    void testHandle2() {
        Aluno aluno = AlunoFactory.generate();
        AlunoFactory.saveDB(aluno, alunoRepository);
        String cpfAltera = "12312312345";
        String nomeAltera = "Teste de alteração";
        String emailAltera = "altera@teste.com.br";
        FormaIngresso formaIngressoAltera = FormaIngresso.ENADE;
        Integer matriculaAltera = 345;
        Set<String> turmasAltera = new HashSet<>(Arrays.asList("1"));

        var cmd = AlteraAlunoCommand.builder()
                .cpf(cpfAltera)
                .nome(nomeAltera)
                .email(emailAltera)
                .formaIngresso(formaIngressoAltera)
                .matricula(matriculaAltera)
                .turmas(turmasAltera)
                .build();

        AlunoDto result = alunoApplicationService.handle(aluno.getId(), cmd);

        var eventArgument = ArgumentCaptor.forClass(DomainCommandEvents.class);
        Mockito.verify(publisher).dispatch(eventArgument.capture());

        assertThat(cpfAltera).isEqualTo(result.getCpf());
        assertThat(nomeAltera).isEqualTo(result.getNome());
        assertThat(emailAltera).isEqualTo(result.getEmail());
        assertThat(matriculaAltera).isEqualTo(result.getMatricula());
        assertThat(formaIngressoAltera).isEqualTo(result.getFormaIngresso());
        assertThat(turmasAltera.size()).isEqualTo(result.getTurmas().size());
    }

    @Test
    @DisplayName("Testando Erro excecao alteracao")
    void testanErroExcecaoAlteracao() {

        Aluno aluno = AlunoFactory.generate();

        var cmd = AlteraAlunoCommand.builder()
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .formaIngresso(aluno.getFormaIngresso())
                .matricula(aluno.getMatricula())
                .turmas((Set) aluno.getTurmas())
                .build();

        // when
        var thrown = catchThrowable(() -> alunoApplicationService.handle(aluno.getId(), cmd));

        // then
        assertThat(thrown).isInstanceOf(PessoaException.class);


    }
}