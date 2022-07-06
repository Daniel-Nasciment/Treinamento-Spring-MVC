package br.com.alura.mvc.request;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.model.Pedido;


public class PedidoRequest {

	@NotBlank
	private String nomeProduto;

	@NotBlank
	private String urlProduto;

	@NotBlank
	private String imagemProduto;

	private String descricaoProduto;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public String getImagemProduto() {
		return imagemProduto;
	}

	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Pedido toModel() {
		return new Pedido(this.nomeProduto, this.imagemProduto, this.urlProduto, this.descricaoProduto);
		
	}

}
