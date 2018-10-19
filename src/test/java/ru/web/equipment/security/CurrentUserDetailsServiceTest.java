package ru.web.equipment.security;

import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.web.equipment.entity.UserRole;
import ru.web.equipment.repository.UserRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrentUserDetailsServiceTest {
    private static final String PASSWORD = ":-)";
    private static final String LOGIN = "admin";
    private static final String AGE = "10";
    private final Map<String, String> properties;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private Environment environment;
    private CurrentUserDetailsService service;

    public CurrentUserDetailsServiceTest() {
        properties = new HashMap<>();
        properties.put(CurrentUserDetailsService.ADMIN_LOGIN, LOGIN);
        properties.put(CurrentUserDetailsService.ADMIN_PASSWORD, PASSWORD);
        properties.put(CurrentUserDetailsService.PASSWORD_AGE, AGE);

        passwordEncoder = new MD5PasswordEncoder();

        userRepository = mock(UserRepository.class);
        when(userRepository.findOneByLogin(CurrentUserDetailsService.ADMIN_LOGIN)).thenReturn(null);

        environment = mock(Environment.class);
        when(environment.getProperty(anyString())).thenAnswer((Answer<String>) invocation -> {
            String key = invocation.getArgumentAt(0, String.class);
            return properties.get(key);
        });

        service = new CurrentUserDetailsService();
        service.setPasswordEncoder(passwordEncoder);
        service.setUserRepository(userRepository);
        service.setEnvironment(environment);
    }

    /**
     * Если в БД нету пользователя, но указаны параметры для амина и логин совпадает c тем, что в параметре, то вернется админ
     */
    @Test
    public void testVirtualAdminCreateIfNoUserExistsAndLoginLikeInParams() {
        UserDetails userDetails = service.loadUserByUsername(LOGIN);
        assertNotNull(userDetails);
        assertEquals(passwordEncoder.encode(PASSWORD), userDetails.getPassword());
        assertEquals(true, userDetails.isEnabled());
        assertEquals(true, userDetails.isAccountNonExpired());
        assertEquals(true, userDetails.isAccountNonLocked());
        assertEquals(true, userDetails.isCredentialsNonExpired());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.contains(UserRole.ROLE_ADMIN));
        assertFalse(authorities.contains(UserRole.ROLE_USER));
    }


    /**
     * Если в БД нету пользователя, указаны параметры для амина, но логин НЕ совпадает c тем, что в параметре, то вернется CurrentUserDetails(null)
     */
    @Test(expected = UsernameNotFoundException.class)
    public void testExceptionIsThrownIfNoSouchUser() {
        UserDetails userDetails = service.loadUserByUsername("blabla");
        assertNotNull(userDetails);
        assertEquals(null, userDetails.getPassword());
        assertEquals(false, userDetails.isEnabled());
        assertEquals(false, userDetails.isAccountNonExpired());
        assertEquals(false, userDetails.isAccountNonLocked());
        assertEquals(false, userDetails.isCredentialsNonExpired());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
    }
}