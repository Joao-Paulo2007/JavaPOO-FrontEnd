package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Acesso;

import java.util.ArrayList;
import java.util.List;

public class AcessoService {
    private List<Acesso> acessos;

    public AcessoService() {
        this.acessos = new ArrayList<>();
    }

    public void addAcesso(Acesso acesso) {
        this.acessos.add(acesso);
    }

    public List<Acesso> getAllAcessos() {
        return new ArrayList<>(this.acessos);
    }

}