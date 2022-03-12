package br.com.alura.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// HELLO é o nome do arquivo HTML na pasta templates
// Caso a anotação do controller fosse @RestController ele retornaria a string em si
@Controller
public class HelloController {

	@GetMapping(value = "/hello")
	public String hello(Model model) {
		
		// MODEL É UM POUCO MAIS ACIMA DA LIB HttpServeltRequest
		// USAMOS PARA ADICIONAR UM ATRIBUTO INFORMANDO O NOME E SEU VALOR
		model.addAttribute("nome", "mundo!!");
		return "hello";
	}

	
}
