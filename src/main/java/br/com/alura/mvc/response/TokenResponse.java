package br.com.alura.mvc.response;

public class TokenResponse {

	private String token;
	private String type;

	public TokenResponse(String token, String type) {
		this.token = token;
		this.type = type;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getString() {
		return type;
	}

	public void setString(String string) {
		this.type = string;
	}

	
}
