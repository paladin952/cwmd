package application.web.controller;

import application.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

//    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.JSON_API)
//    public List<User> getAll() {
//        return userService.read();
//    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.JSON_API)
//    public User update(@RequestBody User user) {
//        return userService.update(user.getUsername(), user.getPassword(), user.getRole(), user.getUserInfo());
//    }
}
