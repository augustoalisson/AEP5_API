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

import com.aep5_api.entities.Destino;
import com.aep5_api.services.DestinoService;

@RestController
@RequestMapping(value = "/destinos")
public class DestinoResource {

    @Autowired
    private DestinoService service;

    @GetMapping
    public ResponseEntity<List<Destino>> findAll() {
        List<Destino> destinos = service.findAll();
        return ResponseEntity.ok().body(destinos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Destino> findById(@PathVariable Long id) {
        Destino destino = service.findById(id);
        return ResponseEntity.ok().body(destino);
    }

    @PostMapping
    public ResponseEntity<Destino> insert(@RequestBody Destino destino) {
        destino = service.insert(destino);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(destino.getId())
                .toUri();
        return ResponseEntity.created(uri).body(destino);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Destino> update(@PathVariable Long id, @RequestBody Destino destino) {
        destino = service.update(id, destino);
        return ResponseEntity.ok().body(destino);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}