package br.com.onboard.schoolcommand.disciplina.model;

import br.com.onboard.schoolcommand.disciplina.model.event.DisciplinaCriadaEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DisciplinaTest {

    @Test
    @DisplayName("Teste Criação Disciplina")
    void deveTestarCriacaoDisciplina() {
        String professorId = "1";
        Integer cargaHoraria = 100;
        String descricao = "Matematica";
        String sigla = "M";

        Disciplina disciplina = Disciplina.builder()
                .professorId(professorId)
                .cargaHoraria(cargaHoraria)
                .descricao(descricao)
                .sigla(sigla).build();
        var event = (DisciplinaCriadaEvent) disciplina.getEvents().get(0);

        assertThat(disciplina).isNotNull();
        assertThat(disciplina.getCargaHoraria()).isNotNull();
        assertThat(disciplina.getDescricao()).isNotNull();
        assertThat(disciplina.getId()).isNotNull();
        assertThat(disciplina.getProfessorId()).isNotNull();
        assertThat(disciplina.getSigla()).isNotNull();
        assertThat(disciplina.getEvents().size()).isNotZero();


        assertThat(event.getCargaHoraria()).isEqualTo(disciplina.getCargaHoraria());
        assertThat(event.getDescricao()).isEqualTo(disciplina.getDescricao());
        assertThat(event.getId()).isEqualTo(disciplina.getId());
        assertThat(event.getProfessorId()).isEqualTo(disciplina.getProfessorId());
        assertThat(event.getSigla()).isEqualTo(disciplina.getSigla());

    }

}