package ru.web.equipment.integration.selenium.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * Класс-помощник для работы со страницей.
 */
public class PageHelper {
    private WebDriver driver;
    private WebDriverWait driverWait;
    private int waitInterval = Integer.getInteger("baseDelayInSeconds", 5);

    public PageHelper(WebDriver driver, String pageUrl) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, waitInterval);
        this.driver.get(pageUrl);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement findElementByClass(String cssClass) {
        return findAndAssertElement(By.className(cssClass));
    }

    public WebElement findLinkByText(String text) {
        return findAndAssertElement(By.linkText(text));
    }

    public void waitInSeconds(int numberOfSeconds) {
        driverWait.withTimeout((numberOfSeconds <= 0) ? waitInterval : numberOfSeconds, TimeUnit.SECONDS);
    }

    public WebElement getInputFieldById(String id) {
        return getElementByXpath(".//input[@id='" + id + "']");
    }

    public WebElement getSubmitButton() {
        return getElementByXpath(".//input[@type='submit']");
    }

    public WebElement getSubmitButtonWithCaption(String caption) {
        return getElementByXpath(".//input[@type='submit' and @value='" + caption + "']");
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
