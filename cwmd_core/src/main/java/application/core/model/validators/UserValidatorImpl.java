package application.core.model.validators;

import application.core.model.User;
import application.core.model.validators.exceptions.ValidatorException;

public class UserValidatorImpl implements IValidator<User> {
    @Override
    public IValidator<User> validate(User entity) throws ValidatorException {
        if (entity.getUsername().equals("")) throw new ValidatorException("Username is null");
        if (entity.getPassword().equals("")) throw new ValidatorException("Password is null");
//        if (entity.getUserInfo() == null) throw new ValidatorException("User has no details");

        return this;
    }
}
