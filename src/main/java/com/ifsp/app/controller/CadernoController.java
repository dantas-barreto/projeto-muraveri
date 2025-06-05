package com.ifsp.app.controller;

import com.ifsp.app.model.Caderno;
import com.ifsp.app.service.CadernoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/cadernos")
public class CadernoController {

    private final CadernoService cadernoService;

    public CadernoController(CadernoService cadernoService) {
        this.cadernoService = cadernoService;
    }

    @GetMapping
    public List<Caderno> listar() {
        return cadernoService.listarTodos();
    }

    @PostMapping
    public Caderno criar(@RequestBody Caderno caderno) {
        return cadernoService.salvar(caderno);
    }

    @GetMapping("/{id}")
    public Caderno buscarPorId(@PathVariable Long id) {
        return cadernoService.buscarPorId(id);
    }
}
