package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Класс-сущность для заявки
 */
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @Column(name = "tsk_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "tsk_category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "tsk_owner", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "tsk_executor", nullable = false)
    private User executor;

    @Column(name = "tsk_state", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TaskState state = TaskState.OPEN;

    @Column(name = "tsk_title", nullable = false)
    private String title;

    @Column(name = "tsk_description", nullable = false, length = 65535)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tsk_opened", nullable = false)
    private Date opened = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tsk_closed")
    private Date closed;

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }
}
