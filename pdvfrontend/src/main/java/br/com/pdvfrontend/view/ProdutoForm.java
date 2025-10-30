package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Produto;
import com.br.pdvpostocombustivel.api.produto.ProdutoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProdutoForm extends JFrame {
    private JTextField txtNome;
    private JTextField txtReferencia;
    private JTextField txtFornecedor;
    private JTextField txtCategoria;
    private JTextField txtMarca;
    private final ProdutoService produtoService;
    private final ProdutoList produtoList;

    public ProdutoForm(ProdutoService service, ProdutoList list) {
        this.produtoService = service;
        this.produtoList = list;
        initComponents();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Cadastro de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

        Color roxo = new Color(128, 0, 128);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel(" CADASTRO DE PRODUTO ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 18));
        header.setOpaque(true);
        header.setBackground(roxo);
        header.setForeground(branco);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(preto, 2),
                new EmptyBorder(10, 0, 10, 0)
        ));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        formPanel.setBackground(branco);

        txtNome = new JTextField();
        txtReferencia = new JTextField();
        txtFornecedor = new JTextField();
        txtCategoria = new JTextField();
        txtMarca = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);

        formPanel.add(new JLabel("Referência:"));
        formPanel.add(txtReferencia);

        formPanel.add(new JLabel("Fornecedor:"));
        formPanel.add(txtFornecedor);

        formPanel.add(new JLabel("Categoria:"));
        formPanel.add(txtCategoria);

        formPanel.add(new JLabel("Marca:"));
        formPanel.add(txtMarca);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(cinzaFundo);

        JButton btnSalvar = criarBotao("💾 Salvar", roxo, branco);
        JButton btnCancelar = criarBotao("❌ Cancelar", Color.GRAY, branco);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnSalvar.addActionListener(e -> salvarProduto());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton btn = new JButton(texto);
        btn.setBackground(fundo);
        btn.setForeground(textoCor);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                new EmptyBorder(8, 16, 8, 16)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void salvarProduto() {
        String nome = txtNome.getText();
        String referencia = txtReferencia.getText();
        String fornecedor = txtFornecedor.getText();
        String categoria = txtCategoria.getText();
        String marca = txtMarca.getText();

        if (nome.isEmpty() || referencia.isEmpty() || fornecedor.isEmpty() || categoria.isEmpty() || marca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produto produto = new Produto(nome, referencia, fornecedor, categoria, marca);

        produtoService.addProduto(produto);
        produtoList.atualizarTabela();
        dispose();
    }
}
