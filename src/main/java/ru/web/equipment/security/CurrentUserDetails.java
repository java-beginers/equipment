package ru.web.equipment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.web.equipment.entity.User;

import java.util.Collection;
import java.util.Collections;

/**
 * Обертка для спрингового сервиса безопасности.
 * Позволяет подсунуть спрингу свой собственный класс.
 */
public class CurrentUserDetails implements UserDetails {

    private User user;

    CurrentUserDetails(User user, int passwordAge) {
        this.user = user;
        if (user != null) {
            user.checkPasswordExpired(passwordAge);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user == null ? Collections.emptyList() : Collections.singletonList(user.getRole());
    }

    @Override
    public String getPassword() {
        return user == null ? null : user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user == null ? null : user.getFullName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isCredentialsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user != null && !user.isExpired();
    }

    @Override
    public boolean isEnabled() {
        return user != null && user.isEnabled();
    }

    User getUser() {
        return user;
    }
}
