package ru.web.equipment.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;

/**
 * Утилитарный класс для потребностей подсистемы безопасности.
 */
public class SecurityUtils {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CurrentUserDetails) {
                CurrentUserDetails details = (CurrentUserDetails) principal;
                return details.getUser();
            }
        }
        return null;
    }

    public static boolean isAdmin() {
        User currentUser = getCurrentUser();
        if (currentUser != null && UserRole.ROLE_ADMIN.equals(currentUser.getRole())) {
            return true;
        }
        return false;
    }

    public static boolean sameUser(User user1, User user2) {
        if (user1 == null || user2 == null) {
            return false;
        }
        return user1.getId() == user2.getId();
    }

    public static boolean isCurrentUser(User user) {
        return sameUser(getCurrentUser(), user);
    }

    public static boolean isUserInRole(UserRole role) {
        User currentUser = getCurrentUser();
        if (role != null && currentUser != null) {
            return role.equals(currentUser.getRole());
        }
        return false;
    }
}
