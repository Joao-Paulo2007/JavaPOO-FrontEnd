package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Contato;
import br.com.pdvfrontend.service.ContatoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ContatoList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ContatoService contatoService;

    public ContatoList(ContatoService service) {
        this.contatoService = service;

        setTitle("Lista de Contatos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Telefone", "Email", "Endereço"};
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
            ContatoForm form = new ContatoForm(contatoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para editar contato (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de edição a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um contato para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lógica para remover contato (implementar)
                JOptionPane.showMessageDialog(this, "Funcionalidade de remoção a ser implementada.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um contato para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        atualizarTabela();
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Contato> contatos = contatoService.getAllContatos();
        for (Contato contato : contatos) {
            tableModel.addRow(new Object[]{contato.getTelefone(), contato.getEmail(), contato.getEndereco()});
        }
    }
}