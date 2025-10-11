package br.com.pdvfrontend.view;


import br.com.pdvfrontend.model.Preco;
import br.com.pdvfrontend.service.PrecoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrecoList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private PrecoService precoService;

    public PrecoList(PrecoService service) {
        this.precoService = service;

        setTitle("Lista de Preços");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Valor", "Data de Alteração", "Hora de Alteração"};
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
            PrecoForm form = new PrecoForm(precoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para editar preço (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de edição a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um preço para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para remover preço (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de remoção a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um preço para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        atualizarTabela();
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Preco> precos = precoService.getAllPrecos();
        for (Preco preco : precos) {
            tableModel.addRow(new Object[]{preco.getValor(), preco.getDataAlteracao(), preco.getHoraAlteracao()});
        }
    }
}