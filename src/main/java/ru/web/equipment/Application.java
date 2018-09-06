package ru.web.equipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ru.web.equipment.security.MD5PasswordEncoder;

/**
 * Основной контекст приложения
 */

@SpringBootApplication
@Import({WebSecurityConfig.class})
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	// Бин для загрузки файлов
	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver filterMultipartResolver() {
		CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
		filterMultipartResolver.setDefaultEncoding("utf-8");
		return filterMultipartResolver;
	}


	// Кодировщик пароля
	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new MD5PasswordEncoder();
	}
}
