package ru.web.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.web.equipment.security.CustomAuthenticationFailureHandler;

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
				.antMatchers("/static/**", "/", "/changePassword*", "/changepassword*").permitAll()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/**").authenticated()
				.and()
				.formLogin().loginPage("/login").failureHandler(new CustomAuthenticationFailureHandler()).defaultSuccessUrl("/index").permitAll()
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/index").invalidateHttpSession(true).permitAll()
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
