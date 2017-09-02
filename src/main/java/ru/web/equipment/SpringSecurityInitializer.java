package ru.web.equipment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Класс-инициализатор контекста безопасности.
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	private static final Logger log = LoggerFactory.getLogger(SpringSecurityInitializer.class);

	public SpringSecurityInitializer() {
		super(WebSecurityConfig.class);
	}
}
