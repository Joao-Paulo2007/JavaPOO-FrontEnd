package com.br.pdvpostocombustivel.domain.entity;

public class Contato{

    //atributos
    private String telefone;
    private String email;
    private String endereco;

    //construtor
    public Contato(String telefone, String email, String endereco){
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public Contato() {
        super();
    }

    //getters
    public String getTelefone() {
        return telefone;
    }

    //setters
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    //getters
    public String getEmail() {
        return email;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    //getters
    public String getEndereco() {
        return endereco;
    }

    //setters
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}