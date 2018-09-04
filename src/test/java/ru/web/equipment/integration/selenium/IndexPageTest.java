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
import ru.web.equipment.integration.selenium.pages.IndexPage;
import ru.web.equipment.integration.selenium.pages.LoginPage;
import ru.web.equipment.integration.selenium.pages.WebApplication;

import static org.junit.Assert.assertTrue;

/**
 * Тестовый класс индексной страницы
 *
 * @author Voronin Leonid
 * @since 04.09.18
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebDriverConfiguration.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server_port=0")
public class IndexPageTest {

    @Autowired
    private WebApplication site;

    @LocalServerPort
    private int serverPort;
    private IndexPage page;

    @Before
    public void prepare() {
        page = site.openIndexPage(serverPort);
    }

    @Test
    public void testIndexPageAppears() {
        assertTrue(page.isCorrectPage());
    }

    @Test
    public void testLoginFormAppears() {
        LoginPage loginPage = page.clickLoginLink();
        assertTrue(loginPage.isCorrectPage());
    }
}
