package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.contato.ContatoService;
import br.com.pdvfrontend.model.Contato;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ContatoList extends JFrame {
    private final ContatoService contatoService;
    private JTable table;

    public ContatoList(ContatoService service) {
        this.contatoService = service;
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Contatos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);

        Color laranja = new Color(255, 140, 0); // Cor de destaque para Contato
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE CONTATOS ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(laranja);
        header.setForeground(preto);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(preto, 3),
                new EmptyBorder(15, 0, 15, 0)
        ));
        mainPanel.add(header, BorderLayout.NORTH);

        // Painel central com leve sombra
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
        table.setSelectionBackground(laranja.darker());
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

        JButton btnAdicionar = criarBotao("➕ Adicionar", laranja, preto);
        JButton btnRemover = criarBotao("🗑️ Remover", laranja, preto);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", laranja, preto);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Ações
        btnAdicionar.addActionListener(this::adicionarContato);
        btnRemover.addActionListener(this::removerContato);
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

    private void adicionarContato(ActionEvent e) {
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField enderecoField = new JTextField();

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.add(new JLabel("Telefone:"));
        form.add(telefoneField);
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(new JLabel("Endereço:"));
        form.add(enderecoField);

        int result = JOptionPane.showConfirmDialog(this, form, "Novo Contato", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String telefone = telefoneField.getText();
            String email = emailField.getText();
            String endereco = enderecoField.getText();

            if (!telefone.isEmpty() && !email.isEmpty() && !endereco.isEmpty()) {
                contatoService.addContato(new Contato(telefone, email, endereco));
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void removerContato(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            contatoService.removeContato(selectedRow);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"Telefone", "Email", "Endereço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Contato> contatos = contatoService.listContatos();
        for (Contato c : contatos) {
            model.addRow(new Object[]{
                    c.getTelefone(),
                    c.getEmail(),
                    c.getEndereco()
            });
        }

        table.setModel(model);
    }
}
