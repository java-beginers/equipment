package ru.web.equipment.entity;

import java.io.Serializable;

/**
 * Перечисление ролей пользователя
 *
 */
public enum UserRole implements Serializable {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор");

    private String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
