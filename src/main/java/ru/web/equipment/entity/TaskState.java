package ru.web.equipment.entity;

import java.io.Serializable;

/**
 * Перечисление для статусов заявки
 */
public enum TaskState implements Serializable {
    OPEN("Открыта"),
    PROCESSING("В работе"),
    DONE("Закрыта"),
    CLOSED("Выполнена");

    private String description;

    TaskState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
