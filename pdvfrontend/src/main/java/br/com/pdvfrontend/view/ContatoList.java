package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.contato.ContatoService;
import com.br.pdvpostocombustivel.api.contato.dto.ContatoRequest;
import com.br.pdvpostocombustivel.api.contato.dto.ContatoResponse;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ContatoList extends JFrame {
    private final ContatoService contatoService;
    private JTable table;
    private List<ContatoResponse> currentContatos;

    public ContatoList(ContatoService service) {
        this.contatoService = service;
        this.currentContatos = new ArrayList<>();
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Contatos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);

        Color laranja = new Color(255, 140, 0);
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
            try {
                String telefone = telefoneField.getText();
                String email = emailField.getText();
                String endereco = enderecoField.getText();

                ContatoRequest newContato = new ContatoRequest(telefone, email, endereco);
                contatoService.create(newContato);
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar contato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerContato(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                ContatoResponse selectedContato = currentContatos.get(selectedRow);
                contatoService.delete(selectedContato.getId());
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover contato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"ID", "Telefone", "Email", "Endereço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            this.currentContatos = contatoService.list(0, 1000, "id", Sort.Direction.ASC).getContent();

            for (ContatoResponse c : currentContatos) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getTelefone(),
                        c.getEmail(),
                        c.getEndereco()
                });
            }

            table.setModel(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            this.currentContatos = new ArrayList<>();
            table.setModel(model);
            JOptionPane.showMessageDialog(this, "Erro ao carregar contatos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
