package br.com.alura.mvc.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.model.Oferta;

public class OfertaRequest {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Integer idPedido;

	// ^ - COMEÇAR
	// d - DIGITO
	// d+ - VARIOS DIGITOS
	// ()? - OPCIONAL
	// \\ QUALQUER COISA QUE É SEGUIDA DE \\ É OBRIGATORIO
	// \\. - OBRIGATORIO O '.'  
	// d+{2} - LIMITE DE CARACTERES ENTRE {}
	// $ - ACABOU A STRING / NÃO ESPERO MAIS NADA
	
	@Pattern(regexp = "^\\d+(\\.\\d{2})")
	@NotBlank
	private String valorProduto;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotBlank
	private String dataEntrega;

	private String comentario;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(String valorProduto) {
		this.valorProduto = valorProduto;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Oferta toModel() {

		return new Oferta(new BigDecimal(this.valorProduto), LocalDate.parse(this.dataEntrega, formatter), this.comentario);
		
	}

}
