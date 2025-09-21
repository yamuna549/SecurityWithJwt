package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.CustomerService;

@Configuration
@EnableWebSecurity
public class SecurityCongig {
	@Autowired
	private CustomerService customerService;
	@Autowired
    private JWTFilter jwtFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/register","/login").permitAll()
                .anyRequest().authenticated()
                
            );

        return http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).csrf().disable().build();
    }

	@Bean
	public PasswordEncoder getPassworcEncoder() {
		return new BCryptPasswordEncoder();     
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(customerService);
	        provider.setPasswordEncoder(getPassworcEncoder());
	        return provider;
	}
	

	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
		
	}
	

}
