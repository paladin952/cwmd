package application.web.controller;

import application.core.model.User;
import application.core.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam String username, @RequestParam String password) {
        User loggedUser = loginService.login(username, password);

        return loggedUser;
    }
}
