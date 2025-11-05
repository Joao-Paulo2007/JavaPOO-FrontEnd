package com.br.pdvpostocombustivel.api.acesso.dto;

public class AcessoRequest {
    private String usuario;
    private String senha;

    public AcessoRequest(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

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
