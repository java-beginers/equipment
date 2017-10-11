package ru.web.equipment.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестовый класс для проверки модели изменения паролей.
 */
public class ChangePasswordModelTest {

    @Test
    public void testAllParamsIsCorrect() {
        ChangePasswordModel cp = new ChangePasswordModel();
        cp.setOldPassword("oldPassword");
        cp.setNewPassword("newPassword");
        cp.setConfirmPassword("newPassword");
        assertEquals("Новый пароль не совпадает с подтверждением!", false, cp.passwordMismatchesConfirm());
        assertEquals("Новый пароль совпадает со старым!", false, cp.oldPasswordMatchesNew());
    }


    @Test
    public void testOldAndNewPasswordEquals() {
        ChangePasswordModel cp = new ChangePasswordModel();
        cp.setOldPassword("newPassword");
        cp.setNewPassword("newPassword");
        cp.setConfirmPassword("newPassword");
        assertEquals("Новый пароль не совпадает со старым!", true, cp.oldPasswordMatchesNew());
    }


    @Test
    public void testNewPasswordAndConfirmNotEquals() {
        ChangePasswordModel cp = new ChangePasswordModel();
        cp.setOldPassword("oldPassword");
        cp.setNewPassword("newPassword");
        cp.setConfirmPassword("confirmPassword");
        assertEquals("Новый пароль совпадает с подтверждением!", true, cp.passwordMismatchesConfirm());
    }

}