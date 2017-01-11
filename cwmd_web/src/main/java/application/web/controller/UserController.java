package application.web.controller;

import application.core.model.Department;
import application.core.model.User;
import application.core.model.UserDetails;
import application.core.service.DepartmentService;
import application.core.service.DepartmentUserService;
import application.core.service.IUserService;
import application.core.utils.enums.RoleType;
import application.web.converter.UserConverter;
import application.web.dto.UserDto;
import application.web.misc.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.JSON_API)
    public ResponseEntity<List<UserDto>> getAll() {
        try {
            List<User> users = userService.read();
            List<UserDto> userDtos = new ArrayList<>();

            for (User user : users) {
                UserDto userDto = userConverter.toDTO(user);
                userDtos.add(userDto);
            }
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@RequestBody UserDto userDto) {
        RoleType roleType;
        switch (userDto.getRole()) {
            case "admin":
                roleType = RoleType.ROLE_ADMIN;
                break;
            case "manager":
                roleType = RoleType.ROLE_MANAGER;
                break;
            case "contributor":
                roleType = RoleType.ROLE_CONTRIBUTOR;
                break;
            case "reader":
                roleType = RoleType.ROLE_READER;
                break;
            default:
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

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
