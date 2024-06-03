package com.aep5_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aep5_api.entities.TipoMovimentacao;
import com.aep5_api.services.TipoMovimentacaoService;

@RestController
@RequestMapping(value = "/tipomovimentacoes")
public class TipoMovimentacaoResource {

    @Autowired
    private TipoMovimentacaoService service;

    @GetMapping
    public ResponseEntity<List<TipoMovimentacao>> findAll() {
        List<TipoMovimentacao> tipoMovimentacoes = service.findAll();
        return ResponseEntity.ok().body(tipoMovimentacoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoMovimentacao> findById(@PathVariable Long id) {
        TipoMovimentacao tipoMovimentacao = service.findById(id);
        return ResponseEntity.ok().body(tipoMovimentacao);
    }

    @PostMapping
    public ResponseEntity<TipoMovimentacao> insert(@RequestBody TipoMovimentacao tipoMovimentacao) {
        tipoMovimentacao = service.insert(tipoMovimentacao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tipoMovimentacao.getId()).toUri();
        return ResponseEntity.created(uri).body(tipoMovimentacao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoMovimentacao> update(@PathVariable Long id,
            @RequestBody TipoMovimentacao tipoMovimentacao) {
        tipoMovimentacao = service.update(id, tipoMovimentacao);
        return ResponseEntity.ok().body(tipoMovimentacao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}