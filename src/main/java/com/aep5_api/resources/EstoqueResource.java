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

import com.aep5_api.entities.Estoque;
import com.aep5_api.services.EstoqueService;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueResource {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public ResponseEntity<List<Estoque>> findAll() {
        List<Estoque> estoques = service.findAll();
        return ResponseEntity.ok().body(estoques);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estoque> findById(@PathVariable Long id) {
        Estoque estoque = service.findById(id);
        return ResponseEntity.ok().body(estoque);
    }

    @PostMapping
    public ResponseEntity<Estoque> insert(@RequestBody Estoque estoque) {
        estoque = service.insert(estoque);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estoque.getId())
                .toUri();
        return ResponseEntity.created(uri).body(estoque);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estoque> update(@PathVariable Long id, @RequestBody Estoque estoque) {
        estoque = service.update(id, estoque);
        return ResponseEntity.ok().body(estoque);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}