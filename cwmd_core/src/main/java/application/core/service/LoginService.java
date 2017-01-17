package application.core.service;

import application.core.model.User;
import application.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for login
 */
@Service
@Transactional
public class LoginService {
    @Autowired
    private UserRepository userRepo;

    /**
     * Returns the user with given username and password or null otherwise
     * @param username the username
     * @param password the password
     * @return The existing {@link User} or null otherwise.
     */
    public User login(String username, String password) {

        List<User> users = userRepo.findAll();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}


