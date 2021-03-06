package ru.web.equipment.integration.selenium;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.web.equipment.Application;
import ru.web.equipment.integration.WebDriverConfiguration;
import ru.web.equipment.integration.selenium.pages.LoginPage;
import ru.web.equipment.integration.selenium.pages.WebApplication;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebDriverConfiguration.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server_port=0")
public class LoginPageTest {

    @Autowired
    private WebApplication site;

    @LocalServerPort
    private int serverPort;
    private LoginPage page;

    @Before
    public void prepare() {
        page = site.openLoginPage(serverPort);
    }

    @Test
    public void testSuccessLogin() {
        page.doLogin("admin", "adminpwd");
        assertTrue(page.isLoggedAs("Администратор проекта"));
    }

    @Test
    public void testLoginWithInvalidLogin() {
        page.doLogin("blabla", "adminpwd");
        assertTrue(page.isLoginError());
    }

    @Test
    public void testLoginWithInvalidPassword() {
        page.doLogin("admin", "blablabla");
        assertTrue(page.isLoginError());
    }

    @Test
    public void testLoginWithEmptyName() {
        page.doLogin("", "blablabla");
        assertTrue(page.isLoginError());
    }

    @Test
    public void testLoginWithEmptyPassword() {
        page.doLogin("admin", "");
        assertTrue(page.isLoginError());
    }
}
