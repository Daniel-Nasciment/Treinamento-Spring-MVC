package br.com.alura.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	// FOI NECESSARIO PASSAR ESSE OBJ NO PARAMETRO POR CONTA DO THYMELEAF QUE FAZ USO NA RENDERIZAÇÃO DO FORMULARIO
	public String formulario(PedidoRequest request) {
		return "pedido/formulario";
	}

	
	// @RequestMapping barra alguns media types
	@PostMapping(value = "/novo")
	public String novoPedido(@Valid PedidoRequest request, BindingResult result) {

	// O Spring através do BindingResult retorna o resultado das validações
	// E quando faço a utilização dele a validação não joga o erro, é tratado pelo código
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		
		Pedido pedido = request.toModel();
		
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
	}

	
}
