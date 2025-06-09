package com.ifsp.app.service;

import com.ifsp.app.dto.CadernoDTO;
import com.ifsp.app.model.Caderno;
import com.ifsp.app.model.Usuario;
import com.ifsp.app.model.repository.CadernoRepository;
import com.ifsp.app.model.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CadernoService {

    private final CadernoRepository cadernoRepository;
    private final UsuarioRepository usuarioRepository;

    public CadernoService(CadernoRepository cadernoRepository, UsuarioRepository usuarioRepository) {
        this.cadernoRepository = cadernoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Caderno> findAll() {
        return cadernoRepository.findAll();
    }

    public Caderno findById(Long id) {
        return cadernoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Caderno não encontrado"));
    }

    public Caderno save(CadernoDTO cadernoDTO) {
        Usuario usuario = usuarioRepository.findById(cadernoDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Caderno caderno = new Caderno();
        caderno.setTitulo(cadernoDTO.getTitulo());
        caderno.setUsuario(usuario);
        return cadernoRepository.save(caderno);
    }

    public void deleteById(Long id) {
        if (!cadernoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Caderno não encontrado");
        }
        cadernoRepository.deleteById(id);
    }

    public Caderno update(Long id , CadernoDTO cadernoDTO) {
        Caderno caderno = findById(id);
        if (cadernoDTO.getTitulo() != null) {
            caderno.setTitulo(cadernoDTO.getTitulo());
        }
        if (cadernoDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(cadernoDTO.getUsuarioId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Usuário não encontrado"));
        }
        return cadernoRepository.save(caderno);
    }
}
