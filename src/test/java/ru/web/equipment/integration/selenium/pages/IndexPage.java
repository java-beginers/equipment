package ru.web.equipment.integration.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * Высокоуровневый объект индексной страницы.
 *
 * @author Voronin Leonid
 * @since 05.09.18
 **/
public class IndexPage extends AbstractPage {
    private static final String PAGE_URL = "/";

    IndexPage(WebDriver driver, int serverPort) {
        super(driver, serverPort, PAGE_URL);
    }

    @Override
    public boolean isCorrectPage() {
        return "Веб приложение инвентаризации".equals(getPageHeader());
    }

    public LoginPage clickLoginLink() {
        findLinkByText("пройти авторизацию");
        return new LoginPage(getDriver(), getServerPort());
    }
}
