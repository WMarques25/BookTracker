package com.wmarques.booktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wmarques.booktracker.model.Marcador;

public interface MarcadorRepository extends JpaRepository<Marcador, Long>{
    
    /**
     * Retorna todos os marcadores de um usuário
     * @param usuarioId
     * @return List<{@link Marcador}>
     */
    List<Marcador> findByUsuarioId(Long idUsuario);

    /**
     * Retorna todos os marcadores de um livro
     * @param livroId
     * @return List<{@link Marcador}>
     */
    List<Marcador> findByLivroId(Long idLivro);

    /**
     * Retorna um marcador de um usuário e um livro
     * @param usuarioId
     * @param livroId
     * @return {@link Marcador}
     */
    Marcador findByUsuarioIdAndLivroId(Long idUsuario, Long idLivro);
}
