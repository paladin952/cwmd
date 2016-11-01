package application.web.controller;

import application.core.model.User;
import application.core.service.IUserService;
import application.web.misc.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

//    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.JSON_API)
//    public List<User> getAll() {
//        return userService.read();
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.JSON_API)
    public String getOne(@RequestBody User user) {
        User tmp = userService.readOne(user.getUsername());
        if (tmp != null && tmp.getPassword().equals(user.getPassword()))
            return "welcome.jsp";
        return "index.jsp";
    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.JSON_API)
//    public User update(@RequestBody User user) {
//        return userService.update(user.getUsername(), user.getPassword(), user.getRole(), user.getUserInfo());
//    }
}
