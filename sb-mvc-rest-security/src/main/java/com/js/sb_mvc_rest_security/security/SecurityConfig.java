package com.js.sb_mvc_rest_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
 
@Configuration
public class SecurityConfig {
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/public").permitAll()
	                .requestMatchers("/private").hasRole("USER")
	                .requestMatchers("/admin").hasRole("ADMIN")
	                .anyRequest().authenticated()
	            )
	            .formLogin(Customizer.withDefaults())
	            .httpBasic(Customizer.withDefaults())
	            .csrf(csrf -> csrf.disable())
	            .logout(Customizer.withDefaults()); // Disable CSRF for simplicity (not for production)
 
	        return http.build();
	    }
	
 
	    @Bean
	    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	    	UserDetails user = User.withUsername("user")
	            .password(passwordEncoder.encode("password")) // password encoding
	            .roles("USER")
	            .build();
	        
	        UserDetails admin = User.withUsername("admin")
	                .password(passwordEncoder.encode("admin"))
	                .roles("ADMIN")
	                .build();
	        
	        return new InMemoryUserDetailsManager(user,admin);
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	   
	    
}
