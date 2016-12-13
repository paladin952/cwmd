package application.web.controller;

import application.core.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean loggedIn = loginService.login(username, password);

        if (!loggedIn) {
            //401
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failure");
        } else {
            //200
            return ResponseEntity.ok().body("Success");
        }
    }
}
