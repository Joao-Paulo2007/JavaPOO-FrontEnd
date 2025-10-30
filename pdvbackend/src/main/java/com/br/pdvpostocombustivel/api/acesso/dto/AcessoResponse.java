package com.br.pdvpostocombustivel.api.acesso.dto;

public class AcessoResponse {
    private Long id;
    private String usuario;

    public AcessoResponse() {
    }

    public AcessoResponse(Long id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
