package br.com.pdvfrontend.view;


import br.com.pdvfrontend.model.Custo;
import br.com.pdvfrontend.service.CustoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustoList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private CustoService custoService;

    public CustoList(CustoService service) {
        this.custoService = service;

        setTitle("Lista de Custos");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Imposto", "Frete", "Custo Variável", "Custo Fixo", "Margem de Lucro", "Data de Processamento"};
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
            CustoForm form = new CustoForm(custoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para editar custo (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de edição a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um custo para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para remover custo (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de remoção a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um custo para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        atualizarTabela();
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Custo> custos = custoService.getAllCustos();
        for (Custo custo : custos) {
            tableModel.addRow(new Object[]{custo.getImposto(), custo.getFrete(), custo.getCustoVariavel(), custo.getCustoFixo(), custo.getMargemLucro(), custo.getDataProcessamento()});
        }
    }
}