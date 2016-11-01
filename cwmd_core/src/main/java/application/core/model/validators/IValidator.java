package application.core.model.validators;

import application.core.model.validators.exceptions.ValidatorException;

public interface IValidator<T> {
    IValidator<T> validate(T entity) throws ValidatorException;
}
