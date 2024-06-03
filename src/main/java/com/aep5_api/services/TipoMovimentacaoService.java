package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.TipoMovimentacao;
import com.aep5_api.repositories.TipoMovimentacaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoMovimentacaoService {

    @Autowired
    private TipoMovimentacaoRepository repository;

    public List<TipoMovimentacao> findAll() {
        return repository.findAll();
    }

    public TipoMovimentacao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Nenhum tipo de movimentação localizado com o ID: " + id));
    }

    public TipoMovimentacao insert(TipoMovimentacao _tipoMovimentacao) {
        return repository.save(_tipoMovimentacao);
    }

    public TipoMovimentacao update(Long id, TipoMovimentacao _tipoMovimentacao) {
        try {
            TipoMovimentacao tipoMovimentacao = repository.getReferenceById(id);
            tipoMovimentacao.setDescricao(_tipoMovimentacao.getDescricao());

            return repository.save(tipoMovimentacao);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new EntityNotFoundException("Nenhum tipo de movimentação localizado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Nenhum tipo de movimentação localizado com o ID: " + id);
        }
    }

}