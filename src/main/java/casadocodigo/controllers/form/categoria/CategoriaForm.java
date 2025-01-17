package casadocodigo.controllers.form.categoria;

import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Categoria;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotEmpty;

public class CategoriaForm {

    @NotEmpty
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "{field.validation.category.duplicated}")
    private String nome;

    // Para o jackson conseguir parsear o json para entidade
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public Categoria converter() {
        return new Categoria(this.nome);
    }
}
