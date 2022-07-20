package br.com.alura.mvc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	// ASSIM INJETAMOS VALORES DO APP PROPERTIES
	@Value("${mudi.jwt.expiration}")
	private Long expiration;

	@Value("${mudi.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		// getPrincipal DEVOLVE UM OBJECT ENTÃO PRECISO FAZER UM CAST PARA MINHA CLASSE
		// QUE REPRESENTE UM USUÁRIO
		User principal = (User) authentication.getPrincipal();

		Date dateStart = new Date();

		Date dateEnd = new Date(dateStart.getTime() + expiration);

		return Jwts.builder()
				// QUEM ESTÁ GERANDO O TOKEN
				.setIssuer("API MUDI")
				// USUÁRIO ID
				.setSubject(principal.getId().toString())
				// QUANDO FOI GERADO (DATA)
				.setIssuedAt(dateStart).setExpiration(dateEnd)
				// TOKEN TEM QUE SER CRIPTOGRAFADO
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean validate(String tokenRecovered) {

		try {
			// ESSE MÉTODO CASO O TOKEN ESTEJA VALIDO DEVOLVERA UM OBJETO
			// SE NESTIVER INVALIDO OU NULL ELE JOGA UMA EXCEPTION
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(tokenRecovered);
			
			// SE CHEGOU AQUI O TOKEN ESTÁ VALIDO
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
