package br.com.alura.mvc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.model.User;
import br.com.alura.mvc.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	
	@Autowired
	private UserRepository repository;
	
	// COM ESSA INTERFACE IMPLEMENTADA, O SPRING VAI SABER QUE ESSE MÉTOO VAI SER CHAMADO
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// O SPRING PASSA SOMENTE O USERNAME/EMAIL PARA QUE NÓS FAÇAMOS A BUSCA NO BANCO DE DADOS
		// A SENHA ELE FAZ A CHECAGEM EM MEMÓRIA, 
		
		Optional<User> user = repository.findByUsername(username);
		
		if(user.isPresent()) {
			
			return user.get();
			
		}
		
		// CLASSE DE EXCEPTION DO SPRING PARA USUARIO NÃO ENCONTRADO 
		throw new UsernameNotFoundException("Dados invalidos");
	}

}
