package br.com.onboard.schoolcommand.disciplina.api;

import br.com.onboard.schoolcommand.disciplina.application.DisciplinaService;
import br.com.onboard.schoolcommand.disciplina.application.command.CriarDisciplinaCommand;
import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolcommand.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DisciplinaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DisciplinaService disciplinaService;

    @Test
    @DisplayName("Criação de Disicplina core")
    void deveCriarDisciplina() throws Exception {

        String professorId = "1";
        Integer cargaHoraria = 100;
        String descricao = "Matematica";
        String sigla = "M";

        var dto = DisciplinaDto.builder()
                .professorId(professorId)
                .cargaHoraria(cargaHoraria)
                .descricao(descricao)
                .sigla(sigla).build();

        var cmd = CriarDisciplinaCommand.builder()
                .cargaHoraria(dto.getCargaHoraria())
                .descricao(dto.getDescricao())
                .professorId(dto.getProfessorId())
                .sigla(dto.getSigla())
                .build();

        DisciplinaDto disciplinaDto = DisciplinaDto.builder()
                .id(dto.getId())
                .cargaHoraria(dto.getCargaHoraria())
                .descricao(dto.getDescricao())
                .professorId(dto.getProfessorId())
                .sigla(dto.getSigla())
                .build();

        Mockito.when(disciplinaService.handler(cmd)).thenReturn(disciplinaDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST,
                DisciplinaController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(dto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao", CoreMatchers.is(dto.getDescricao())));

        // then

        Mockito.verify(disciplinaService).handler(cmd);

        assertThat(dto.getCargaHoraria()).isEqualTo(cmd.getCargaHoraria());
        assertThat(dto.getDescricao()).isEqualTo(cmd.getDescricao());
        assertThat(dto.getProfessorId()).isEqualTo(cmd.getProfessorId());
        assertThat(dto.getSigla()).isEqualTo(cmd.getSigla());
    }

}