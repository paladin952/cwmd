package application.core.utils;

import application.core.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Utility class for {@link User}
 */
public class UserUtil {

    /**
     * Returns the username of the user making the request.
     * @return The username of the user
     */
    public static String getCurrentUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<Cookie> cookieList = Arrays.asList(cookies);
        Optional<Cookie> cookieOptional = cookieList.stream().filter(cookie -> Objects.equals(cookie.getName(), "username")).findFirst();
        boolean cookieExists = cookieOptional.isPresent();
        if (cookieExists) {
            Cookie user = cookieOptional.get();
            return user.getValue();
        }
        return null;
    }
}
