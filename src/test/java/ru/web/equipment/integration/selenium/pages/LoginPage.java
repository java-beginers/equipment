package ru.web.equipment.integration.selenium.pages;

import org.openqa.selenium.WebDriver;
import ru.web.equipment.utils.StringUtils;

/**
 * Класс, упрощающий работу со страницей логина
 *
 * @author Voronin Leonid
 * @since 04.09.18
 **/
public class LoginPage extends AbstractPage {
    private static final String PAGE_URL = "/login";


    LoginPage(WebDriver driver, int serverPort) {
        super(driver, serverPort, PAGE_URL);
    }

    @Override
    public boolean isCorrectPage() {
        return "Выполните вход".equals(getPageHeader());
    }

    public void doLogin(String userName, String password) {
        if (StringUtils.isNotBlank(userName)) {
            getInputFieldById("username").sendKeys(userName);
        }
        if (StringUtils.isNotBlank(password)) {
            getInputFieldById("password").sendKeys(password);
        }
        getSubmitButton().click();
        waitSecond();
    }

    public boolean isLoginError() {
        return "Неправильное имя пользователя или пароль".equals(findElementByClass("errorMessage").getText());
    }
}
