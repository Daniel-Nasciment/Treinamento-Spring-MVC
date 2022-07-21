package br.com.alura.mvc.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.PedidoRepository;

@ActiveProfiles("test")
@WebMvcTest(controllers = PedidosRest.class)
public class PedidosRestTest {

	@Autowired
	private MockMvc mvc; // SIMULA UMA REQUISIÇÃO MVC

	@MockBean
	private PedidoRepository repo;

	@Test
	public void deveriaRetornarOk() throws Exception {

		// PASSANDO PAGEABLE PARA O REPOSITORY
		Pageable pageable = PageRequest.of(0, 3);

		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos.add(new Pedido("Teste", "Teste", "Teste"));

		// FAÇO A CONVERSÃO DA LISTA PARA PAGE
		Page<Pedido> listPage = new PageImpl<>(pedidos);

		// MOCKO A CHAMADA AO REPOSITORY
		BDDMockito.given(this.repo.findAll(pageable)).willReturn(listPage);

		// TENHO QUE PASSAR OS PARAMETROS DE PAGINAÇÃO SE NÃO, NÃO FUNCIONA
		mvc.perform(MockMvcRequestBuilders.get("/api/pedidos?page=0&size=3")
				// CASO TENHA .content("CONTEUDO JSON")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	
}
