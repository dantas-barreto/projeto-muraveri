package com.ifsp.app.service;

import com.ifsp.app.model.Caderno;
import com.ifsp.app.model.repository.CadernoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadernoService {

    private final CadernoRepository cadernoRepository;

    public CadernoService(CadernoRepository cadernoRepository) {
        this.cadernoRepository = cadernoRepository;
    }

    public List<Caderno> listarTodos() {
        return cadernoRepository.findAll();
    }

    public Caderno salvar (Caderno caderno) {
        return cadernoRepository.save(caderno);
    }

    public Caderno buscarPorId(Long id) {
        return cadernoRepository.findById(id).orElse(null);
    }
}
