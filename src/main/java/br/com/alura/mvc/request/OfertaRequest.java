package br.com.alura.mvc.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.alura.mvc.model.Oferta;

public class OfertaRequest {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Long idPedido;

	private String valorProduto;

	private String dataEntrega;

	private String comentario;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
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
