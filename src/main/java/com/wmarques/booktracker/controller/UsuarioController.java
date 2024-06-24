package com.wmarques.booktracker.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wmarques.booktracker.model.Usuario;
import com.wmarques.booktracker.service.UsuarioService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        var usuarioCriado = usuarioService.save(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(usuarioCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        
        return ResponseEntity.ok(usuarioService.findById(id));
    }
}
