package ru.web.equipment;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Класс-инициализатор контекста безопасности.
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SpringSecurityInitializer() {
		super(WebSecurityConfig.class);
	}
}
