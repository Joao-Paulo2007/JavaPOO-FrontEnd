package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class PessoaService {

    private final List<Pessoa> pessoas = new ArrayList<>();

    public PessoaService() {

    }

    public List<Pessoa> listPessoas() {
        return pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public void removePessoa(int index) {
        if (index >= 0 && index < pessoas.size()) {
            pessoas.remove(index);
        }
    }
}