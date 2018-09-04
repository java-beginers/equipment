package ru.web.equipment.integration.selenium.pages;

/**
 * Контракт для вызова высокоуровневых объектов страниц веб-интерфейса
 *
 * @author Voronin Leonid
 * @since 05.09.18
 **/
public interface WebApplication {

    LoginPage openLoginPage(int serverPort);

    IndexPage openIndexPage(int serverPort);
}
