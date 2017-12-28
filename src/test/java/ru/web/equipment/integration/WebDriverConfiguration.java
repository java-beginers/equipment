package ru.web.equipment.integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfiguration {
    private static final String BROWSER_KEY = "browser";
    // Кодировщик пароля
    @Bean(name = "webDriver")
    public WebDriver getPasswordEncoder() {
        String browser = System.getProperty(BROWSER_KEY);
        switch (browser) {
            case "chrome" : return new ChromeDriver();
            case "opera" : return new OperaDriver();
            default: return new FirefoxDriver();
        }
    }
}
