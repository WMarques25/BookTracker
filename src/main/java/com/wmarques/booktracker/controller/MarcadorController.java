package com.wmarques.booktracker.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wmarques.booktracker.model.Marcador;
import com.wmarques.booktracker.repository.LivroRepository;
import com.wmarques.booktracker.repository.MarcadorRepository;
import com.wmarques.booktracker.service.UsuarioService;


@RestController
@RequestMapping("/marcador")
public class MarcadorController {

    @Autowired
    private MarcadorRepository marcadorRepository;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Marcador>> listarPorUsuario(@PathVariable Long idUsuario) {
        var page = marcadorRepository.findByUsuarioId(idUsuario);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/usuario/{idUsuario}/livro/{idLivro}")
    public ResponseEntity<Marcador> buscarPorUsuarioELivro(@PathVariable Long idUsuario, @PathVariable Long idLivro) {
        return ResponseEntity.ok(marcadorRepository.findByUsuarioIdAndLivroId(idUsuario, idLivro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marcador> findById(@PathVariable Long id) {
        return ResponseEntity.ok(marcadorRepository.findById(id).orElseThrow());
    }

    @PostMapping("/usuario/{idUsuario}/livro/{idLivro}/pagina/{pagina}")
    @Transactional
    public ResponseEntity<Marcador> marcar(@PathVariable int pagina, @PathVariable Long idUsuario, @PathVariable Long idLivro){
        if(marcadorRepository.findByUsuarioIdAndLivroId(idUsuario, idLivro) != null){
            return criarMarcador(pagina, idUsuario, idLivro);
        }else {
            return atualizarMarcador(pagina, idUsuario, idLivro);
        }
    }

    private ResponseEntity<Marcador> atualizarMarcador(int pagina, Long idUsuario, Long idLivro) {
        var marcadorExistente = marcadorRepository.findByUsuarioIdAndLivroId(idUsuario, idLivro);
        marcadorExistente.setPagina(pagina);

        var marcadorAtualizado = marcadorRepository.save(marcadorExistente);

        return ResponseEntity.ok(marcadorAtualizado);
    }

    public ResponseEntity<Marcador> criarMarcador(int pagina, Long idUsuario, Long idLivro) {
    
        var usuario = usuarioService.findById(idUsuario);
        var livro = livroRepository.findById(idLivro).orElseThrow();
        
        var marcador = new Marcador(pagina, usuario, livro);
        
        var marcadorCriado = marcadorRepository.save(marcador);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(marcadorCriado.getId()).toUri();
        
        return ResponseEntity.created(uri).body(marcadorCriado);
    }
    
}
