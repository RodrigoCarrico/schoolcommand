package br.com.onboard.schoolcommand.pessoa.factory;

import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import lombok.Getter;

@Getter
public class ProfessorFactoryTest {

    private final static String cpf = "00000000003";
    private final static String email = "teste3@teste3.com.br";
    private final static String nome = "teste3 de inclusao3 de professor";
    private final static Titulacao titulacao = Titulacao.PHD;


    private final static String cpf2 = "11111111112";
    private final static String email2 = "teste2@teste2.com.br";
    private final static String nome2 = "teste2 de inclusao2 de professor2";
    private final static Titulacao titulacao2 = Titulacao.MESTRE;

    public static Professor generate1() {
        return Professor.builder()
                .cpf(cpf)
                .email(email)
                .nome(nome)
                .titulacao(titulacao)
                .build();
    }

    public static Professor generate2() {
        return Professor.builder()
                .cpf(cpf2)
                .email(email2)
                .nome(nome2)
                .titulacao(titulacao2)
                .build();
    }

    public static void salvaBD(Professor professor, ProfessorRepository professorRepository) {
        professorRepository.save(professor);
    }

}
