package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Estoque;

import java.util.ArrayList;
import java.util.List;

public class EstoqueService {
    private List<Estoque> estoques;

    public EstoqueService() {
        this.estoques = new ArrayList<>();
    }

    public void addEstoque(Estoque estoque) {
        this.estoques.add(estoque);
    }

    public List<Estoque> getAllEstoques() {
        return new ArrayList<>(this.estoques);
    }

}