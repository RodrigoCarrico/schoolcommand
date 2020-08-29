package br.com.onboard.schoolcommand.pessoa.repository;

import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository professorRepository;
    @Test
    @DisplayName("Teste de alteração no repository")
    public void testeDeAlteraçaoRepository(){
       /* //String id, String nome, String email, String cpf, Titulacao titulacao, Set<String> disciplinas
        Professor prof = new Professor();
        Set<String> set = new HashSet<>();
        set.add("aasdsadasdasdsadasd");
        prof.alterar("5f4970837655513f293b7037",
                "TESTE de alteração","teste@teste","999999999", Titulacao.DOUTOR,set);
        Professor profResult = professorRepository.save(prof);
        assertThat(profResult).isNotNull();*/
    }
}
