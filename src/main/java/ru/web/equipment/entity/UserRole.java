package ru.web.equipment.entity;

import java.io.Serializable;

/**
 * Перечисление ролей пользователя
 *
 * Роль "Пользователь" присваивается изначально всем создаваемым пользователям. Позволяет создавать задачи
 * Роль "Специалист" назначается пользователю администратором. Специалисты могут создавать категории заявок
 * Роль "Администратор" используется для управления персоналом.
 */
public enum UserRole implements Serializable {
    ROLE_USER("Пользователь"),
    ROLE_SPEC("Специалист"),
    ROLE_ADMIN("Администратор");

    private String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
