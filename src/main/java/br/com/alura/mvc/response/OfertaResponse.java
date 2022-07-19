package br.com.alura.mvc.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.mvc.model.Oferta;

public class OfertaResponse {

	private Integer id;

	private BigDecimal valor;

	private LocalDate dataEntrega;

	private String comentario;

	private Integer idPedido;

	public OfertaResponse(Oferta oferta) {
		
		this.id = oferta.getId();
		this.valor = oferta.getValor();
		this.dataEntrega = oferta.getDataEntrega();
		this.comentario = oferta.getComentario();
		this.idPedido = oferta.getPedido().getId();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	
}
