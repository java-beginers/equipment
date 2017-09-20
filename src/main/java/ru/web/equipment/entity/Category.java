package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность для категорий оборудования
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @Column(name = "cat_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cat_name", nullable = false, length = 255)
    private String name;
    @Column(name = "cat_description", length = 1024)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
