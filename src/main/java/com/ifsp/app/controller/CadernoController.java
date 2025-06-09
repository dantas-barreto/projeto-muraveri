package com.ifsp.app.controller;

import com.ifsp.app.dto.CadernoDTO;
import com.ifsp.app.model.Caderno;
import com.ifsp.app.service.CadernoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadernos")
public class CadernoController {

    private final CadernoService cadernoService;

    public CadernoController(CadernoService cadernoService) {
        this.cadernoService = cadernoService;
    }

    @GetMapping
    public List<Caderno> findAll() {
        return cadernoService.findAll();
    }

    @PostMapping
    public Caderno create(@RequestBody CadernoDTO cadernoDTO) {
        return cadernoService.save(cadernoDTO);
    }

    @GetMapping("/{id}")
    public Caderno findById(@PathVariable Long id) {
        return cadernoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cadernoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Caderno update(@PathVariable Long id, @RequestBody CadernoDTO cadernoDTO) {
        return cadernoService.update(id, cadernoDTO);
    }
}
