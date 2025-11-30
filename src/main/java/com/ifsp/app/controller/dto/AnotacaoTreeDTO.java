package com.ifsp.app.controller.dto;

import java.util.List;

public record AnotacaoTreeDTO(
        Long id,
        String titulo,
        String conteudo,
        List<AnotacaoTreeDTO> filhos
) {
}
