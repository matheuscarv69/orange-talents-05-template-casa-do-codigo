package casadocodigo.controllers.form.estado;

import casadocodigo.configs.validation.customValidation.exists.ExistsId;
import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Estado;
import casadocodigo.entities.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstadoForm {

    @NotEmpty
    @UniqueValue(domainClass = Estado.class, fieldName = "nome", message = "{field.validation.estado.duplicated}")
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class)
    @Positive
    private Long idPais;

    public EstadoForm(String nome, Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado converter(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, this.idPais);
        return new Estado(nome, pais);
    }

}
