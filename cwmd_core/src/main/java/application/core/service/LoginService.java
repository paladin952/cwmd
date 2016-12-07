package application.core.service;

import application.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by clapalucian on 12/7/16.
 */
@Service
@Transactional
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    public boolean isLoggedIn(String username, String password) {



        return true;
    }

}
