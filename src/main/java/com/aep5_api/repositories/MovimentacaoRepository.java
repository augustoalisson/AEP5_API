package com.aep5_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aep5_api.entities.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}