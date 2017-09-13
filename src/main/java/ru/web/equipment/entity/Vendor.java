package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность для производителей оборудования
 */
@Entity
@Table(name = "vendors")
public class Vendor implements Serializable{

    @Id
    @Column(name = "vnd_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "vnd_name", nullable = false)
    private String name;
    @Column(name = "vnd_site", length = 1024)
    private String site;

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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
