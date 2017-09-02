package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность для категорий заявок
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @Column(name = "ctg_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ctg_executor")
    private User executor;

    @Column(name = "ctg_title", nullable = false, length = 255)
    private String title;

    public long getId() {
        return id;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
