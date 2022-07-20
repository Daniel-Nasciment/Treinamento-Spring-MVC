package br.com.alura.mvc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.model.User;
import br.com.alura.mvc.repository.PedidoRepository;
import br.com.alura.mvc.repository.UserRepository;
import br.com.alura.mvc.request.PedidoRequest;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = "/formulario")
	// FOI NECESSARIO PASSAR ESSE OBJ NO PARAMETRO POR CONTA DO THYMELEAF QUE FAZ USO NA RENDERIZAÇÃO DO FORMULARIO
	public String formulario(PedidoRequest request) {
		return "pedido/formulario";
	}

	
	@CacheEvict(value = "pedidosAll", allEntries = true) // LIMPAR TODOS OS REGISTROS E ZERAR O CACHE
	// @RequestMapping barra alguns media types
	@PostMapping(value = "/novo")
	public String novoPedido(@Valid PedidoRequest request, BindingResult result) {

	// O Spring através do BindingResult retorna o resultado das validações
	// E quando faço a utilização dele a validação não joga o erro, é tratado pelo código
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		// OUTRA FORMA DE ACESSAR O NAME DE CONTEXTO LOGADO NA APLICAÇÃO
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Optional<User> oUsername = userRepository.findByUsername(username);
		
		if(oUsername.isPresent()) {
			Pedido pedido = request.toModel(oUsername.get());
			pedidoRepository.save(pedido);
			
			return "redirect:/home";
		}
		
		return "redirect:/home";
	}

	
}
