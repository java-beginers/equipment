package ru.web.equipment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.web.equipment.entity.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Обертка для спрингового сервиса безопасности.
 * Позволяет подсунуть спрингу свой собственный класс.
 */
public class CurrentUserDetails implements UserDetails {

    private User user;

    public CurrentUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new LinkedList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return roles;
    }

    @Override
    public String getPassword() {
        return user == null ? null : user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user == null ? null : user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return !user.isExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
