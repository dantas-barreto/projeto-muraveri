package com.ifsp.app.model.repository;

import com.ifsp.app.model.Anotacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
    List<Anotacao> findByPaiIdIsNull();
    List<Anotacao> findByPaiId(Long paiId);
}
