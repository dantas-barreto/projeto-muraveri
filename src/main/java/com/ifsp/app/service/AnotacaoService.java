package com.ifsp.app.service;

import com.ifsp.app.controller.dto.AnotacaoTreeDTO;
import com.ifsp.app.model.Anotacao;
import com.ifsp.app.model.repository.AnotacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    public AnotacaoService(AnotacaoRepository anotacaoRepository) {
        this.anotacaoRepository = anotacaoRepository;
    }

    public List<AnotacaoTreeDTO> obterArvoreCompleta() {
        List<Anotacao> root = anotacaoRepository.findByPaiIdIsNull();
        return root.stream()
                .map(this::montarArvore)
                .toList();
    }

    public List<AnotacaoTreeDTO> obterFilhos(Long id) {
        return anotacaoRepository.findByPaiId(id)
                .stream()
                .map(this::montarArvore)
                .toList();
    }

    public AnotacaoTreeDTO montarArvore(Anotacao anotacao) {
        List<AnotacaoTreeDTO> filhos = anotacaoRepository.findByPaiId(anotacao.getId())
                .stream()
                .map(this::montarArvore)
                .toList();

        return new AnotacaoTreeDTO(
                anotacao.getId(),
                anotacao.getTitulo(),
                anotacao.getConteudo(),
                filhos
        );
    }

    public List<Anotacao> findAll() {
        return anotacaoRepository.findAll();
    }

    public Anotacao findById(Long id) {
        return anotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotação não encontrada"));
    }

    public Anotacao save(String titulo, String conteudo, Long paiId) {

        Long prev = getUltimoFilho(paiId);

        Anotacao nova = new Anotacao(titulo, conteudo, paiId);
        nova.setPrevId(prev);
        nova.setNextId(null);

        Anotacao salva = anotacaoRepository.save(nova);

        if (prev != null) {
            Anotacao anterior = findById(prev);
            anterior.setNextId(salva.getId());
            anotacaoRepository.save(anterior);
        }

        return salva;
    }

    private Long getUltimoFilho(Long paiId) {
        List<Anotacao> filhos = anotacaoRepository.findByPaiId(paiId);

        return filhos.stream()
                .filter(a -> a.getNextId() == null)
                .map(Anotacao::getId)
                .findFirst()
                .orElse(null);
    }

    public Anotacao update(Long id, String titulo, String conteudo) {
        Anotacao a = findById(id);
        a.setTitulo(titulo);
        a.setConteudo(conteudo);
        return anotacaoRepository.save(a);
    }

    public void delete(Long id) {
        Anotacao atual = findById(id);

        Long prev = atual.getPrevId();
        Long next = atual.getNextId();

        if (prev != null) {
            Anotacao p = findById(prev);
            p.setNextId(next);
            anotacaoRepository.save(p);
        }

        if (next != null) {
            Anotacao n = findById(next);
            n.setPrevId(prev);
            anotacaoRepository.save(n);
        }

        anotacaoRepository.delete(atual);

        List<Anotacao> filhos = anotacaoRepository.findByPaiId(id);
        filhos.forEach(f -> delete(f.getId()));
    }

    public Anotacao move(Long id, Long novoPaiId, Long novoPrevId) {
        Anotacao atual = findById(id);

        Long prev = atual.getPrevId();
        Long next = atual.getNextId();

        if (prev != null) {
            Anotacao p = findById(prev);
            p.setNextId(next);
            anotacaoRepository.save(p);
        }

        if (next != null) {
            Anotacao n = findById(prev);
            n.setPrevId(prev);
            anotacaoRepository.save(n);
        }

        atual.setPaiId(novoPaiId);
        atual.setPrevId(novoPrevId);

        Long novoNextId = null;

        if (novoPrevId != null) {
            Anotacao novoPrev = findById(novoPrevId);
            novoNextId = novoPrev.getNextId();
            novoPrev.setNextId(id);
            anotacaoRepository.save(novoPrev);
        }

        if (novoNextId != null) {
            Anotacao novoNext = findById(novoNextId);
            novoNext.setPrevId(id);
            anotacaoRepository.save(novoNext);
        }

        atual.setNextId(novoNextId);

        return anotacaoRepository.save(atual);
    }
}