package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Produto;

@Repository // Define como uma Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
		// JPARepository mapeia a classe produto e a primary key e extende métodos para dar comando na base
		// O repository implementa os métodos para as classes usarem
	
	@Query(value="select id, id_categoria, nome, id_fornecedor from produto where id_categoria = ?;", nativeQuery = true)
	// a interrogação vai ser subtituida no comando
	List<Produto> findByIdCategoria(Integer idCategoria);
}


