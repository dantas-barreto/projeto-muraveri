package com.ifsp.app.controller;

import com.ifsp.app.controller.dto.AnotacaoTreeDTO;
import com.ifsp.app.controller.dto.CriarAnotacaoDTO;
import com.ifsp.app.controller.dto.MoverAnotacaoDTO;
import com.ifsp.app.model.Anotacao;
import com.ifsp.app.service.AnotacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anotacoes")
public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    public AnotacaoController(AnotacaoService anotacaoService) {
        this.anotacaoService = anotacaoService;
    }

    @GetMapping("/arvore")
    public List<AnotacaoTreeDTO> getArvoreCompleta() {
        return anotacaoService.obterArvoreCompleta();
    }

    @GetMapping("/{id}/filhos")
    public List<AnotacaoTreeDTO> getFilhos(@PathVariable Long id) {
        return anotacaoService.obterFilhos(id);
    }

    @GetMapping
    public List<Anotacao> findAll() {
        return anotacaoService.findAll();
    }

    @GetMapping("/{id}")
    public Anotacao findById(@PathVariable Long id) {
        return anotacaoService.findById(id);
    }

    @PostMapping
    public Anotacao create(@RequestBody CriarAnotacaoDTO dto) {
        return anotacaoService.save(dto.titulo(), dto.conteudo(), dto.paiId());
    }

    @PutMapping("/{id}")
    public Anotacao update(@PathVariable Long id, @RequestBody CriarAnotacaoDTO dto) {
        return anotacaoService.update(id, dto.titulo(), dto.conteudo());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        anotacaoService.delete(id);
    }

    @PostMapping("/{id}/mover")
    public Anotacao move(
            @PathVariable Long id,
            @RequestBody MoverAnotacaoDTO dto
    ) {
        return anotacaoService.move(id, dto.novoPaiId(), dto.novoPrevId());
    }
}
