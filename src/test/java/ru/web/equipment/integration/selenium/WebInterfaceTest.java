package ru.web.equipment.integration.selenium;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.web.equipment.Application;
import ru.web.equipment.integration.WebDriverConfiguration;
import ru.web.equipment.integration.selenium.helpers.PageHelper;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebDriverConfiguration.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server_port=0")
public class WebInterfaceTest {
    private static final int ONE_SECOND = 1;

    @Autowired
    private WebDriver driver;

    @LocalServerPort
    private int serverPort;
    private PageHelper page;

    @Before
    public void prepare() {
        String baseAddress = String.format("http://localhost:%d/", serverPort);
        page = new PageHelper(driver, baseAddress);
    }


    /**
     * Отображает индексную страницу и проверяет, что заголовок страницы соответствует ожидаемому
     */
    @Test
    public void testIndexPageTitle() {
        assertEquals("Заголовок страницы не соответствует ожидаемому", "Equipment", page.getPageTitle());
    }


    /**
     * Воспроизводит сценарий вводалогина и пароля и проверяет, что пользователь вошел как администратор
     * @throws Exception
     */
    @Test
    public void testSuccessLogin() throws Exception {
        page.findLinkByText("пройти авторизацию").click();
        page.waitInSeconds(ONE_SECOND);
        // Появилась форма ввода логина и пароля Ищем поля и кнопку и иммитируем заполнение данных.
        page.getInputFieldById("username").sendKeys("admin");
        page.getInputFieldById("password").sendKeys("adminpwd");
        page.getSubmitButton().click();
        page.waitInSeconds(ONE_SECOND);
        // Появилась информация о пользователе в верхней части экрана.
        assertEquals("Вы вошли как: Администратор проекта", page.findElementByClass("userInfo").getText());
    }

}
