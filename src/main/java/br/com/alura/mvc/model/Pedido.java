package br.com.alura.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.alura.mvc.enuns.StatusPedido;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

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

	// RESOLVENDO O PROBLEMA org.hibernate.PersistentObjectException: detached entity passed to persist
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private User user;

	// RELACIONAMENTOS TERMINADOS EM ONE (ONETOONE E MANYTOONE) POR PADRÃO É EAGER
	// RELACIONAMENTOS TERMINADOS EM MANY (ONETOMANY E MANYTOMANY) POR PADRÃO É LAZY
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<Oferta> ofertas;

	@Deprecated
	public Pedido() {
	}

	public Pedido(String nomeProduto, String urlImagem, String urlProduto, String descricao, StatusPedido status,
			User user) {
		this.nomeProduto = nomeProduto;
		this.urlImagem = urlImagem;
		this.urlProduto = urlProduto;
		this.descricao = descricao;
		this.status = status;
		this.user = user;
	}


	// CONSTRUTOPR PARA TESTE
	public Pedido(String nomeProduto, String urlImagem, String urlProduto) {
		this.nomeProduto = nomeProduto;
		this.urlImagem = urlImagem;
		this.urlProduto = urlProduto;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Oferta> getOfertas() {
		return Collections.unmodifiableList(this.ofertas);
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void adicionaOferta(Oferta oferta) {
		this.ofertas.add(oferta);
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", nomeProduto=" + nomeProduto + ", urlImagem=" + urlImagem + ", urlProduto="
				+ urlProduto + ", valorProduto=" + valorProduto + ", dataEntrega=" + dataEntrega + ", descricao="
				+ descricao + ", status=" + status + ", user=" + user + "]";
	}

}
