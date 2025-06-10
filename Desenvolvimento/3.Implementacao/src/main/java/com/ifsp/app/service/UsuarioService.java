package com.ifsp.app.service;

import com.ifsp.app.controller.dto.UsuarioDTO;
import com.ifsp.app.model.Usuario;
import com.ifsp.app.model.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public Usuario save(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = findById(id);
        if (usuarioDTO.getNome() != null) {
            usuario.setNome(usuarioDTO.getNome());
        }
        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        return usuarioRepository.save(usuario);
    }
}
