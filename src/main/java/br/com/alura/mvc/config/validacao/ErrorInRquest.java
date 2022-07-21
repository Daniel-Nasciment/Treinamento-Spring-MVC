package br.com.alura.mvc.config.validacao;

// ESSE DTO REPRESENTA O ERRO DE ALGUM CAMPO
public class ErrorInRquest {

	private String field;

	private String message;

	public ErrorInRquest(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
