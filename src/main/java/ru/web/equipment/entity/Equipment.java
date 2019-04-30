package ru.web.equipment.entity;

import ru.web.equipment.utils.StringUtils;

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
    @JoinColumn(name = "eqp_catcode", nullable = false)
    private Category category;
    @Transient
    private long categoryCode;
    @ManyToOne
    @JoinColumn(name = "eqp_vndcode", nullable = false)
    private Vendor vendor;
    @Transient
    private long vendorCode;
    @ManyToOne
    @JoinColumn(name = "eqp_psncode", nullable = false)
    private Person person;
    @Transient
    private long personCode;
    @Column(name = "eqp_model", length = 255)
    private String model;
    @Column(name = "eqp_serial", length = 50)
    private String serial;
    @Column(name = "eqp_inventory", length = 50)
    private String inventory;
    @Column(name = "eqp_damaged")
    private boolean damaged;
    @Column(name = "eqp_description", length = 1024)
    private String description;

    @PostLoad
    private void updateCodes() {
        vendorCode = vendor == null ? 0 : vendor.getId();
        categoryCode = category == null ? 0 : category.getId();
        personCode = person == null ? 0 : person.getId();
    }


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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(long categoryCode) {
        this.categoryCode = categoryCode;
    }

    public long getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(long vendorCode) {
        this.vendorCode = vendorCode;
    }

    public long getPersonCode() {
        return personCode;
    }

    public void setPersonCode(long personCode) {
        this.personCode = personCode;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public String getEquipmentDamaged() {
        return StringUtils.getStringFromBoolean(damaged);
    }
}
