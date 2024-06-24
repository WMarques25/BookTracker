package com.wmarques.booktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wmarques.booktracker.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

    Livro findByTitulo(String titulo);
}
