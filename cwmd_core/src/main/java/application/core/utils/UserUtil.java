package application.core.utils;

import application.core.model.User;
import application.core.utils.enums.RoleType;

public class UserUtil {
    // TODO: 05.12.2016 remove this and set the current user on login
    private static User currentUser = new User("admin", "admin", RoleType.ROLE_ADMIN, null);
//    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
