package com.aep5_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aep5_api.entities.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {

}