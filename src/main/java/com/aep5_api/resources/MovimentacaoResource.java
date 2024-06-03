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

import com.aep5_api.entities.Movimentacao;
import com.aep5_api.services.MovimentacaoService;

@RestController
@RequestMapping(value = "/movimentacoes")
public class MovimentacaoResource {

    @Autowired
    private MovimentacaoService service;

    @GetMapping
    public ResponseEntity<List<Movimentacao>> findAll() {
        List<Movimentacao> movimentacoes = service.findAll();
        return ResponseEntity.ok().body(movimentacoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movimentacao> findById(@PathVariable Long id) {
        Movimentacao movimentacao = service.findById(id);
        return ResponseEntity.ok().body(movimentacao);
    }

    @PostMapping
    public ResponseEntity<Movimentacao> insert(@RequestBody Movimentacao movimentacao) {
        movimentacao = service.insert(movimentacao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movimentacao.getId())
                .toUri();
        return ResponseEntity.created(uri).body(movimentacao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Movimentacao> update(@PathVariable Long id, @RequestBody Movimentacao movimentacao) {
        movimentacao = service.update(id, movimentacao);
        return ResponseEntity.ok().body(movimentacao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}