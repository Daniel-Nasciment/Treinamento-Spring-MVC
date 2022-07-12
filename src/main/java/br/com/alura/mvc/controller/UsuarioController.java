package br.com.alura.mvc.controller;

import java.security.Principal;
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
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping(value = "/pedido")
	public String home(Model model, Principal principal) {
		
		List<Pedido> pedidos = pedidoRepository.findAllByUsername(principal.getName());
		model.addAttribute("pedidos", pedidos);
		
		return "usuario/home";
	}

	
	@GetMapping(value = "/pedido/{status}")
	public String porStatus(Model model, @PathVariable("status") String status, Principal principal) {
		
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUsername(StatusPedido.valueOf(status.toUpperCase()), principal.getName());		
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
	@ExceptionHandler(Exception.class)
	public String onError() {
		return"redirect:/usuario/home";
	}

}
