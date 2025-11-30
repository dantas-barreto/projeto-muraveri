package com.ifsp.app.controller.dto;

public record CriarAnotacaoDTO(
        String titulo,
        String conteudo,
        Long paiId
) {}
