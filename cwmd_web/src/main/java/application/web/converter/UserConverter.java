package application.web.converter;

import application.core.model.User;
import application.core.model.UserDetails;
import application.core.service.IUserService;
import application.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public class UserConverter extends Converter<User, UserDto> {

    @Autowired
    private IUserService userService;

    @Override
    public UserDto toDTO(User user) {
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
//                .department(userDetail) TODO: get user department
                .build();
    }

    @Override
    public User fromDTO(UserDto userDto) {
        return userService.readOne(userDto.getUsername());
    }
}
