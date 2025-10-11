package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Produto;
import br.com.pdvfrontend.service.ProdutoService;

import javax.swing.*;
import java.awt.*;

public class ProdutoForm extends JFrame {
    private JTextField txtNome;
    private JTextField txtReferencia;
    private JTextField txtFornecedor;
    private JTextField txtCategoria;
    private JTextField txtMarca;
    private ProdutoService produtoService;
    private ProdutoList produtoList;

    public ProdutoForm(ProdutoService service, ProdutoList list) {
        this.produtoService = service;
        this.produtoList = list;

        setTitle("Cadastro de Produto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Referência:"));
        txtReferencia = new JTextField();
        add(txtReferencia);

        add(new JLabel("Fornecedor:"));
        txtFornecedor = new JTextField();
        add(txtFornecedor);

        add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        add(txtCategoria);

        add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        add(txtMarca);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarProduto());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarProduto() {
        Produto produto = new Produto(
                txtNome.getText(),
                txtReferencia.getText(),
                txtFornecedor.getText(),
                txtCategoria.getText(),
                txtMarca.getText()
        );

        produtoService.addProduto(produto);
        produtoList.atualizarTabela();
        dispose();
    }
}