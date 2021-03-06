package br.com.alura.mvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile(value = {"dev", "test"})
public class DevWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// VM AGUMENTS DE PROFILE QUE O SPRING LE spring.profiles.active
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.and().csrf().disable();
	}

	

	

}
