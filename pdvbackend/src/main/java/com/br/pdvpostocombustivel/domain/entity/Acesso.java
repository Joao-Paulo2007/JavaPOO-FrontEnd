package com.br.pdvpostocombustivel.domain.entity;

public class Acesso{

    //atributos
    private String usuario;
    private String senha;

    //construtor
    public Acesso(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    public Acesso() {
        super();
    }

    //getters
    public String getUsuario() {
        return usuario;
    }

    //setters
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //getters
    public String getSenha() {
        return senha;
    }

    //setters
    public void setSenha(String senha) {
        this.senha = senha;
    }
}