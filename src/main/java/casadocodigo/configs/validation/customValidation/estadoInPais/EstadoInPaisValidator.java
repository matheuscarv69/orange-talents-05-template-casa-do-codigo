package casadocodigo.configs.validation.customValidation.estadoInPais;

import casadocodigo.controllers.form.localidade.LocalidadeForm;
import casadocodigo.entities.Estado;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EstadoInPaisValidator implements ConstraintValidator<EstadoInPais, LocalidadeForm> {

    @Autowired
    private EntityManager manager;

    @Override
    public void initialize(EstadoInPais constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalidadeForm localidadeForm, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select e from Estado e where e.id = :pIdEstado and e.pais.id = :pIdPais");
        query.setParameter("pIdEstado", localidadeForm.getIdEstado());
        query.setParameter("pIdPais", localidadeForm.getIdPais());

        List<?> list = query.getResultList();

        boolean entityExists = !list.isEmpty();

        return entityExists;
    }
}
