package br.com.onboard.schoolcommand.pessoa.api;

import br.com.onboard.schoolcommand.pessoa.api.dto.AlunoDto;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.AlteraAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.application.command.aluno.CriarAlunoCommand;
import br.com.onboard.schoolcommand.pessoa.application.service.AlunoApplicationService;
import br.com.onboard.schoolcommand.pessoa.domain.enums.FormaIngresso;
import br.com.onboard.schoolcommand.pessoa.domain.model.Aluno;
import br.com.onboard.schoolcommand.pessoa.repository.AlunoRepository;
import br.com.onboard.schoolcommand.utils.GeneratedUUID;
import br.com.onboard.schoolcommand.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
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
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AlunoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AlunoApplicationService alunoApplicationService;

    private final String id = GeneratedUUID.getUUID();
    private final String nome = "Teste Core aluno";
    private final String email = "testecore@teste.com.br";
    private final Integer matricula = 1234;
    private final FormaIngresso formaIngresso = FormaIngresso.ENADE;
    private final String cpf = "00000000001";
    private final Set<String> turmas = new HashSet<>(Arrays.asList("1"));

    @Test
    @DisplayName("Criação de Aluno core")
    void CriacaoDeAlunoCoreController() throws Exception {
        var dto = AlunoDto.builder()
                .cpf(cpf)
                .email(email)
                .matricula(matricula)
                .formaIngresso(formaIngresso)
                .nome(nome)
                .turmas(turmas)
                .build();

        var cmd = CriarAlunoCommand.builder()
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .matricula(dto.getMatricula())
                .formaIngresso(dto.getFormaIngresso())
                .nome(dto.getNome())
                .turmas(dto.getTurmas())
                .build();

        AlunoDto alunoDto = AlunoDto.builder().
                id(id)
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .matricula(dto.getMatricula())
                .formaIngresso(dto.getFormaIngresso())
                .nome(dto.getNome())
                .turmas(dto.getTurmas())
                .build();

        Mockito.when(alunoApplicationService.handle(cmd)).thenReturn(alunoDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST,
                AlunoController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(dto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", CoreMatchers.is(nome)));

        // then

        Mockito.verify(alunoApplicationService).handle(cmd);
    }

    @Test
    @DisplayName("Alteracao de Aluno core")
    void AlteracaoDeAlunoCoreController() throws Exception {
        var dto = AlunoDto.builder()
                .cpf(cpf)
                .email(email)
                .matricula(matricula)
                .formaIngresso(formaIngresso)
                .nome(nome)
                .turmas(turmas)
                .build();

        var cmd = AlteraAlunoCommand.builder()
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .matricula(dto.getMatricula())
                .formaIngresso(dto.getFormaIngresso())
                .nome(dto.getNome())
                .turmas(dto.getTurmas())
                .build();

        AlunoDto alunoDto = AlunoDto.builder().
                id(id)
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .matricula(dto.getMatricula())
                .formaIngresso(dto.getFormaIngresso())
                .nome(dto.getNome())
                .turmas(dto.getTurmas())
                .build();

        Mockito.when(alunoApplicationService.handle(id, cmd)).thenReturn(alunoDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT,
                AlunoController.PATH + "/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(dto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        // then
        Mockito.verify(alunoApplicationService).handle(id, cmd);
    }
}
