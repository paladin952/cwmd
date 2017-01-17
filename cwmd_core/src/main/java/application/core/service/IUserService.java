package application.core.service;

import application.core.model.User;
import application.core.model.UserDetails;
import application.core.utils.enums.RoleType;

import java.util.List;

/**
 * Inteface for the User Service
 */
public interface IUserService {

    /**
     * Creates a user with the given data.
     * @param username The username for the user.
     * @param password The password for the user.
     * @param role The role of the user.
     * @param userInfo The user information.
     * @return The newly created user.
     */
    User create(String username, String password, RoleType role, UserDetails userInfo);

    /**
     * Returns all users
     * @return A list of {@link User}
     */
    List<User> read();

    /**
     * Returns a user by username.
     * @param username the username.
     * @return A {@link User} with the given username.
     */
    User readOne(String username);

    /**
     * Updates a user with new information
     * @param username the username.
     * @param password the new password.
     * @param role the new user role.
     * @param userInfo the new user information.
     * @return The updated {@link User}.
     */
    User update(String username, String password, RoleType role, UserDetails userInfo);

    /**
     * Delets a User by username.
     * @param username The username of the user to be deleted.
     */
    IUserService delete(String username);
}
