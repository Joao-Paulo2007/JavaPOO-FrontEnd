package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.pessoa.PessoaService;
import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaResponse;
import com.br.pdvpostocombustivel.enums.TipoPessoa;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PessoaList extends JFrame {
    private final PessoaService pessoaService;
    private JTable table;
    private List<PessoaResponse> currentPessoas;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PessoaList(PessoaService service) {
        this.pessoaService = service;
        this.currentPessoas = new ArrayList<>();
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Pessoas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        btnAdicionar.addActionListener(this::adicionarPessoa);
        btnRemover.addActionListener(this::removerPessoa);
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

    private void adicionarPessoa(ActionEvent e) {
        JTextField nomeCompletoField = new JTextField();
        JTextField cpfCnpjField = new JTextField();
        JTextField numeroCtpsField = new JTextField();
        JTextField dataNascimentoField = new JTextField();
        JComboBox<TipoPessoa> tipoPessoaBox = new JComboBox<>(TipoPessoa.values());

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.add(new JLabel("Nome Completo:"));
        form.add(nomeCompletoField);
        form.add(new JLabel("CPF/CNPJ:"));
        form.add(cpfCnpjField);
        form.add(new JLabel("Número CTPS:"));
        form.add(numeroCtpsField);
        form.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        form.add(dataNascimentoField);
        form.add(new JLabel("Tipo de Pessoa:"));
        form.add(tipoPessoaBox);

        int result = JOptionPane.showConfirmDialog(this, form, "Nova Pessoa", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nomeCompleto = nomeCompletoField.getText();
                String cpfCnpj = cpfCnpjField.getText();
                Long numeroCtps = Long.parseLong(numeroCtpsField.getText());
                LocalDate dataNascimento = LocalDate.parse(dataNascimentoField.getText(), dateFormatter);
                TipoPessoa tipoPessoa = (TipoPessoa) tipoPessoaBox.getSelectedItem();

                PessoaRequest newPessoa = new PessoaRequest(nomeCompleto, cpfCnpj, numeroCtps, dataNascimento, tipoPessoa);
                pessoaService.create(newPessoa);
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar pessoa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerPessoa(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                PessoaResponse selectedPessoa = currentPessoas.get(selectedRow);
                pessoaService.delete(selectedPessoa.getId());
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover pessoa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para remover!");
        }
    }

    void atualizarTabela() {
        String[] colunas = {"ID", "Nome Completo", "CPF/CNPJ", "Nº CTPS", "Data de Nascimento"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            this.currentPessoas = pessoaService.list(0, 1000, "id", Sort.Direction.ASC).getContent();

            for (PessoaResponse p : currentPessoas) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getNomeCompleto(),
                        p.getCpfCnpj(),
                        p.getNumeroCtps(),
                        p.getDataNascimento().format(dateFormatter)
                });
            }

            table.setModel(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            this.currentPessoas = new ArrayList<>();
            table.setModel(model);
            JOptionPane.showMessageDialog(this, "Erro ao carregar pessoas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
