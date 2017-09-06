package ru.web.equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс, представляющий собой сущность пользователя
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "usr_pcode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "usr_login", unique = true, nullable = false, length = 50)
    private String login;
    @Column(name = "usr_password", nullable = false)
    private String passwordHash;
    @Column(name = "usr_fullname", nullable = false, length = 128)
    private String fullName;
    @Column(name = "usr_phone", nullable = false, length = 128)
    private String phone;
    @Column(name = "usr_email", nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "usr_role", nullable = false, length = 50)
    private UserRole role = UserRole.ROLE_USER;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
