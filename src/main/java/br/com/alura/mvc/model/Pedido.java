package br.com.alura.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alura.mvc.enuns.StatusPedido;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME_PRODUTO")
	private String nomeProduto;

	@Column(name = "URL_IMAGEM")
	private String urlImagem;

	@Column(name = "URL_PRODUTO")
	private String urlProduto;

	@Column(name = "VALOR_PRODUTO")
	private BigDecimal valorProduto;

	@Column(name = "DATA_ENTREGA")
	private LocalDate dataEntrega;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "STATUS_PEDIDO")
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Deprecated
	public Pedido() {
	}

	public Pedido(String nomeProduto, String urlImagem, String urlProduto, String descricao) {
		this.nomeProduto = nomeProduto;
		this.urlImagem = urlImagem;
		this.urlProduto = urlProduto;
		this.descricao = descricao;
	}

	public Pedido(String nomeProduto, String urlImagem, String urlProduto, String descricao, StatusPedido status) {
		this.nomeProduto = nomeProduto;
		this.urlImagem = urlImagem;
		this.urlProduto = urlProduto;
		this.descricao = descricao;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
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

}
