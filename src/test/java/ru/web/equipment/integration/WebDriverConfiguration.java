package ru.web.equipment.integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.web.equipment.integration.selenium.pages.WebApplication;
import ru.web.equipment.integration.selenium.pages.WebPageFactory;
import ru.web.equipment.utils.StringUtils;

import java.net.URL;

@Configuration
@ComponentScan("ru.web.equipment.test.*")
public class WebDriverConfiguration {
    private static final String BROWSER_KEY = "browser";
    private static final String HUB_URL = "http://localhost:4444/wd/hub";


    @Bean
    public WebApplication createWebApplicationFactory() throws Exception {
        return new WebPageFactory(getDriver());
    }


    private WebDriver getDriver() throws Exception {
        DesiredCapabilities remoteFirefox = DesiredCapabilities.firefox();
        DesiredCapabilities remoteChrome = DesiredCapabilities.chrome();
        String browser = System.getProperty(BROWSER_KEY);
        if (StringUtils.isBlank(browser)) {
            return new FirefoxDriver();
        }
        switch (browser) {
            case "chrome" : return new ChromeDriver();
            case "hub-chrome" : return new RemoteWebDriver(new URL(HUB_URL), remoteChrome);
            case "firefox" : return new FirefoxDriver();
            case "hub-firefox" : return new RemoteWebDriver(new URL(HUB_URL), remoteFirefox);
            case "opera" : return new OperaDriver();
            default: return new FirefoxDriver();
        }
    }
}
