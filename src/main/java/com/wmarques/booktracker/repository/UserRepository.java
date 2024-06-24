package com.wmarques.booktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wmarques.booktracker.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{

    Usuario findByNome(String nome);
}
