package br.com.alura.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.PedidoRepository;
import br.com.alura.mvc.request.PedidoRequest;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping(value = "/formulario")
	public String novoPedido() {
		return "pedido/formulario";
	}

	
	// @RequestMapping barra alguns media types
	@PostMapping(value = "/novo")
	public String novoPedido(@Valid PedidoRequest request) {

		Pedido pedido = request.toModel();
		
		pedidoRepository.save(pedido);
		
		return "home";
	}

	
}
