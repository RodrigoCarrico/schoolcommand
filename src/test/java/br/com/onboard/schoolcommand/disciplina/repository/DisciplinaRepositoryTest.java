package br.com.onboard.schoolcommand.disciplina.repository;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.repository.ProfessorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DisciplinaRepositoryTest {

    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Test
    @DisplayName("Teste de alteração no disciplina repository")
    public void testeDeAlteraçaoRepository(){
        Disciplina d = Disciplina.builder().id("5f4a390aa4ee473b37052b6d").cargaHoraria(100).descricao("Matematica").sigla("M").build();

        Disciplina disciplina = disciplinaRepository.save(d);

        assertThat(disciplina).isNotNull();
    }
}
