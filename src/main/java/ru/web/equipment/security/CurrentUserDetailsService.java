package ru.web.equipment.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;
import ru.web.equipment.repository.UserRepository;

/**
 * Сервис, возвращающий информаци о пользователе
 * Created by leonid on 12.11.16.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${admin.login}")
    private String login;
    @Value("${admin.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findOneByLogin(username);
        } catch (Exception e) {
            // Ничего не делаем. Видимо, пользователя нет
        }
        if (user == null) {
            user = getAdminUser();
        }
        return new CurrentUserDetails(user);
    }

    // Встроенный администратор
    private User getAdminUser() {
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            return null;
        }
        User user = new User();
        user.setFullName("Администратор проекта");
        user.setRole(UserRole.ROLE_ADMIN);
        user.setLogin(login);
        user.setPasswordHash(passwordEncoder.encode(password));
        return user;
    }

}
