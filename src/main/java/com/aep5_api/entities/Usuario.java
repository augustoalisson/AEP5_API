package com.aep5_api.entities;

import java.io.Serializable;
import java.util.Objects;

import com.aep5_api.entities.enums.NivelAcesso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome_completo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nome_usuario;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nivel_acesso;

    public Usuario() {
    }

    public Usuario(String nome_completo, String email, String nome_usuario, String senha, NivelAcesso nivel_acesso) {
        this.nome_completo = nome_completo;
        this.email = email;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
        this.nivel_acesso = nivel_acesso.getValor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcesso getNivel_acesso() {
        return NivelAcesso.fromString(nivel_acesso);
    }

    public void setNivel_acesso(NivelAcesso nivel_acesso) {
        this.nivel_acesso = nivel_acesso.getValor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id);
    }
}