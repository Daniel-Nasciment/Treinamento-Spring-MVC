package br.com.alura.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@GetMapping(value = "/formulario")
	public String novoPedido() {
		return "pedido/formulario";
	}

	
}
