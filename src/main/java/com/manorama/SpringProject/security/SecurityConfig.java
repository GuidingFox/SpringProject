package com.manorama.SpringProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET).permitAll().antMatchers(HttpMethod.POST).permitAll().anyRequest().authenticated()
				.and().httpBasic();

		return http.build();
	}

//	@Bean
//	public UserDetailsService users() {
//		
//		UserDetails admin = User.builder().username("admin").password("supersecurepassword").roles("ADMIN").build();
//		UserDetails user = User.builder().username("user").password("superstrongpassword").roles("USER").build();
//		return new InMemoryUserDetailsManager(admin, user);
//	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
