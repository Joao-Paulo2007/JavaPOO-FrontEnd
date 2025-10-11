package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Estoque;
import br.com.pdvfrontend.service.EstoqueService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstoqueList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private EstoqueService estoqueService;

    public EstoqueList(EstoqueService service) {
        this.estoqueService = service;

        setTitle("Lista de Estoques");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Quantidade", "Local Tanque", "Local Endereço", "Lote Fabricação", "Data Validade"};
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
            EstoqueForm form = new EstoqueForm(estoqueService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para editar estoque (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de edição a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um estoque para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para remover estoque (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de remoção a ser implementada.");
            }
        });

        atualizarTabela();
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Estoque> estoques = estoqueService.getAllEstoques();
        for (Estoque estoque : estoques) {
            tableModel.addRow(new Object[]{estoque.getQuantidade(), estoque.getLocalTanque(), estoque.getLocalEndereco(), estoque.getLoteFabricacao(), estoque.getDataValidade()});
        }
    }
}