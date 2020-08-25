package br.com.onboard.schoolcommand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.onboard.schoolcommand.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolcommand.pessoa.domain.enums.Titulacao;
import br.com.onboard.schoolcommand.pessoa.domain.model.Professor;
import br.com.onboard.schoolcommand.pessoa.service.ProfessorService;
import br.com.onboard.schoolcommand.utils.GenerateUUID;

@SpringBootTest
class ProfessorTests {
	@Autowired
	ProfessorService professoService;

	@Test
	@DisplayName("Deve executar a criação de um novo ID")
	public void contextLoads() {
		String id = GenerateUUID.generate();
		assertThat(id).isNotBlank();
	}

	@Test
	@DisplayName("Criando nova classe professor")
	public void CriaNovaClasseProfessor() {
		Professor professor = new Professor("Laura", "laura@teste.com.br", "000000000000", Titulacao.MESTRE);
		assertThat(professor).isNotNull();
	}

	@Test
	@DisplayName("Teste alteração professor")
	public void AlteraProfessorTeste() {
		ProfessorDto professorDto = new ProfessorDto(null, "rodrigo.carrico", "rodrigo@teste.com.br", "11111111111",
				Titulacao.DOUTOR, null);
		professoService.atualizar("ea7947cd344d4343864c8ceb0ad2e174", professorDto);
		assertThat(professorDto.getEmail()).isEqualTo("rodrigo@teste.com.br");
	}

	@Test
	@DisplayName("Teste de conversão Objeto professor para json")
	public void conversaoDoProfessorParaJsonString() {
		Professor professor = new Professor("teste teste", "teste@teste.com.br", "00000000000", Titulacao.MESTRE);
		String value = ProfessorDto.toGsonString(professor);
		System.out.println(value);
		assertThat(value).isNotBlank();
	}
}
