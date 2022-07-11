package br.com.alura.mvc.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	// MÉTODO DE AUTIRIZAÇÃO

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// .antMatchers("/*").permitAll() -- DINIFIÇÃO DAS URIS QUE NÃO SÃO NECESSÁRIAS
				// AUTENTICAR
				.anyRequest().authenticated() // TODAS AS DEMAIS SÃO NECESSÁRIAS
				.and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/home", true) // FORÇA O REDIRECIONAMENTO PARA /HOME NO MOMENTO DE AUTENTICAÇÃO
				.and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/login");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*ENCODE PARA SENHA*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 

		/*ESSA CONFIGURAÇÃO JA VAI CRIAR O USUARIO NO BANCO DE DADOS*/
		UserDetails user = User.builder().username("bia").password(encoder.encode("1234")).roles("USER").build();

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder); //.withUser(user);
		
		// A PARTIR DA DOCUMENTAÇÃO DO SPRING SOBRE JDBC AUTHENTICATION
		// A TABALA DE EXEMPLO É MOLDADA COM A PK NO USERNAME, DESSA FORMA O SPRING SECURITY É CONFIGURADO APENAS PARA ESSE CAMPO

	}
	// MÉTODO DE AUTENTICAÇÃO LOGIN EM MEMÓRIA

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("daniel").password("1234").roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}

}
