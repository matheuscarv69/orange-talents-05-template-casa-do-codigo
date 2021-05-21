package casadocodigo.controllers.form.localidade;

import casadocodigo.configs.validation.customValidation.exists.ExistsId;
import casadocodigo.entities.Estado;
import casadocodigo.entities.Pais;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LocalidadeForm {

    @NotNull
    @Positive
    @ExistsId(domainClass = Pais.class, message = "{field.validation.pais.not-exists}")
    private Long idPais;

    @Positive
    @ExistsId(domainClass = Estado.class, message = "{field.validation.estado.not-exists}")
    private Long idEstado;

    public LocalidadeForm(Long idPais, Long idEstado) {
        this.idPais = idPais;
        this.idEstado = idEstado;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }
}
