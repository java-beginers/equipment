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
        return currentUser != null && UserRole.ROLE_ADMIN.equals(currentUser.getRole());
    }
}
