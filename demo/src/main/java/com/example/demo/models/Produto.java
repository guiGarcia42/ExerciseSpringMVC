package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //Define a classe como uma entity
public class Produto {

	@Id // Identifica a primary key
	@GeneratedValue(strategy=GenerationType.AUTO) //Auto increment do ID
	private Long id;
	private String nome;
	private Long idCategoria, idFornecedor;
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
