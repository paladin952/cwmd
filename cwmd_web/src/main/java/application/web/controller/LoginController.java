package application.web.controller;

import application.core.model.User;
import application.core.service.LoginService;
import application.web.converter.LightUserConverter;
import application.web.dto.LightUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LightUserConverter lightUserConverter;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LightUserDto login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        User loggedUser = loginService.login(username, password);
        if (loggedUser != null) {
            Cookie userName = new Cookie("username", username);
            userName.setMaxAge(300*60);
            response.addCookie(userName);
            return lightUserConverter.toDTO(loggedUser);
        }
        return null;
    }
}
