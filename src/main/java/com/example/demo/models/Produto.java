package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity //Define a classe como uma entity
public class Produto {
	
	@Id // Identifica a primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Auto increment do ID
	private Long id; 
	
	@NotBlank(message = "Favor preencher o nome do produto")
	private String nome;
		
	private Long idCategoria; 
	
	@NotNull(message="O fornecedor é obrigatório!")
	@Min(value = 1, message = "Favor selecionar o fornecedor")
	private Long idFornecedor; 
	
	
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
