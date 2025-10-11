package br.com.pdvfrontend.service;

import br.com.pdvfrontend.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private List<Produto> produtos;

    public ProdutoService() {
        this.produtos = new ArrayList<>();
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public List<Produto> getAllProdutos() {
        return new ArrayList<>(this.produtos);
    }

}