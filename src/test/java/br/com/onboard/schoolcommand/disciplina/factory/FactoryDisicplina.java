package br.com.onboard.schoolcommand.disciplina.factory;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import br.com.onboard.schoolcommand.disciplina.repository.DisciplinaRepository;

public class FactoryDisicplina {

    private final static String sigla = "M";
    private final static String descricao = "Matematica";
    private final static Integer cargaHoraria = 200;
    private final static String professorId = "1";


    public static Disciplina generate() {
        Disciplina disciplina = Disciplina.builder()
                .sigla(sigla)
                .descricao(descricao)
                .cargaHoraria(cargaHoraria)
                .professorId(professorId)
                .build();

        return disciplina;
    }

    public static void saveDB(Disciplina disciplina, DisciplinaRepository repository) {
        repository.save(disciplina);
    }
}
