package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Preco;

import java.util.ArrayList;
import java.util.List;

public class PrecoService {
    private List<Preco> precos;

    public PrecoService() {
        this.precos = new ArrayList<>();
    }

    public void addPreco(Preco preco) {
        this.precos.add(preco);
    }

    public List<Preco> getAllPrecos() {
        return new ArrayList<>(this.precos);
    }

}