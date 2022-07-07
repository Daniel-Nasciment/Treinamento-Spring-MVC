package br.com.alura.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.enuns.StatusPedido;
import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.PedidoRepository;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public String home(Model model) {
		
		// MODEL É DO PACOTE "UI" USER INTERFACE, PARA PASSAR ESSE ATRIBUTO PARA A VIEW (HTML) E TRATA JOGA ESSE DADO NA TELA COM O THYMELEAF

		List<Pedido> pedidos = pedidoRepository.findAll();
		
		
		// "pedidos" -> Nome da variavel a ser pega pelo thymeleaf em seguida o objeto 
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}

	
	@GetMapping(value = "/{status}")
	public String porStatus(Model model, @PathVariable("status") String status) {
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
	// MÉTODO AUXILIAR PARA A REQUISIÇÃO DE ALGUMA URL DESCONHECIDA PELA API
	@ExceptionHandler(Exception.class)
	public String onError() {
		return"redirect:/home";
	}

	
}
