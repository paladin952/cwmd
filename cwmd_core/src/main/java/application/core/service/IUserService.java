package application.core.service;

import application.core.model.User;
import application.core.model.UserDetails;
import application.core.utils.enums.RoleType;

import java.util.List;

public interface IUserService {
    User create(String username, String password, RoleType role, UserDetails userInfo);
    List<User> read();
    User readOne(String username);
    User update(String username, String password, RoleType role, UserDetails userInfo);
    IUserService delete(String username);
}
