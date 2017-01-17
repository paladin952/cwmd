package application.web.converter;

import application.core.model.User;
import application.core.model.UserDetails;
import application.core.service.DepartmentUserService;
import application.core.service.IUserService;
import application.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converting {@link User} to {@link UserDto} or the other way around
 */
@Component
public class UserConverter extends Converter<User, UserDto> {

    @Autowired
    private IUserService userService;

    @Autowired
    private DepartmentUserService departmentUserService;

    /**
     * Converting to DTO
     * @param user The user
     * @return a new DTO object
     */
    @Override
    public UserDto toDTO(User user) {
        String departmentName = departmentUserService.getUserDepartmentName(user);

        UserDetails userDetails = user.getUserInfo() == null ? new UserDetails() : user.getUserInfo();
        return UserDto.builder()
                .username(user.getUsername())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .departmentChief(userDetails.getIsDepartmentChief())
                .address(userDetails.getAddress())
                .phoneNumber(userDetails.getPhoneNumber())
                .role(user.getRole().name())
                .department(departmentName) //TODO: get user department
                .build();
    }

    /**
     * Converting to User object
     * @param userDto The DTO as input
     * @return a new User Object
     */
    @Override
    public User fromDTO(UserDto userDto) {
        return userService.readOne(userDto.getUsername());
    }
}
