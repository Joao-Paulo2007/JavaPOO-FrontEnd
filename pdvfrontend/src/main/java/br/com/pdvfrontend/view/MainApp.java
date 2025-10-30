package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.acesso.AcessoService;
import com.br.pdvpostocombustivel.api.contato.ContatoService;
import com.br.pdvpostocombustivel.api.custo.CustoService;
import com.br.pdvpostocombustivel.api.estoque.EstoqueService;
import com.br.pdvpostocombustivel.api.pessoa.PessoaService;
import com.br.pdvpostocombustivel.api.preco.PrecoService;
import com.br.pdvpostocombustivel.api.produto.ProdutoService;

import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Gerenciamento PDV");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(300, 400);
            mainFrame.setLayout(new GridLayout(0, 1));

            // Pessoa
            PessoaService pessoaService = new PessoaService();
            JButton btnPessoa = new JButton("Gerenciar Pessoas");
            btnPessoa.addActionListener(e -> new PessoaList(pessoaService).setVisible(true));
            mainFrame.add(btnPessoa);

            // Preco
            PrecoService precoService = new PrecoService();
            JButton btnPreco = new JButton("Gerenciar Preços");
            btnPreco.addActionListener(e -> new PrecoList(precoService).setVisible(true));
            mainFrame.add(btnPreco);

            // Produto
            ProdutoService produtoService = new ProdutoService();
            JButton btnProduto = new JButton("Gerenciar Produtos");
            btnProduto.addActionListener(e -> new ProdutoList(produtoService).setVisible(true));
            mainFrame.add(btnProduto);

            // Custo
            CustoService custoService = new CustoService();
            JButton btnCusto = new JButton("Gerenciar Custos");
            btnCusto.addActionListener(e -> new CustoList(custoService).setVisible(true));
            mainFrame.add(btnCusto);

            // Estoque
            EstoqueService estoqueService = new EstoqueService();
            JButton btnEstoque = new JButton("Gerenciar Estoques");
            btnEstoque.addActionListener(e -> new EstoqueList(estoqueService).setVisible(true));
            mainFrame.add(btnEstoque);

            // Acesso
            AcessoService acessoService = new AcessoService();
            JButton btnAcesso = new JButton("Gerenciar Acessos");
            btnAcesso.addActionListener(e -> new AcessoList(acessoService).setVisible(true));
            mainFrame.add(btnAcesso);

            // Contato
            ContatoService contatoService = new ContatoService();
            JButton btnContato = new JButton("Gerenciar Contatos");
            btnContato.addActionListener(e -> new ContatoList(contatoService).setVisible(true));
            mainFrame.add(btnContato);

            mainFrame.setVisible(true);
        });
    }
}