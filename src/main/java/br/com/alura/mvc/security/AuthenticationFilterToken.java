package br.com.alura.mvc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

// ESSA CLASE AINDA NÃO É GERENCIADA PELO SPRING, FAÇO ISSO NA CLASSE WEBSECURITYCONFIG
public class AuthenticationFilterToken extends OncePerRequestFilter {

	// COMO ESSE CARA NÃO É UM BEAN NÃO É POSSIVEL USAR AUTOWIRED
	// POREM ADIONANDO ELE A UM CONSTRUTUOR, A CLASSE QUE INSTANCIA AuthenticationFilterToken É UM BEAN DO SPRING
	// LA SIM PODEREMOS INJETAR O TokenService
	private TokenService tokenService;
	
	public AuthenticationFilterToken(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	// ESSA LÓGICA INTERCEPTA A REQUISIÇÃO PARA VALIDAR O TOKEN
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// AUTENTICAÇÃO STATELESS NÃO EXISTE MAIS O CONCEITO DE USUÁRIO LOGADO
		// ENTÃO TODA REQUISIÇÃO SERÁ NECESSÁRIO A AUTENTICAÇÃO
		
		
		String tokenRecovered = tokenRecover(request);
		
		
		boolean valid = tokenService.validate(tokenRecovered);
		
		System.out.println(valid);
		
		// JÁ FIZ TUDO O QUE PRECISAVA E A REQUISIÇÃO PODE SEGUIR
		filterChain.doFilter(request, response);
		
	}

	private String tokenRecover(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
	
		return token.substring(7, token.length());
		
	}
	
}
