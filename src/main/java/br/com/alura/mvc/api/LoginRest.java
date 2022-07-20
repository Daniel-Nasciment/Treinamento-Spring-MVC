package br.com.alura.mvc.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.request.LoginRequest;
import br.com.alura.mvc.security.TokenService;

@RestController
@RequestMapping(value = "/api/login")
public class LoginRest {
	
	// O SPRING NÃO CONSGUE INJETAR ESSE CARA, ENTÃO FAREI ISSO NA CLASSE WEBSECURITYCONFIG
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody @Valid LoginRequest request) {
		
		UsernamePasswordAuthenticationToken login = request.toLoginAuthentication();
		
		try {
			
			Authentication authentication = authManager.authenticate(login);
			
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(token);
			
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
			
		}
	}

	
}
