package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Produto;
import br.com.pdvfrontend.service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdutoList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProdutoService produtoService;

    public ProdutoList(ProdutoService service) {
        this.produtoService = service;

        setTitle("Lista de Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Nome", "Referência", "Fornecedor", "Categoria", "Marca"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnRemover);
        add(panelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> {
            ProdutoForm form = new ProdutoForm(produtoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para editar produto (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de edição a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para remover produto (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de remoção a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        atualizarTabela();
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Produto> produtos = produtoService.getAllProdutos();
        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{produto.getNome(), produto.getReferencia(), produto.getFornecedor(), produto.getCategoria(), produto.getMarca()});
        }
    }
}