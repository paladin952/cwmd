package application.web.controller;

import application.core.model.Department;
import application.core.model.User;
import application.core.model.UserDetails;
import application.core.service.DepartmentService;
import application.core.service.DepartmentUserService;
import application.core.service.IUserService;
import application.core.utils.enums.RoleType;
import application.core.utils.logging.Log;
import application.web.converter.UserConverter;
import application.web.dto.UserDto;
import application.web.misc.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controler for User related stuff
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentUserService departmentUserService;

    @Autowired
    private Log log;

    /**
     * Get all users
     * @return The list of users
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.JSON_API)
    public ResponseEntity<List<UserDto>> getAll() {
        try {
            log.info(UserController.class.getSimpleName(), "Retrieving the user list");

            List<User> users = userService.read();
            List<UserDto> userDtos = new ArrayList<>();

            for (User user : users) {
                UserDto userDto = userConverter.toDTO(user);
                userDtos.add(userDto);
            }
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error(UserController.class.getSimpleName(), "Error while retrieving the user list", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add a new user
     * @param userDto The user data
     * @return True is it is created, false otherwise
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@RequestBody UserDto userDto) {
        RoleType roleType;
        switch (userDto.getRole()) {
            case "ROLE_ADMIN":
                roleType = RoleType.ROLE_ADMIN;
                break;
            case "ROLE_MANAGER":
                roleType = RoleType.ROLE_MANAGER;
                break;
            case "ROLE_CONTRIBUTOR":
                roleType = RoleType.ROLE_CONTRIBUTOR;
                break;
            case "ROLE_READER":
                roleType = RoleType.ROLE_READER;
                break;
            default:
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        log.info(UserController.class.getSimpleName(), "Creating a new user");

        UserDetails userDetails = new UserDetails();
        userDetails.setAddress(userDto.getAddress());
        userDetails.setEmail(userDto.getEmail());
        userDetails.setFirstName(userDto.getFirstName());
        userDetails.setIsDepartmentChief(userDto.getDepartmentChief());
        userDetails.setLastName(userDto.getLastName());
        userDetails.setPhoneNumber(userDto.getPhoneNumber());

        User user = userService.create(userDto.getUsername(), userDto.getPassword(), roleType, userDetails);
        Department department = departmentService.getDepartmentByName(userDto.getDepartment());

        departmentUserService.addDepartmentUser(department, user);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.JSON_API)
//    public User update(@RequestBody User user) {
//        return userService.update(user.getUsername(), user.getPassword(), user.getRole(), user.getUserInfo());
//    }
}
