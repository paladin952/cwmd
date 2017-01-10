package application.web.converter;

import application.core.model.User;
import application.core.service.IUserService;
import application.web.dto.LightUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LightUserConverter extends Converter<User, LightUserDto> {

    @Autowired
    private IUserService userService;

    @Override
    public LightUserDto toDTO(User user) {
        return LightUserDto.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public User fromDTO(LightUserDto userDto) {
        return userService.readOne(userDto.getUsername());
    }
}
