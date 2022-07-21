package br.com.alura.mvc.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.mvc.model.User;

@DataJpaTest // CRIAR CLASSE DE TESTE PARA REPOSITORY
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ISSO SERVE PARA O SPRING NÃO SUBSTITUIR MINHAS CONFIGURAÇÕES DO DATABASE
@ActiveProfiles(value = "test") // OUTRA FORMA DE DEFINIR O PROFILE
public class UserRepositoryTest {
	
	// POR PADRÃO O SPRING BOOT CONSIDERA QUE NOS TESTES DE REPOSITORY ESTAREMOS UTILIZANDO UM BANCO DE DADOS EM MEMÓRIA

	@Autowired
	private UserRepository repository;
	
	@Autowired // INICIALIZAR O BANCO DA FORMA QUE EU PRECISE
	private TestEntityManager em;
	
	@Test
	void deveriaCarregarUmUserAoBuscarPeloNome() {
		
		String nome = "daniel";
		
		User newUser = new User();
		newUser.setUsername("daniel");
		
		em.persist(newUser);
		
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
