package br.com.alura.mvc.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.mvc.model.User;
import br.com.alura.mvc.repository.UserRepository;

// ESSA CLASE AINDA NÃO É GERENCIADA PELO SPRING, FAÇO ISSO NA CLASSE WEBSECURITYCONFIG
public class AuthenticationFilterToken extends OncePerRequestFilter {

	// COMO ESSE CARA NÃO É UM BEAN NÃO É POSSIVEL USAR AUTOWIRED
	// POREM ADIONANDO ELE A UM CONSTRUTUOR, A CLASSE QUE INSTANCIA
	// AuthenticationFilterToken É UM BEAN DO SPRING
	// LA SIM PODEREMOS INJETAR O TokenService
	private TokenService tokenService;

	private UserRepository userRepository;

	public AuthenticationFilterToken(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	// ESSA LÓGICA INTERCEPTA A REQUISIÇÃO PARA VALIDAR O TOKEN
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// AUTENTICAÇÃO STATELESS NÃO EXISTE MAIS O CONCEITO DE USUÁRIO LOGADO
		// ENTÃO TODA REQUISIÇÃO SERÁ NECESSÁRIO A AUTENTICAÇÃO

		String tokenRecovered = tokenRecover(request);

		boolean valid = tokenService.validate(tokenRecovered);

		if (valid) {
			authenticate(tokenRecovered);
		}

		// JÁ FIZ TUDO O QUE PRECISAVA E A REQUISIÇÃO PODE SEGUIR
		filterChain.doFilter(request, response);

	}

	private void authenticate(String tokenRecovered) {

		// RECUPERAR ID DO USUÁRIO DENTRO DO TOKEN
		Integer idUser = tokenService.getUser(tokenRecovered);

		// BUSCO O USUARIO
		Optional<User> userOptional = userRepository.findById(idUser);

		User user = userOptional.get();

		// O PARAMETRO NULL SERIA O PASSWORD, PORÉM JA FOI PASSADO NO MOMENTO DE
		// AUTENTICAÇÃO
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());

		// A classe AuthenticationManager deve ser utilizada apenas na lógica de autenticação via username/password, para a geração do token.

		// AQUI EU FORÇO O SPRING A VALIDAR O TOKEN/AUTENTICAÇÃO
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String tokenRecover(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());

	}

}
