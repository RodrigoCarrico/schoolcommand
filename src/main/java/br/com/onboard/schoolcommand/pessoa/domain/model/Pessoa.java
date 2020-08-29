package br.com.onboard.schoolcommand.pessoa.domain.model;

import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import br.com.onboard.schoolcommand.utils.GenerateUUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public abstract class Pessoa extends DomainCommandEvents {

    @Id
    private String id;
    @NotNull
    @Length(min = 1, max = 255)
    private String nome;
    @NotNull
    @Length(min = 1, max = 100)
    private String email;
    @NotNull
    @Length(min = 1, max = 12)
    private String cpf;

    public Pessoa(String id, @NotNull @Length(min = 1, max = 255) String nome, @NotNull @Length(min = 1, max = 100) String email, @NotNull @Length(min = 1, max = 12) String cpf) {
       // this.id = GenerateUUID.generate();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
}
