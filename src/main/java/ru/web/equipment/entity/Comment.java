package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Класс-сущность для комментария
 */
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @Column(name = "cmt_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cmt_owner", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "cmt_task", nullable = false)
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cmt_date", nullable = false)
    private Date date;

    @Column(name = "cmt_text", nullable = false)
    private String text;

    public long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
