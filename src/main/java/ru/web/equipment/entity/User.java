package ru.web.equipment.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.web.equipment.utils.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @NotBlank(message = "Необходимо указать значение")
    @Column(name = "usr_login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "usr_password", nullable = false)
    private String passwordHash;

    @Transient
    @NotBlank(message = "Необходимо указать значение")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;

    @Transient
    @NotBlank(message = "Необходимо указать значение")
    @Size(min = 6, message = "Подтверждение должно быть не менее 6 символов")
    private String confirm;

    @NotBlank(message = "Необходимо указать значение")
    @Column(name = "usr_fullname", nullable = false, length = 128)
    private String fullName;

    @NotBlank(message = "Необходимо указать значение")
    @Column(name = "usr_phone", nullable = false, length = 128)
    private String phone;

    @Email(message = "Недопустимый формат адреса электронной почты")
    @Column(name = "usr_email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "usr_role", nullable = false, length = 50)
    private UserRole role = UserRole.ROLE_USER;

    @Column(name = "usr_enabled", nullable = false)
    private boolean enabled;

    @Column(name = "usr_expired", nullable = false)
    private boolean expired;

    @Column(name = "usr_pwdchange_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date passwordChangeDate;

    /**
     * Проверяет, не просрочен ли пароль. Проверка выполняется на основе даты последней смены пароля.
     * @param daysCount Срок годности пароля в днях.
     */
    public void checkPasswordExpired(int daysCount) {
        if (passwordChangeDate != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(passwordChangeDate);
            calendar.add(Calendar.DAY_OF_MONTH, daysCount); // время действия пароля месяц
            Date expirationDate = calendar.getTime();
            expired = expirationDate.before(new Date()) || expired;
        }
    }

    /**
     * Проверяет соответствие пароля и подтверждения
     * @return {@code true} если пароль и подтверждение совпадают. Иначе - {@code false}
     */
    public boolean isPasswordMatchesConfirm() {
        return StringUtils.equals(password, confirm);
    }

    /**
     * Признак того, что это новый пользователь
     * @return
     */
    public boolean isNewUser() {
        return id == 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}


