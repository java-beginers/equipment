package ru.web.equipment.integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfiguration {
    // Кодировщик пароля
    @Bean(name = "webDriver")
    public WebDriver getPasswordEncoder() {
        return new FirefoxDriver();
    }
}
