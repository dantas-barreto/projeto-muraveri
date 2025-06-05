package com.ifsp.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ifsp.app.model.enuns.Cor;
import jakarta.persistence.*;

@Entity
public class Caderno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonBackReference
    private Usuario usuario;

    //@OneToMany(mappedBy = "caderno")
    //private List<Anotacao> anotacoes;

    private Cor cor;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
