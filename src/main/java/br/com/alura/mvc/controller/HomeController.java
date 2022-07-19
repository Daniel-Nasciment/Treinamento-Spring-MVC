package br.com.alura.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
		// PRINCIPAL EU CONSIGO RECUPERAR O USUARIO LOGADO
		
		// CRIANDO ORDENAÇÃO PARA CONSULTA
		Sort sort = Sort.by("dataEntrega").descending();
		// CRIANDO PAGINAÇÃO 
		PageRequest paginacao = PageRequest.of(0, 1, sort);
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		
		
		// "pedidos" -> Nome da variavel a ser pega pelo thymeleaf em seguida o objeto 
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
	// MÉTODO AUXILIAR PARA A REQUISIÇÃO DE ALGUMA URL DESCONHECIDA PELA API
	@ExceptionHandler(Exception.class)
	public String onError() {
		return"redirect:/home";
	}

	
}
