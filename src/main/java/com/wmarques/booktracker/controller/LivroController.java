package com.wmarques.booktracker.controller;

import java.net.URI;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wmarques.booktracker.model.Livro;
import com.wmarques.booktracker.repository.LivroRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<Livro> save(@RequestBody Livro livro) {
        var livroCriado = livroRepository.save(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(livroCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(livroCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livroRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> findByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroRepository.findByTitulo(titulo));
    }

    
}
