package casadocodigo.controllers.form.autor;

import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AutorForm {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "{field.validation.email.duplicated}")
    private String email;

    @NotEmpty
    @Length(max = 400)
    private String descricao;

    public AutorForm(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converter() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
