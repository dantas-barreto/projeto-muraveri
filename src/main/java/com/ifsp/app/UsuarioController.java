package com.ifsp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    @PostMapping
    public Usuario criar(@RequestBody Usuario u) {
        return repo.save(u);
    }

    @GetMapping
    public List<Usuario> listar() {
        return repo.findAll();
    }
}
