package com.aep5_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aep5_api.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}