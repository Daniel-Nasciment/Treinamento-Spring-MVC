package br.com.alura.mvc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.model.Pedido;

@Controller
public class HomeController {

	@GetMapping(value = "/home")
	public String home(Model model) {
		
		Pedido pedido = new Pedido();
		pedido.setNomeProduto("iPhone 12 64GB");
		pedido.setUrlImagem("https://m.media-amazon.com/images/I/51misXwIxAL._AC_SX679_.jpg");
		pedido.setUrlProduto("https://www.amazon.com.br/Apple-iPhone-12-128-GB-Azul/dp/B09B2T1Z7Z/ref=sr_1_3_sspa?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=U65I0E5FGRNA&keywords=iphone+12&qid=1647202249&sprefix=iphone+12%2Caps%2C415&sr=8-3-spons&ufe=app_do%3Aamzn1.fos.25548f35-0de7-44b3-b28e-0f56f3f96147&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRlZIVE5WRVlGMTNJJmVuY3J5cHRlZElkPUEwNzQ0MjE4RTJGQ0VSSkdQTFM4JmVuY3J5cHRlZEFkSWQ9QTA1NzQwNjYxOVVQS0NBWExZVExRJndpZGdldE5hbWU9c3BfYXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ==");
		pedido.setValorProduto(new BigDecimal("5641.90"));
		pedido.setDataEntrega(LocalDate.of(2022, 5, 19));
		pedido.setDescricao("Um celular muito legal");
		
		
		
		List<Pedido> pedidos = Arrays.asList(pedido);
		
		// "pedidos" -> Nome da variavel a ser pega pelo thymeleaf em seguida o objeto 
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}

}
