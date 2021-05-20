package casadocodigo.controllers.form.pais;

import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotEmpty;

public class PaisForm {

    @NotEmpty
    @UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "{field.validation.pais.duplicated}")
    private String nome;

    // Para o jackson conseguir parsear o json para entidade
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaisForm(String nome) {
        this.nome = nome;
    }

    public Pais converter() {
        return new Pais(this.nome);
    }
}
