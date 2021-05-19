package casadocodigo.configs.validation.customValidation.exists;

import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsValidator implements ConstraintValidator<Exist, Object> {

    private String domainAttribute;
    private Class<?> aClass;

    @Autowired
    private EntityManager manager;

    @Override
    public void initialize(Exist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        domainAttribute = constraintAnnotation.fieldName();
        aClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " + aClass.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        boolean entityExists = !list.isEmpty();

        return entityExists;
    }
}