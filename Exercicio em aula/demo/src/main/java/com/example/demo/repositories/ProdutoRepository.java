package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	// JPARepository mapeia a classe produto e a primary key e extende métodos para dar comando na base
	// O repository implementa os métodos para as classes usarem

}
