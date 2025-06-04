package com.ifsp.app.controller.fichario;

import com.ifsp.app.modelo.Caderno;
import com.ifsp.app.repository.CadernoRepository;
import com.ifsp.app.modelo.Usuario;
import com.ifsp.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/cadernos")
public class CadernoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadernoRepository cadernoRepository;

    @PostMapping
    public Caderno criar(@PathVariable Long usuarioId, @RequestBody Caderno caderno) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        caderno.setUsuario(usuario);
        return cadernoRepository.save(caderno);
    }

    @GetMapping
    public List<Caderno> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario.getCadernos();
    }
}
