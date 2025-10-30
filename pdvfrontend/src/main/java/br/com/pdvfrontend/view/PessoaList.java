package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.pessoa.PessoaService;
import br.com.pdvfrontend.model.Pessoa;
import com.br.pdvpostocombustivel.api.pessoa.PessoaService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PessoaList extends JFrame {
    private final PessoaService pessoaService;
    private JTable table;

    public PessoaList(PessoaService service) {
        this.pessoaService = service;
        initComponents();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Pessoas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);

        Color verde = new Color(0, 140, 0);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE PESSOAS ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(verde);
        header.setForeground(branco);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(preto, 3),
                new EmptyBorder(15, 0, 15, 0)
        ));
        mainPanel.add(header, BorderLayout.NORTH);

        // 📋 Painel central com leve sombra
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
        table.setSelectionBackground(verde);
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

        JButton btnAdicionar = criarBotao("➕ Adicionar", verde, branco);
        JButton btnRemover = criarBotao("🗑️ Remover", verde, branco);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", verde, branco);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Ações
        btnAdicionar.addActionListener(this::adicionarPessoa);
        btnRemover.addActionListener(this::removerPessoa);
        btnAtualizar.addActionListener(e -> atualizarTabela());

        atualizarTabela();
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

    private void adicionarPessoa(ActionEvent e) {
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataField = new JTextField();
        JTextField tipoField = new JTextField();
        String[] roles = {"USER", "ADMIN"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.add(new JLabel("Nome:"));
        form.add(nomeField);
        form.add(new JLabel("CPF:"));
        form.add(cpfField);
        form.add(new JLabel("Data de Nascimento (YYYY-MM-DD):"));
        form.add(dataField);
        form.add(new JLabel("Tipo (Física/Jurídica):"));
        form.add(tipoField);
        form.add(new JLabel("Função:"));
        form.add(roleBox);

        int result = JOptionPane.showConfirmDialog(this, form, "Nova Pessoa", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String dataNasc = dataField.getText();
            String tipo = tipoField.getText();
            String role = (String) roleBox.getSelectedItem();

            if (!nome.isEmpty() && !cpf.isEmpty() && !dataNasc.isEmpty() && !tipo.isEmpty()) {
                pessoaService.addPessoa(new Pessoa(nome, cpf, dataNasc, tipo, role));
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void removerPessoa(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            pessoaService.removePessoa(selectedRow);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para remover!");
        }
    }

    void atualizarTabela() {
        String[] colunas = {"Nome", "CPF", "Data de Nascimento", "Tipo", "Função"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Pessoa p : pessoaService.listPessoas()) {
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCpf(),
                    p.getDataNascimento(),
                    p.getTipo(),
                    p.getRole()
            });
        }

        table.setModel(model);
    }
}