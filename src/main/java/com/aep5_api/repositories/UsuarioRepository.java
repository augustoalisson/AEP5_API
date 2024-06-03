package com.aep5_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aep5_api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}