package application.core.validator;

import application.core.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        validateUsername(user, errors);
        validatePassword(user, errors);
    }

    private void validateUsername(User user, Errors errors) {
        String name = user.getUsername();
        if (name.length() < 5 || name.length() > 255) {
            errors.rejectValue("username", "user.username.size", "*  The username is of invalid size.");
        }

    }

    private void validatePassword(User user, Errors errors) {
        String password = user.getPassword();
        if (password.length() < 5 || password.length() > 255) {
            errors.rejectValue("password", "user.password.size", "*  The password is of invalid size.");
        }
    }
}
