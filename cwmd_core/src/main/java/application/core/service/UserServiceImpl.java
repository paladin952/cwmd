package application.core.service;

import application.core.model.User;
import application.core.model.UserDetails;
import application.core.model.validators.IValidator;
import application.core.model.validators.UserValidatorImpl;
import application.core.model.validators.exceptions.ValidatorException;
import application.core.repository.UserRepository;
import application.core.service.exceptions.ServiceException;
import application.core.service.exceptions.ServiceHibernateException;
import application.core.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    private IValidator<User> validator = new UserValidatorImpl();

    @Autowired
    private UserRepository userRepo;

    @Override
    public User create(String username, String password, RoleType role, UserDetails userInfo) {
        try {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .role(role)
                    .userInfo(userInfo)
                    .build();
            validator.validate(user);
            return userRepo.save(user);

        } catch (ValidatorException e) {
            throw new ServiceException("Error creating a new user", e);
        } catch (Exception e) {
            throw new ServiceException("Unknown error while creating a new user", e);
        }
    }

    @Override
    public List<User> read() {
        try {
            return userRepo.findAll();
        } catch (Exception e) {
            throw new ServiceHibernateException("Unknown error while reading users", e);
        }
    }

    @Override
    public User readOne(String username) {
        try {
            return userRepo.findOne(username);
        } catch (Exception e) {
            throw new ServiceHibernateException("Unknown error while retrieving user having username " + username, e);
        }
    }

    @Override
    public User update(String username, String password, RoleType role, UserDetails userInfo) {
        try {
            User user = userRepo.findOne(username);
            user.setPassword(password);
            user.setRole(role);
            user.setUserInfo(userInfo);
            return userRepo.save(user);
        } catch (Exception e) {
            throw new ServiceHibernateException("Unknown error while updating user having username" + username, e);
        }
    }

    @Override
    public IUserService delete(String username) {
        try {
            userRepo.delete(username);
        } catch (Exception e) {
            throw new ServiceHibernateException("Uknown error while deleting user having username " + username, e);
        }

        return this;
    }
}
