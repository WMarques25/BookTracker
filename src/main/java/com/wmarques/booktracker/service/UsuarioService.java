package com.wmarques.booktracker.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmarques.booktracker.model.Usuario;
import com.wmarques.booktracker.repository.UserRepository;

@Service
public class UsuarioService {

    @Autowired
    private UserRepository userRepository;
    
    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
