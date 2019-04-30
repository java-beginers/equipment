package ru.web.equipment.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * Перечисление ролей пользователя
 */
public enum UserRole implements Serializable, GrantedAuthority {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор");

    private String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
