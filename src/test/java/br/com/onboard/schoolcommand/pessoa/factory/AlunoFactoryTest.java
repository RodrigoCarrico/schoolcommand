package br.com.onboard.schoolcommand.pessoa.factory;

import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.repository.AlunoRepository;
import br.com.onboard.schoolcommand.utils.GeneratedUUID;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AlunoFactoryTest {
    
    private final static String nome = "Teste Core aluno";
    private final static String email = "testecore@teste.com.br";
    private final static Integer matricula = 123456;
    private final static FormaIngresso formaIngresso = FormaIngresso.VESTIBULAR;
    private final static String cpf = "00000000002";
    private final static Set<String> turmas = new HashSet<String>(Arrays.asList("1","2"));

    public static Aluno generate(){
        Aluno aluno = Aluno.builder()
                .cpf(cpf)
                .email(email)
                .formaIngresso(formaIngresso)
                .matricula(matricula)
                .nome(nome)
                .turmas(turmas)
                .build();
        return aluno;
    }

    public static void saveDB(Aluno aluno, AlunoRepository repository){
        repository.save(aluno);
    }
}
