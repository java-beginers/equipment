package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность для отделов
 */
@Entity
@Table(name = "places")
public class Place implements Serializable {

    @Id
    @Column(name = "plc_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "plc_name", nullable = false, length = 255)
    private String name;
//    @Column(name = "plc_description", length = 1024)
//    private String description;

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

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
