package br.com.onboard.schoolcommand.disciplina.dto;

import br.com.onboard.schoolcommand.disciplina.model.Disciplina;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class DisciplinaDto {

    private String id;
    private String descricao;
    private String sigla;
    private Integer cargaHoraria;

    public DisciplinaDto(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.descricao = disciplina.getDescricao();
        this.sigla = disciplina.getSigla();
        this.cargaHoraria = disciplina.getCargaHoraria();
    }

    public static List<DisciplinaDto> converterList(List<Disciplina> disciplinas) {
        return disciplinas.stream().map(DisciplinaDto::new).collect(Collectors.toList());
    }

    public Disciplina converter() {
        return new Disciplina().builder().cargaHoraria(this.cargaHoraria).descricao(this.descricao).sigla(this.sigla).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaDto that = (DisciplinaDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(sigla, that.sigla) &&
                Objects.equals(cargaHoraria, that.cargaHoraria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, sigla, cargaHoraria);
    }
}
