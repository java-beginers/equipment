package ru.web.equipment.integration.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * Абстрактный класс от которого будут наследоваться страницы.
 */
public abstract class AbstractPage {
    private static final int ONE_SECOND = 1;
    private static final String APPLICATION_HOST = System.getProperty("applicationHost", "localhost");
    private static final String BASE_URL = "http://" + APPLICATION_HOST + ":%d%s";

    private WebDriver driver;
    private WebDriverWait driverWait;
    private int serverPort;
    private int waitInterval = Integer.getInteger("baseDelayInSeconds", ONE_SECOND);

    AbstractPage(WebDriver driver, int serverPort, String pageUrl) {
        this.driver = driver;
        this.serverPort = serverPort;
        driverWait = new WebDriverWait(driver, waitInterval);
        driver.get(String.format(BASE_URL, serverPort, pageUrl));
        waitSecond();
    }

    public abstract boolean isCorrectPage();

    public boolean isLoggedAs(String userFullName) {
        return String.format("Вы вошли как: %s", userFullName).equals(findElementByClass("userInfo").getText());
    }

    WebElement findElementByClass(String cssClass) {
        return findAndAssertElement(By.className(cssClass));
    }

    WebElement findLinkByText(String text) {
        return findAndAssertElement(By.linkText(text));
    }

    void waitSecond() {
        driverWait.withTimeout(waitInterval, TimeUnit.SECONDS);
    }

    void waitSecond(int numberOfSeconds) {
        driverWait.withTimeout((numberOfSeconds <= 0) ? waitInterval : numberOfSeconds, TimeUnit.SECONDS);
    }

    WebElement getInputFieldById(String id) {
        return getElementByXpath(".//input[@id='" + id + "']");
    }

    String getPageHeader() {
        return getElementByXpath(".//h1").getText();
    }


    WebElement getSubmitButton() {
        return getElementByXpath(".//input[@type='submit']");
    }

    WebElement getSubmitButton(String caption) {
        return getElementByXpath(".//input[@type='submit' and @value='" + caption + "']");
    }

    WebDriver getDriver() {
        return driver;
    }

    int getServerPort() {
        return serverPort;
    }


    private WebElement getElementByXpath(String selector) {
        By xpathSelector = By.xpath(selector);
        return findAndAssertElement(xpathSelector);
    }

    private WebElement findAndAssertElement(By criteria) {
        WebElement element = driver.findElement(criteria);
        assertNotNull(element);
        return element;
    }
}
