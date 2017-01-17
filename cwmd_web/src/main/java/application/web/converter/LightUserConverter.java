package application.web.converter;

import application.core.model.User;
import application.core.service.IUserService;
import application.web.dto.LightUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converting {@link User} to {@link LightUserDto} or the other way around
 */
@Component
public class LightUserConverter extends Converter<User, LightUserDto> {

    @Autowired
    private IUserService userService;

    /**
     * Converting a {@link User} to a {@link LightUserDto}
     * @param user The user as input
     * @return a new {@link LightUserDto}
     */
    @Override
    public LightUserDto toDTO(User user) {
        return LightUserDto.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    /**
     * Converting a {@link LightUserDto} to a {@link User}
     * @param userDto The userDto as input
     * @return a new {@link User}
     */
    @Override
    public User fromDTO(LightUserDto userDto) {
        return userService.readOne(userDto.getUsername());
    }
}
