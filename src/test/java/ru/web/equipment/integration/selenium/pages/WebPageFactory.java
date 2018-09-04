package ru.web.equipment.integration.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * Фабрика, которая будет создавать нужную нам страницу веб интерфейса.
 *
 * @author Voronin Leonid
 * @since 05.09.18
 **/
public class WebPageFactory implements WebApplication {
    private WebDriver driver;

    public WebPageFactory(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LoginPage openLoginPage(int serverPort) {
        return new LoginPage(driver, serverPort);
    }

    @Override
    public IndexPage openIndexPage(int serverPort) {
        return new IndexPage(driver, serverPort);
    }
}
