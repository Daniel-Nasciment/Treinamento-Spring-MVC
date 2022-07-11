package br.com.alura.mvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	// FOI CRIADO DESSA FORMA A PARTIR DO EXEMPLO DO CURSO E DA DOCUMENTAÇÃO DO SPRING
	@Id
	private String username;
	private String password;
	private Boolean enabled;

	// FETCH TYPE LAZY (SE EU CARREGAR O USUARIO NÃO SERÁ CARREGADO OS PEDIDOS DO
	// USUÁRIO)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Pedido> pedidos;



	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
