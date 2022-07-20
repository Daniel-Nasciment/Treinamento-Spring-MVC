package br.com.alura.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.mvc.repository.UserRepository;
import br.com.alura.mvc.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	// DESSA FORMA CONSEGUIMOS INJETAR NO NOSSO CONTROLLER
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}	
	
	// REFAZENDO CONFIGURAÇÕES DE SEGURANÇA PARA UTILIZAÇÃO DO JWT FOCANDO NOS CONTROLLERS REST
	
	// CONFIGURAÇÕES DE AUTENTICAÇÃO
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// ESSE MÉTODO userDetailsService EXIGE UM SERVIÇO QUE IMPLEMENTE A INTERFACE UserDetailsService
		
		// ESSA FORMA DE AUTENTICAÇÃO É CRIADO UMA SESSÃO ARMAZENADO NA MEMÓRIA
		// E EM UMA API REST A AUTENTICAÇÃO DEVE SER STATELESS
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// csrf - ATAQUE HACKER, PORÉM COMO FAREMOS A AUTHENTICAÇÃO VIA TOKEN, JÁ ESTAMOS LIVRES DESSE TIPO DE ATAQUE
	// MAS SÓ DESABILITAMOS PARA QUE O SPRING SECUTIRY NÃO FAÇA A VALIDAÇÃO DO TOKEN CSRF
	
	// CONFIGURAÇÕES DE AUTORIZAÇÃO (URL)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/login", "/actuator/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		// AO SE AUTHENTICAR NÃO É PARA CRIAR SESSÃO
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// POR PADRÃO O SPRING JÁ POSSUI UL FILTER ENTÃO PRECISO USAR O MÉTODO addFilterBefore
		// ISSO SIGNIFICA QUE NOSSO FILTER VEM ANTES DO UsernamePasswordAuthenticationFilter
		.and().addFilterBefore(new AuthenticationFilterToken(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}

	
	// CONFIGURAÇÕES DE ARQUIVOS ESTATICOS (JS, CSS, IMAGENS)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	

	

}
