package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Custo;

import java.util.ArrayList;
import java.util.List;

public class CustoService {
    private List<Custo> custos;

    public CustoService() {
        this.custos = new ArrayList<>();
    }

    public void addCusto(Custo custo) {
        this.custos.add(custo);
    }

    public List<Custo> getAllCustos() {
        return new ArrayList<>(this.custos);
    }

}