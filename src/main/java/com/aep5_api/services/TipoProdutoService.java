package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.TipoProduto;
import com.aep5_api.repositories.TipoProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository repository;

    public List<TipoProduto> findAll() {
        return repository.findAll();
    }

    public TipoProduto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum tipo de produto localizado com o ID: " + id));
    }

    public TipoProduto insert(TipoProduto _tipoProduto) {
        return repository.save(_tipoProduto);
    }

    public TipoProduto update(Long id, TipoProduto _tipoProduto) {
        try {
            TipoProduto tipoProduto = repository.getReferenceById(id);
            tipoProduto.setDescricao(_tipoProduto.getDescricao());

            return repository.save(tipoProduto);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new EntityNotFoundException("Nenhum tipo de produto localizado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Nenhum tipo de produto localizado com o ID: " + id);
        }
    }

}