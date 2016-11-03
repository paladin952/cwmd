package application.web.controller;

import application.core.model.User;
import application.core.service.IUserService;
import application.core.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final String LOGIN_PATH="common/login/login";


    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView(LOGIN_PATH, "user", new User());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return LOGIN_PATH;

        } else {
            User tmp = userService.readOne(user.getUsername());
            if (tmp != null && tmp.getPassword().equals(user.getPassword()))
                return "welcome";
            model.addAttribute("error", "Authentication failed. Please check your credentials.");
            return LOGIN_PATH;
        }
    }

}
