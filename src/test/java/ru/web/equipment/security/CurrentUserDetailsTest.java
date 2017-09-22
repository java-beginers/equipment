package ru.web.equipment.security;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import ru.web.equipment.TestUtils;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки корректности работы методов и механизма вычисления автивности пользователя
 * Фиксируем моменты:
 * - Имя пользователя - это не логин, а полное имя пользователя.
 * - Пароль пользователя - это хеш пароля.
 * - Активный (enabled) это не одно и то же, что непросроченный (!expired). То есть пользователь может иметь свежий пароль, но быть отключен.
 * - залоченный (locked) и отключенный (disabled, !enabled) у нас одно и то же.
 * - Следим за тем, чтобы в списке ролей были ровно те, которые мы вытавили.
 */
public class CurrentUserDetailsTest {
    private static final int PASSWORD_AGE = 3;
    private static final String VALID_USER_NAME = "Test User";
    private static final String VALID_USER_LOGIN = "test.user";
    private static final String VALID_USER_PASSWORD = ":-)";

    @Test
    public void testEnabledAndNotExpiredUser() throws Exception {
        User validUser = getUser(VALID_USER_NAME, UserRole.ROLE_ADMIN, VALID_USER_LOGIN, VALID_USER_PASSWORD, true, new Date());
        CurrentUserDetails userDetails = new CurrentUserDetails(validUser, PASSWORD_AGE);
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(VALID_USER_NAME, userDetails.getUsername());
        assertEquals(VALID_USER_PASSWORD, userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertFalse(authorities.contains(UserRole.ROLE_USER));
        assertTrue(authorities.contains(UserRole.ROLE_ADMIN));
    }


    @Test
    public void testEnabledAndExpiredUser() throws Exception {
        User validUser = getUser(VALID_USER_NAME, UserRole.ROLE_USER, VALID_USER_LOGIN, VALID_USER_PASSWORD, true, TestUtils.getDateFromString("01.01.1970"));
        CurrentUserDetails userDetails = new CurrentUserDetails(validUser, PASSWORD_AGE);
        assertTrue(userDetails.isEnabled());
        assertFalse(userDetails.isCredentialsNonExpired());
        assertFalse(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(VALID_USER_NAME, userDetails.getUsername());
        assertEquals(VALID_USER_PASSWORD, userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertTrue(authorities.contains(UserRole.ROLE_USER));
        assertFalse(authorities.contains(UserRole.ROLE_ADMIN));
    }


    @Test
    public void testDisabledAndNotExpiredUser() throws Exception {
        User validUser = getUser(VALID_USER_NAME, UserRole.ROLE_USER, VALID_USER_LOGIN, VALID_USER_PASSWORD, false, new Date());
        CurrentUserDetails userDetails = new CurrentUserDetails(validUser, PASSWORD_AGE);
        assertFalse(userDetails.isEnabled());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonExpired());
        assertFalse(userDetails.isAccountNonLocked());
        assertEquals(VALID_USER_NAME, userDetails.getUsername());
        assertEquals(VALID_USER_PASSWORD, userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertTrue(authorities.contains(UserRole.ROLE_USER));
        assertFalse(authorities.contains(UserRole.ROLE_ADMIN));
    }


    @Test
    public void testUserExpireIfDateOlderThan3Days() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -4); // Лимит - 3 дня, а у нас 4
        Date pastDate = calendar.getTime();
        User validUser = getUser(VALID_USER_NAME, UserRole.ROLE_USER, VALID_USER_LOGIN, VALID_USER_PASSWORD, true, pastDate);
        CurrentUserDetails userDetails = new CurrentUserDetails(validUser, PASSWORD_AGE);
        assertTrue(userDetails.isEnabled());
        assertFalse(userDetails.isCredentialsNonExpired());
        assertFalse(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(VALID_USER_NAME, userDetails.getUsername());
        assertEquals(VALID_USER_PASSWORD, userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertTrue(authorities.contains(UserRole.ROLE_USER));
        assertFalse(authorities.contains(UserRole.ROLE_ADMIN));
    }


    @Test
    public void testDisabledAndExpiredUser() throws Exception {
        User validUser = getUser(VALID_USER_NAME, UserRole.ROLE_USER, VALID_USER_LOGIN, VALID_USER_PASSWORD, false, TestUtils.getDateFromString("01.01.1970"));
        CurrentUserDetails userDetails = new CurrentUserDetails(validUser, PASSWORD_AGE);
        assertFalse(userDetails.isEnabled());
        assertFalse(userDetails.isCredentialsNonExpired());
        assertFalse(userDetails.isAccountNonExpired());
        assertFalse(userDetails.isAccountNonLocked());
        assertEquals(VALID_USER_NAME, userDetails.getUsername());
        assertEquals(VALID_USER_PASSWORD, userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertTrue(authorities.contains(UserRole.ROLE_USER));
        assertFalse(authorities.contains(UserRole.ROLE_ADMIN));
    }


    private User getUser(String name, UserRole role, String login, String password, boolean enabled, Date passwordDate) {
        User user = new User();
        user.setFullName(name);
        user.setRole(role);
        user.setLogin(login);
        user.setPasswordHash(password);
        user.setEnabled(enabled);
        user.setPwdchangedate(passwordDate);
        return user;
    }
}