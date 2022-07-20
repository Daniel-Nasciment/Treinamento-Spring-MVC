package br.com.alura.mvc.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.request.LoginRequest;

@RestController
@RequestMapping(value = "/api/login")
public class LoginRest {

	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody @Valid LoginRequest request) {
		
		
		
		return ResponseEntity.ok().build();
	}

	
}
