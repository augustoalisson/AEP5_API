package com.aep5_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.aep5_api.entities.Movimentacao;
import com.aep5_api.repositories.MovimentacaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    public List<Movimentacao> findAll() {
        return repository.findAll();
    }

    public Movimentacao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma movimentação localizada com o ID: " + id));
    }

    public Movimentacao insert(Movimentacao _movimentacao) {
        return repository.save(_movimentacao);
    }

    public Movimentacao update(Long id, Movimentacao _movimentacao) {
        try {
            Movimentacao movi = repository.getReferenceById(id);
            movi.setQuantidade(_movimentacao.getQuantidade());
            movi.setOrcamento(_movimentacao.getOrcamento());
            movi.setTipoMovimentacao(_movimentacao.getTipoMovimentacao());
            movi.setDestino(_movimentacao.getDestino());
            movi.setEstoque(_movimentacao.getEstoque());
            return repository.save(movi);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new EntityNotFoundException("Nenhuma movimentação localizada com o ID: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Nenhuma movimentação localizada com o ID: " + id);
        }
    }
}