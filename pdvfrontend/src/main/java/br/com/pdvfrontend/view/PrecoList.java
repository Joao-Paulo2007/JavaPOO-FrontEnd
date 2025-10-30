package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.preco.PrecoService;
import br.com.pdvfrontend.model.Preco;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.math.BigDecimal;

public class PrecoList extends JFrame {
    private final PrecoService precoService;
    private JTable table;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public PrecoList(PrecoService service) {
        this.precoService = service;
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Preços");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);

        Color dourado = new Color(255, 190, 0);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE PREÇOS ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(dourado);
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
        table.setSelectionBackground(dourado.darker());
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

        JButton btnAdicionar = criarBotao("➕ Adicionar", dourado, preto);
        JButton btnRemover = criarBotao("🗑️ Remover", dourado, preto);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", dourado, preto);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(this::adicionarPreco);
        btnRemover.addActionListener(this::removerPreco);
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

    private void adicionarPreco(ActionEvent e) {
        JTextField valorField = new JTextField();
        JTextField dataAlteracaoField = new JTextField();
        JTextField horaAlteracaoField = new JTextField();

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.add(new JLabel("Valor (R$):"));
        form.add(valorField);
        form.add(new JLabel("Data Alteração (dd/MM/yyyy):"));
        form.add(dataAlteracaoField);
        form.add(new JLabel("Hora Alteração (HH:mm:ss):"));
        form.add(horaAlteracaoField);

        int result = JOptionPane.showConfirmDialog(this, form, "Novo Preço", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                BigDecimal valor = new BigDecimal(valorField.getText());
                java.util.Date dataAlteracao = dateFormat.parse(dataAlteracaoField.getText());
                java.util.Date horaAlteracao = timeFormat.parse(horaAlteracaoField.getText());

                precoService.addPreco(new Preco(valor, dataAlteracao, horaAlteracao));
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados de entrada. Verifique os formatos (números, data - dd/MM/yyyy e hora - HH:mm:ss).", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerPreco(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            precoService.removePreco(selectedRow);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um preço para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"Valor (R$)", "Data Alteração", "Hora Alteração"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Preco> precos = precoService.listPrecos();
        for (Preco p : precos) {
            model.addRow(new Object[]{
                    String.format("R$ %.2f", p.getValor()),
                    dateFormat.format(p.getDataAlteracao()),
                    timeFormat.format(p.getHoraAlteracao())
            });
        }

        table.setModel(model);
    }
}
