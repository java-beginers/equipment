package ru.web.equipment.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.web.equipment.utils.StringUtils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс для шифрования пароля в "Соленый" MD5 хэш
 */
public class MD5PasswordEncoder implements Serializable, PasswordEncoder {
    private final static String SALT = "123qweasdgfdtre@#$%^*&&HJKJH@#HFHGF$%^%$GJHVBNJdghjfu";

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String saltPassword = SALT + rawPassword.toString();
            byte[] hash = md.digest(saltPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                builder.append(String.format("%02x", b & 0xFF));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Ошибка при шифровании пароля! " + e.getMessage());
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtils.equals(encode(rawPassword), encodedPassword);
    }
}
