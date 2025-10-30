package com.br.pdvpostocombustivel.api.contato.dto;

public class ContatoResponse {
    private Long id;
    private String telefone;
    private String email;
    private String endereco;

    public ContatoResponse() {
    }

    public ContatoResponse(Long id, String telefone, String email, String endereco) {
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
