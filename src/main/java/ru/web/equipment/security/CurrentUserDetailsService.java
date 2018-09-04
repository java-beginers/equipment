package ru.web.equipment.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;
import ru.web.equipment.repository.UserRepository;
import ru.web.equipment.utils.StringUtils;

import java.util.Date;

/**
 * Сервис, возвращающий информаци о пользователе
 * Created by leonid on 12.11.16.
 */
@Service
public class  CurrentUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final static int DEFAULT_PASSWORD_AGE = 30; // срок жизни пароля по умолчанию.
    final static String PASSWORD_AGE = "password.max-age";
    final static String ADMIN_LOGIN = "admin.login";
    final static String ADMIN_PASSWORD = "admin.password";


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private Environment environment;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userRepository.findOneByLogin(username);
        } catch (Exception e) {
            // Ничего не делаем. Видимо, пользователя нет
        }
        if (user == null) {
            user = getAdminUser(username);
        }
        return new CurrentUserDetails(user, getPasswordAge());
    }

    // Встроенный администратор
    private User getAdminUser(String userName) {
        String login = getLogin();
        String password = getPassword();
        if (!StringUtils.equals(userName, login) || StringUtils.isBlank(password)) {
            return null;
        }
        User user = new User();
        user.setFullName("Администратор проекта");
        user.setRole(UserRole.ROLE_ADMIN);
        user.setLogin(login);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setPasswordChangeDate(new Date());
        return user;
    }

    private String getLogin() {
        return environment.getProperty(ADMIN_LOGIN);
    }

    private String getPassword() {
        return environment.getProperty(ADMIN_PASSWORD);
    }

    private int getPasswordAge() {
        String ageValue = environment.getProperty(PASSWORD_AGE);
        if (StringUtils.isNotBlank(ageValue)) {
            try {
                int age = Integer.parseInt(ageValue);
                return age <= 0 ? DEFAULT_PASSWORD_AGE : age;
            } catch (Exception e) {
                String message = String.format("Некорректное значение параметра %s [%s]", PASSWORD_AGE, ageValue);
                log.error(message, e);
            }
        }
        return DEFAULT_PASSWORD_AGE;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
