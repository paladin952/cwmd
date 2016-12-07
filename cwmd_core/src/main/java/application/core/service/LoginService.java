package application.core.service;

import application.core.model.User;
import application.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by clapalucian on 12/7/16.
 */
@Service
@Transactional
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    public boolean login(String username, String password) {

        List<User> users = userRepo.findAll();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

}
