package br.com.alura.mvc.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

	public PedidoResponse(Pedido pedido) {

		this.id = pedido.getId();
		this.nomeProduto = pedido.getNomeProduto();
		this.urlImagem = pedido.getUrlImagem();
		this.urlProduto = pedido.getUrlProduto();
		this.valorProduto = pedido.getValorProduto();
		this.dataEntrega = pedido.getDataEntrega();
		this.descricao = pedido.getDescricao();
		this.status = pedido.getStatus();

	}

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

	public static List<PedidoResponse> toResponse(List<Pedido> pedidos) {

		// PedidoResponse::new -> CHAMA O PROPRIO CONSTRUTOR QUE RECEBE O PEDIDO COMO PARAMETRO
		return pedidos.stream().map(PedidoResponse::new).collect(Collectors.toList());

	}
}
