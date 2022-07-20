package br.com.alura.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.mvc.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	
	// REFAZENDO CONFIGURAÇÕES DE SEGURANÇA PARA UTILIZAÇÃO DO JWT FOCANDO NOS CONTROLLERS REST
	
	// CONFIGURAÇÕES DE AUTENTICAÇÃO
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// ESSE MÉTODO userDetailsService EXIGE UM SERVIÇO QUE IMPLEMENTE A INTERFACE UserDetailsService
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	
	// CONFIGURAÇÕES DE AUTORIZAÇÃO (URL)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/pedidos").permitAll()
		.anyRequest().authenticated()
		.and().formLogin() ;
	}

	
	// CONFIGURAÇÕES DE ARQUIVOS ESTATICOS (JS, CSS, IMAGENS)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	


}
