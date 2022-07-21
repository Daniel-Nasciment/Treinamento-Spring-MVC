package br.com.alura.mvc.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.alura.mvc.model.User;

@DataJpaTest // CRIAR CLASSE DE TESTE PARA REPOSITORY
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // O TESTE RODA NO MESMO BANCO DE DADOS DA APLICAÇÃO
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	void deveriaCarregarUmUserAoBuscarPeloNome() {
		
		String nome = "daniel";
		
		User user = repository.findByUsername(nome).get();
		
		assertNotNull(user);
		assertEquals(nome, user.getUsername());
	}
	
	@Test
	void naoDeveriaCarregarUmUserAoBuscarPeloNome() {
		
		String nome = "danielNascimento";
		
		Optional<User> user = repository.findByUsername(nome);
		
		assertFalse(user.isPresent());
	}

}
