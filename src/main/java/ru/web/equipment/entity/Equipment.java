package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность для категорий оборудования
 */
@Entity
@Table(name = "equipments")
public class Equipment implements Serializable {

    @Id
    @Column(name = "eqp_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "eqp_catcode",  nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "eqp_vndcode",  nullable = false)
    private Vendor vendor;
    @Column(name = "eqp_model", length = 255)
    private String model;
    @Column(name = "eqp_serial", length = 50)
    private String serial;
    @Column(name = "eqp_inventory", length = 50)
    private String inventory;
    @Column(name = "eqp_damaged")
    private Boolean damaged;
    @Column(name = "eqp_description", length = 1024)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public Boolean getDamaged() {
        return damaged;
    }

    public void setDamaged(Boolean damaged) {
        this.damaged = damaged;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
