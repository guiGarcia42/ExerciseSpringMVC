package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Fornecedor;

@Repository
public interface FornecedoRepository extends JpaRepository<Fornecedor, Long> {
	


}
