package br.com.onboard.schoolcommand.turma.model.event;

import br.com.onboard.schoolcommand.turma.model.Turma;
import br.com.onboard.schoolcommand.utils.DomainCommand;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class TurmaCriadaEvent implements DomainCommand {
    private final String id;
    private final String descricao;
    private final Integer anoLetivo;
    private final Integer periodoLetivo;
    private final Integer numeroVagas;
    private final Set<TurmaDisciplinaCriadaEvent> disciplinas;

    public static TurmaCriadaEvent from(Turma turma) {
        return TurmaCriadaEvent.builder()
                .id(turma.getId())
                .anoLetivo(turma.getAnoLetivo())
                .descricao(turma.getDescricao())
                .disciplinas(turma.getDisciplinas()
                        .stream().map(TurmaDisciplinaCriadaEvent::from)
                        .collect(Collectors.toSet()))
                .numeroVagas(turma.getNumeroVagas())
                .periodoLetivo(turma.getPeriodoLetivo())
                .build();
    }

    @Data
    @AllArgsConstructor(staticName = "from")
    public static final class TurmaDisciplinaCriadaEvent {
        private final String disciplinaId;
    }
}
