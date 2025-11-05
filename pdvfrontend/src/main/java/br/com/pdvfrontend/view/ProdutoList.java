package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.produto.ProdutoService;
import com.br.pdvpostocombustivel.api.produto.dto.ProdutoRequest;
import com.br.pdvpostocombustivel.api.produto.dto.ProdutoResponse;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ProdutoList extends JFrame {
    private final ProdutoService produtoService;
    private JTable table;
    private List<ProdutoResponse> currentProdutos;

    public ProdutoList(ProdutoService service) {
        this.produtoService = service;
        this.currentProdutos = new ArrayList<>();
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);

        Color roxo = new Color(128, 0, 128);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE PRODUTOS ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(roxo);
        header.setForeground(branco);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(preto, 3),
                new EmptyBorder(15, 0, 15, 0)
        ));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(branco);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(preto, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(preto);
        table.setSelectionBackground(roxo.brighter());
        table.setSelectionForeground(branco);
        table.getTableHeader().setBackground(preto);
        table.getTableHeader().setForeground(branco);
        table.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(preto);

        JButton btnAdicionar = criarBotao("➕ Adicionar", roxo, branco);
        JButton btnRemover = criarBotao("🗑️ Remover", roxo, branco);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", roxo, branco);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(this::adicionarProduto);
        btnRemover.addActionListener(this::removerProduto);
        btnAtualizar.addActionListener(e -> atualizarTabela());
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

    private void adicionarProduto(ActionEvent e) {
        JTextField nomeField = new JTextField();
        JTextField referenciaField = new JTextField();
        JTextField fornecedorField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField marcaField = new JTextField();

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.add(new JLabel("Nome:"));
        form.add(nomeField);
        form.add(new JLabel("Referência:"));
        form.add(referenciaField);
        form.add(new JLabel("Fornecedor:"));
        form.add(fornecedorField);
        form.add(new JLabel("Categoria:"));
        form.add(categoriaField);
        form.add(new JLabel("Marca:"));
        form.add(marcaField);

        int result = JOptionPane.showConfirmDialog(this, form, "Novo Produto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                String referencia = referenciaField.getText();
                String fornecedor = fornecedorField.getText();
                String categoria = categoriaField.getText();
                String marca = marcaField.getText();

                ProdutoRequest newProduto = new ProdutoRequest(nome, referencia, fornecedor, categoria, marca);
                produtoService.create(newProduto);
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerProduto(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                ProdutoResponse selectedProduto = currentProdutos.get(selectedRow);
                produtoService.delete(selectedProduto.getId());
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"ID", "Nome", "Referência", "Fornecedor", "Categoria", "Marca"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            this.currentProdutos = produtoService.list(0, 1000, "id", Sort.Direction.ASC).getContent();

            for (ProdutoResponse p : currentProdutos) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getNome(),
                        p.getReferencia(),
                        p.getFornecedor(),
                        p.getCategoria(),
                        p.getMarca()
                });
            }

            table.setModel(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            this.currentProdutos = new ArrayList<>();
            table.setModel(model);
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
