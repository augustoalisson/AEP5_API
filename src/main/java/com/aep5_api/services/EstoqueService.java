package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.Estoque;
import com.aep5_api.repositories.EstoqueRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    public List<Estoque> findAll() {
        return repository.findAll();
    }

    public Estoque findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estoque localizado com o ID: " + id));
    }

    public Estoque insert(Estoque _estoque) {
        return repository.save(_estoque);
    }

    public Estoque update(Long id, Estoque _estoque) {
        try {
            Estoque estoque = repository.getReferenceById(id);
            estoque.setQuantidade(_estoque.getQuantidade());
            estoque.setValor(_estoque.getValor());
            estoque.setData_validade(_estoque.getData_validade());
            estoque.setProduto(_estoque.getProduto());
            return repository.save(estoque);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new EntityNotFoundException("Nenhum estoque localizado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Nenhum estoque localizado com o ID: " + id);
        }
    }
}
