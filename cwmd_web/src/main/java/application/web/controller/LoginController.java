package application.web.controller;

import application.core.model.User;
import application.core.service.IUserService;
import application.core.validator.UserValidator;
import application.core.utils.UserUtil;
import application.web.misc.ViewPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    // TODO: 03.12.2016 check if this method is still needed
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView(ViewPath.LOGIN, "user", new User());
    }

    @RequestMapping(method = RequestMethod.POST)
    public User login(@RequestBody @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            // TODO: 03.12.2016 if has errors send information about the errors (use messages)
            return new User();
//            return ViewPath.LOGIN;

        } else {
            User loggedUser = userService.readOne(user.getUsername());
            UserUtil.setCurrentUser(loggedUser);
            return loggedUser;
        }
    }

    @RequestMapping(value = "/dummy", method = RequestMethod.GET)
    public String dummyMethod(int someInt) {
        return "The int is: " + someInt;
    }

}
