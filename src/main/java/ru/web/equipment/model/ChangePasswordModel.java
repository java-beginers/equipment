package ru.web.equipment.model;

import org.hibernate.validator.constraints.NotBlank;
import ru.web.equipment.utils.StringUtils;

import javax.validation.constraints.Size;

/**
 * Модель данных для формы смены пароля пользователем
 */
public class ChangePasswordModel {
    private String oldPassword;
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String newPassword;
    @NotBlank(message = "Подтверждение не должно быть пустым")
    @Size(min = 6, message = "Подтверждение должно быть не менее 6 символов")
    private String confirmPassword;


    /**
     * Проверяет, соответствует ли новый пароль подтверждению
     * @return {@code false} в случае, если новый пароль совпадает с подтверждением. Иначе - {@code true}
     */
    public boolean passwordMismatchesConfirm() {
        return !StringUtils.equals(newPassword, confirmPassword);
    }


    /**
     * Проверяет, соответствует ли старый пароль новому.
     * @return {@code true} в случае, если новый пароль совпадает со старым. Иначе - {@code false}
     */
    public boolean oldPasswordMatchesNew() {
        return StringUtils.equals(newPassword, oldPassword);
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public String getNewPassword() {
        return newPassword;
    }


    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }


    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
