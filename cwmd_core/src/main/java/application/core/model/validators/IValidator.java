package application.core.model.validators;

import application.core.model.validators.exceptions.ValidatorException;

/**
 * Contract for all validators
 * @param <T> The type
 */
public interface IValidator<T> {
    IValidator<T> validate(T entity) throws ValidatorException;
}
