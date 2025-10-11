package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoService {
    private List<Contato> contatos;

    public ContatoService() {
        this.contatos = new ArrayList<>();
    }

    public void addContato(Contato contato) {
        this.contatos.add(contato);
    }

    public List<Contato> getAllContatos() {
        return new ArrayList<>(this.contatos);
    }

}