package com.samir.librarymanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.swing.text.html.HTML;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/register", "/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/books/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/books/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/books/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/books/**").hasAnyRole("ADMIN")
                                .requestMatchers("/api/members/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/borrow/**").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.PUT,"/api/borrow/**").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.GET,"/api/borrow/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider());
        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder(14);
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

}
