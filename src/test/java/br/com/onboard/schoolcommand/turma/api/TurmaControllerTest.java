package br.com.onboard.schoolcommand.turma.api;

import br.com.onboard.schoolcommand.turma.application.TurmaService;
import br.com.onboard.schoolcommand.turma.application.command.CriaTurmaCommand;
import br.com.onboard.schoolcommand.turma.dto.TurmaDto;
import br.com.onboard.schoolcommand.utils.GeneratedUUID;
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

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class TurmaControllerTest {
    @MockBean
    TurmaService turmaService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Criação de Turma")
    void deveCriarTurma() throws Exception {
        String id = GeneratedUUID.getUUID();
        Integer anoLetivo = 2020;
        String descricao = "MATUTINO";
        Integer numeroVagas = 60;
        Integer periodoLetivo = 1;

        var dto = TurmaDto.builder()
                .anoLetivo(anoLetivo)
                .descricao(descricao)
                .disciplinas(new HashSet<>(Arrays.asList("1", "2")))
                .numeroVagas(numeroVagas)
                .periodoLetivo(periodoLetivo)
                .build();

        var cmd = CriaTurmaCommand.builder()
                .anoLetivo(dto.getAnoLetivo())
                .descricao(dto.getDescricao())
                .disciplinas(dto.getDisciplinas())
                .numeroVagas(dto.getNumeroVagas())
                .periodoLetivo(dto.getPeriodoLetivo())
                .build();

        TurmaDto turmaDto = TurmaDto.builder()
                .id(id)
                .periodoLetivo(dto.getPeriodoLetivo())
                .numeroVagas(dto.getNumeroVagas())
                .disciplinas(dto.getDisciplinas())
                .descricao(dto.getDescricao())
                .anoLetivo(dto.getAnoLetivo())
                .build();

       Mockito.when(turmaService.handle(cmd)).thenReturn(turmaDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST,
                TurmaController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(dto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.periodoLetivo", CoreMatchers.is(dto.getPeriodoLetivo())));

        // then
        Mockito.verify(turmaService).handle(cmd);

        assertThat(dto.getAnoLetivo()).isEqualTo(cmd.getAnoLetivo());
        assertThat(dto.getDescricao()).isEqualTo(cmd.getDescricao());
        assertThat(dto.getNumeroVagas()).isEqualTo(cmd.getNumeroVagas());
        assertThat(dto.getPeriodoLetivo()).isEqualTo(cmd.getPeriodoLetivo());
    }

}