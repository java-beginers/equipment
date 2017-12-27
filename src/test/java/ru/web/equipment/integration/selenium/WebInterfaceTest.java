package ru.web.equipment.integration.selenium;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.web.equipment.Application;
import ru.web.equipment.integration.WebDriverConfiguration;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebDriverConfiguration.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server_port=0")
public class WebInterfaceTest {

    @Autowired
    private WebDriver driver;

    @LocalServerPort
    private int serverPort;
    private String siteAddress;

    @Before
    public void prepare() {
        siteAddress = String.format("http://localhost:%d/", serverPort);
    }


    /**
     * Отображает индексную страницу и проверяет, что заголовок страницы соответствует ожидаемому
     */
    @Test
    public void testIndexPageTitle() {
        driver.get(siteAddress);
        String title = driver.getTitle();
        assertEquals("Заголовок страницы не соответствует ожидаемому", "Equipment", title);
    }


    /**
     * Воспроизводит сценарий вводалогина и пароля и проверяет, что пользователь вошел как администратор
     * @throws Exception
     */
    @Test
    public void testSuccessLogin() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        driver.get(siteAddress);
        // ищем ссылку для входа на странице
        WebElement element = driver.findElement(By.linkText("пройти авторизацию"));
        assertNotNull(element);
        element.click();
        wait.withTimeout(3, TimeUnit.SECONDS);
        // Должна была загрузиться форма ввода логина и пароля Ищем поля и кнопку и иммитируем заполнение данных.
        WebElement loginField = driver.findElement(By.id("username"));
        assertNotNull(loginField);
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.id("password"));
        assertNotNull(passwordField);
        passwordField.sendKeys("adminpwd");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        assertNotNull(loginButton);
        loginButton.click();
        wait.withTimeout(3, TimeUnit.SECONDS);
        // Должна появиться информация о пользователе в верхней части экрана.
        WebElement userInfo = driver.findElement(By.className("userInfo"));
        assertNotNull(userInfo);
        assertEquals("Вы вошли как: Администратор проекта", userInfo.getText());
    }

}
