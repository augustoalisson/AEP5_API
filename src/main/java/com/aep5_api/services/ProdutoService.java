package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.Produto;
import com.aep5_api.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Produto findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Nenhum produto localizado com o ID: " + id));
	}

	public Produto insert(Produto _produto) {
		return repository.save(_produto);
	}

	public Produto update(Long id, Produto _produto) {
		try {
			Produto produto = repository.getReferenceById(id);
			produto.setNomeProduto(_produto.getNomeProduto());
			produto.setTipoProduto(_produto.getTipoProduto());

			return repository.save(produto);
		} catch (JpaObjectRetrievalFailureException e) {
			throw new EntityNotFoundException("Nenhum produto localizado com o ID: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Nenhum produto localizado com o ID: " + id);
		}
	}
}