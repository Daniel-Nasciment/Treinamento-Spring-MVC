package br.com.alura.mvc.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.mvc.enuns.StatusPedido;
import br.com.alura.mvc.model.Pedido;

public class PedidoResponse {

	private Integer id;

	private String nomeProduto;

	private String urlImagem;

	private String urlProduto;

	private BigDecimal valorProduto;

	private LocalDate dataEntrega;

	private String descricao;

	private StatusPedido status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public void toResponse(Pedido p) {

		this.id = p.getId();
		this.nomeProduto = p.getNomeProduto();
		this.urlImagem = p.getUrlImagem();
		this.urlProduto = p.getUrlProduto();
		this.valorProduto = p.getValorProduto();
		this.dataEntrega = p.getDataEntrega();
		this.descricao = p.getDescricao();
		this.status = p.getStatus();
		
	}

}
