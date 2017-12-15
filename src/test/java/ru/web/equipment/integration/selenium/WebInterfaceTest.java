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

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testIndexPageTitle() {
        driver.get(siteAddress);
        String title = driver.getTitle();
        assertEquals("Заголовок страницы не соответствует ожидаемому", "Equipment", title);
    }
}
