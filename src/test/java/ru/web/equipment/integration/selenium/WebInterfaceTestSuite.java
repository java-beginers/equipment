package ru.web.equipment.integration.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Пакет тестов для веб интерфейса
 *
 * @author Voronin Leonid
 * @since 04.09.18
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        IndexPageTest.class,
        LoginPageTest.class
})
public class WebInterfaceTestSuite {

}
