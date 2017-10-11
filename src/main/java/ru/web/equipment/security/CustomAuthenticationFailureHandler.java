package ru.web.equipment.security;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import ru.web.equipment.controller.RootController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final static String USER_NAME_PARAM = "username";
    private RedirectStrategy redirectStrategy;

    public CustomAuthenticationFailureHandler() {
        redirectStrategy = (request, response, url) -> response.sendRedirect(request.getContextPath() + url);
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        // Запоминаем текущее имя пользователя в сессии.
        String userName = request.getParameter(USER_NAME_PARAM);
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(RootController.SESSION_USER_NAME, userName);
        }


        if (e instanceof CredentialsExpiredException || e instanceof AccountExpiredException) {
            // Исключение, которое бросается при просроченном пароле
            redirectStrategy.sendRedirect(request, response, "/changepassword");
        } else {
            redirectStrategy.sendRedirect(request, response, "/loginError");
        }
    }
}
