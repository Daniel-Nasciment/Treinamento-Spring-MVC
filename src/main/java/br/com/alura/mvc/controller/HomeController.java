package br.com.alura.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.PedidoRepository;

@Controller
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping(value = "/home")
	public String home(Model model) {
		

		 List<Pedido> pedidos = pedidoRepository.findAll();
		
		
		// "pedidos" -> Nome da variavel a ser pega pelo thymeleaf em seguida o objeto 
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}

}
