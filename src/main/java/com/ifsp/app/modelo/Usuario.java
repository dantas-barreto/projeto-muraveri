package com.ifsp.app.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Caderno> cadernos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Caderno> getCadernos() {
        return cadernos;
    }

    public void createCaderno(String titulo) {
        Caderno caderno = new Caderno();
        caderno.setTitulo(titulo);
        caderno.setUsuario(this);
        cadernos.add(caderno);
    }

    public void deleteCaderno(Caderno caderno) {
        cadernos.remove(caderno);
    }
}
