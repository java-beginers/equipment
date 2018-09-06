package ru.web.equipment.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер, который будет осуществлять тестовый вход.
 * Тестовый - значит без использования логина и пароля.
 * Указанный пользователь будет загружаться из БД.
 *
 * @author Voronin Leonid
 * @since 06.09.18
 **/
@Controller()
@RequestMapping("/testlogin")
public class TestLoginController {

    private UserDetailsService userDetailsService;

    @GetMapping("login/{userName}")
    public String editCategory(@PathVariable String userName) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(userDetails, null));
            return "/index";
        } catch (UsernameNotFoundException e) {
            return "loginError";
        }
    }


    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
