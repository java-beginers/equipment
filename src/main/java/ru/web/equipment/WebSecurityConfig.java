package ru.web.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Настройки кофигурации безопастности приложения
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private transient UserDetailsService detailsService;
	private transient PasswordEncoder passwordEncoder;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/static/**", "/").permitAll()
				.antMatchers("/**").authenticated()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.and().formLogin().loginPage("/login").failureUrl("/loginError").defaultSuccessUrl("/index").permitAll()
				.and().logout().logoutSuccessUrl("/index").permitAll()
				.and().csrf().disable();
		http.headers().frameOptions().disable();
	}


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder);
	}


	@Autowired
	public void setDetailsService(UserDetailsService detailsService) {
		this.detailsService = detailsService;
	}


	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
