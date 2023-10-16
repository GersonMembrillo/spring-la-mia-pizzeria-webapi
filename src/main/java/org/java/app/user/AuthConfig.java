package org.java.app.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
			 
			http.authorizeHttpRequests()
		        .requestMatchers("/login").permitAll()
		        .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
		        .requestMatchers(new RegexRequestMatcher("/[0-9]+", null)).hasAnyAuthority("USER", "ADMIN")
		        .requestMatchers("/**").hasAuthority("ADMIN")
		        .and().formLogin()
		        .and().logout();
			
			return http.build();
	}
	
	@Bean
	UserServ userDetailsService() {
		return new UserServ();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
    }
}