package ru.web.equipment.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность для сотрудников
 */
@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @Column(name = "psn_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "psn_fname", nullable = false, length = 50)
    private String fname;
    @Column(name = "psn_mname", nullable = false, length = 50)
    private String mname;
    @Column(name = "psn_lname", nullable = false, length = 50)
    private String lname;
    @Column(name = "psn_phone", length = 15)
    private String phone;
    @Column(name = "psn_mail", length = 30)
    private String mail;
    @Column(name = "psn_description", length = 1024)
    private String description;

    public String getShortName() {
        return String.format("%s%s%s", fname, getInitial(mname), getInitial(lname));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fname + ' ' + mname + ' ' + lname;
    }

    // Получаем первый символ в верхнем регистре. После символа - точка, перед символом - пробел.
    // Это сделано для того, чтобы при форматировании не появлялись лишние пробелы, например, при отсутствии отчества.
    private String getInitial(String name) {
        return StringUtils.isBlank(name) ? "" : " " + name.trim().substring(0, 1).toUpperCase() + ".";
    }
}
