package application.core.validator;

import application.core.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for {@link User}
 */
@Component
public class UserValidator implements Validator {

    /**
     * Checks if the Validator can validate a Class
     *
     * @param clazz The class to be validated
     * @return True if it can be validated by this validator, False otherwise
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    /**
     * Validate a given object
     *
     * @param target The target class to be validated
     */
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        validateUsername(user, errors);
        validatePassword(user, errors);
    }

    /**
     * Validates Usernamae for a user
     *
     * @param user The user to be validated
     */
    private void validateUsername(User user, Errors errors) {
        String name = user.getUsername();
        if (name.length() < 5 || name.length() > 255) {
            errors.rejectValue("username", "user.username.size", "*  The username is of invalid size.");
        }
    }

    /**
     * Validates Password for a user
     *
     * @param user The user to be validated
     */
    private void validatePassword(User user, Errors errors) {
        String password = user.getPassword();
        if (password.length() < 5 || password.length() > 255) {
            errors.rejectValue("password", "user.password.size", "*  The password is of invalid size.");
        }
    }
}
