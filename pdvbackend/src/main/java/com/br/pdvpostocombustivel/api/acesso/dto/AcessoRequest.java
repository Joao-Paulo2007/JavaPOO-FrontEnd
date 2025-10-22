package com.br.pdvpostocombustivel.api.acesso.dto;

public class AcessoRequest {
    private String usuario;
    private String senha;

    // Getters and Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
