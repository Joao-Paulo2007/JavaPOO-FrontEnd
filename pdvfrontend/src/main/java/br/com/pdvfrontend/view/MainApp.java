package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.acesso.AcessoService;
import com.br.pdvpostocombustivel.api.contato.ContatoService;
import com.br.pdvpostocombustivel.api.custo.CustoService;
import com.br.pdvpostocombustivel.api.estoque.EstoqueService;
import com.br.pdvpostocombustivel.api.pessoa.PessoaService;
import com.br.pdvpostocombustivel.api.preco.PrecoService;
import com.br.pdvpostocombustivel.api.produto.ProdutoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApp.class, args);

        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Gerenciamento PDV");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(300, 400);
            mainFrame.setLayout(new GridLayout(0, 1));

            // Pessoa
            PessoaService pessoaService = context.getBean(PessoaService.class);
            JButton btnPessoa = new JButton("Gerenciar Pessoas");
            btnPessoa.addActionListener(e -> new PessoaList(pessoaService).setVisible(true));
            mainFrame.add(btnPessoa);

            // Preco
            PrecoService precoService = context.getBean(PrecoService.class);
            JButton btnPreco = new JButton("Gerenciar Preços");
            btnPreco.addActionListener(e -> new PrecoList(precoService).setVisible(true));
            mainFrame.add(btnPreco);

            // Produto
            ProdutoService produtoService = context.getBean(ProdutoService.class);
            JButton btnProduto = new JButton("Gerenciar Produtos");
            btnProduto.addActionListener(e -> new ProdutoList(produtoService).setVisible(true));
            mainFrame.add(btnProduto);

            // Custo
            CustoService custoService = context.getBean(CustoService.class);
            JButton btnCusto = new JButton("Gerenciar Custos");
            btnCusto.addActionListener(e -> new CustoList(custoService).setVisible(true));
            mainFrame.add(btnCusto);

            // Estoque
            EstoqueService estoqueService = context.getBean(EstoqueService.class);
            JButton btnEstoque = new JButton("Gerenciar Estoques");
            btnEstoque.addActionListener(e -> new EstoqueList(estoqueService).setVisible(true));
            mainFrame.add(btnEstoque);

            // Acesso
            AcessoService acessoService = context.getBean(AcessoService.class);
            JButton btnAcesso = new JButton("Gerenciar Acessos");
            btnAcesso.addActionListener(e -> new AcessoList(acessoService).setVisible(true));
            mainFrame.add(btnAcesso);

            // Contato
            ContatoService contatoService = context.getBean(ContatoService.class);
            JButton btnContato = new JButton("Gerenciar Contatos");
            btnContato.addActionListener(e -> new ContatoList(contatoService).setVisible(true));
            mainFrame.add(btnContato);

            mainFrame.setVisible(true);
        });
    }
}