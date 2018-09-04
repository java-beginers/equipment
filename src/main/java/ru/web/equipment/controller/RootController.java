package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.equipment.entity.User;
import ru.web.equipment.model.ChangePasswordModel;
import ru.web.equipment.repository.UserRepository;
import ru.web.equipment.security.SecurityUtils;
import ru.web.equipment.utils.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RootController {
    public static final String SESSION_USER_NAME = "userName";
    private static final Logger log = LoggerFactory.getLogger(RootController.class);
    private UserRepository userRepository;

    @RequestMapping("/")
    public String getRootPage() {
        return "index";
    }


    @RequestMapping("/index")
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }


    @GetMapping("/changepassword")
    public String promptForNewPassword(Model model) {
        ChangePasswordModel changePasswordModel = new ChangePasswordModel();
        model.addAttribute("changePassword", changePasswordModel);
        return "changePassword";
    }


    @PostMapping("/changepassword")
    public String saveNewEquipment(final ModelMap model,
                                   @ModelAttribute("changePassword") @Valid ChangePasswordModel changePassword,
                                   final BindingResult result, HttpSession httpSession) {
        if (result.hasErrors()) {
            model.addAttribute("changePassword", changePassword);
            return "changePassword";
        }
        if (changePassword != null) {
            try {
                if (changePassword.passwordMismatchesConfirm()) {
                    model.addAttribute("error", true);
                    model.addAttribute("errorText", "Новый пароль не совпадает с подтверждением!");
                    model.addAttribute("changePassword", new ChangePasswordModel());
                    return "changePassword";
                } else if (changePassword.oldPasswordMatchesNew()) {
                    model.addAttribute("error", true);
                    model.addAttribute("errorText", "Новый пароль совпадает со старым!");
                    model.addAttribute("changePassword", new ChangePasswordModel());
                    return "changePassword";
                }
                User currentUser = SecurityUtils.getCurrentUser();
                if (currentUser == null && httpSession != null) {
                    //Возьмем текущего пользователя из сессии
                    String currentUserName = (String) httpSession.getAttribute(SESSION_USER_NAME);
                    if (StringUtils.isNotBlank(currentUserName)) {
                        currentUser = userRepository.findOneByLogin(currentUserName);
                    }
                }
                if (currentUser == null || currentUser.getId() == 0) {
                    //Либо это виртуальный администратор, либо что-то не так
                    model.addAttribute("error", true);
                    model.addAttribute("errorText", "Невозможно сменить пароль данному пользователю");
                    model.addAttribute("changePassword", new ChangePasswordModel());
                    return "changePassword";
                }
            } catch (Exception e) {
                log.error("Ошибка при сохранении оборудования.", e);
            }
        }
        return "/logout";
    }


    @RequestMapping("/index.html")
    public String getIndexPage2() {
        return "index";
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
