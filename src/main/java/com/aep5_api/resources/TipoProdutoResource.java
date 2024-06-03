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

import com.aep5_api.entities.TipoProduto;
import com.aep5_api.services.TipoProdutoService;

@RestController
@RequestMapping(value = "/tipoprodutos")
public class TipoProdutoResource {

    @Autowired
    private TipoProdutoService service;

    @GetMapping
    public ResponseEntity<List<TipoProduto>> findAll() {
        List<TipoProduto> tipoProdutos = service.findAll();
        return ResponseEntity.ok().body(tipoProdutos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoProduto> findById(@PathVariable Long id) {
        TipoProduto tipoProduto = service.findById(id);
        return ResponseEntity.ok().body(tipoProduto);
    }

    @PostMapping
    public ResponseEntity<TipoProduto> insert(@RequestBody TipoProduto tipoProduto) {
        tipoProduto = service.insert(tipoProduto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoProduto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(tipoProduto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoProduto> update(@PathVariable Long id, @RequestBody TipoProduto tipoProduto) {
        tipoProduto = service.update(id, tipoProduto);
        return ResponseEntity.ok().body(tipoProduto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}