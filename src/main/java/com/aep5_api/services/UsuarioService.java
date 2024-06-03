package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.Usuario;
import com.aep5_api.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário localizado com o ID: " + id));
	}

	public Usuario insert(Usuario _usuario) {
		return repository.save(_usuario);
	}

	public Usuario update(Long id, Usuario _usuario) {
		try {
			Usuario usuario = repository.getReferenceById(id);
			usuario.setNome_completo(_usuario.getNome_completo());
			usuario.setEmail(_usuario.getEmail());
			usuario.setNome_usuario(_usuario.getNome_usuario());
			usuario.setNivel_acesso(_usuario.getNivel_acesso());
			usuario.setSenha(_usuario.getSenha());
			return repository.save(usuario);
		} catch (JpaObjectRetrievalFailureException e) {
			throw new EntityNotFoundException("Nenhum usuário localizado com o ID: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Nenhum usuário localizado com o ID: " + id);
		}
	}
}
