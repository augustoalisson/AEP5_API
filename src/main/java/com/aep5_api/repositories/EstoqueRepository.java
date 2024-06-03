package com.aep5_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aep5_api.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}