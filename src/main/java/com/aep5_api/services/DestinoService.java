package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.Destino;
import com.aep5_api.repositories.DestinoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository repository;

    public List<Destino> findAll() {
        return repository.findAll();
    }

    public Destino findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum destino localizado com o ID: " + id));
    }

    public Destino insert(Destino _destino) {
        return repository.save(_destino);
    }

    public Destino update(Long id, Destino _destino) {
        try {
            Destino destino = repository.getReferenceById(id);
            destino.setDescricao(_destino.getDescricao());
            return repository.save(destino);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new EntityNotFoundException("Nenhum destino localizado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Nenhum destino localizado com o ID: " + id);
        }
    }
}