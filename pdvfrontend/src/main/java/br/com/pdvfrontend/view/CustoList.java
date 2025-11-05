package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.custo.CustoService;
import com.br.pdvpostocombustivel.api.custo.dto.CustoRequest;
import com.br.pdvpostocombustivel.api.custo.dto.CustoResponse;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustoList extends JFrame {
    private final CustoService custoService;
    private JTable table;
    private List<CustoResponse> currentCustos;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public CustoList(CustoService service) {
        this.custoService = service;
        this.currentCustos = new ArrayList<>();
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Custos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 550);
        setLocationRelativeTo(null);

        Color vermelho = new Color(200, 0, 0);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE CUSTOS ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(vermelho);
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
        table.setSelectionBackground(vermelho.darker());
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

        JButton btnAdicionar = criarBotao("➕ Adicionar", vermelho, branco);
        JButton btnRemover = criarBotao("🗑️ Remover", vermelho, branco);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", vermelho, branco);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(this::adicionarCusto);
        btnRemover.addActionListener(this::removerCusto);
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

    private void adicionarCusto(ActionEvent e) {
        JTextField impostoField = new JTextField();
        JTextField freteField = new JTextField();
        JTextField custoVariavelField = new JTextField();
        JTextField custoFixoField = new JTextField();
        JTextField margemLucroField = new JTextField();
        JTextField dataProcessamentoField = new JTextField();

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.add(new JLabel("Imposto (%):"));
        form.add(impostoField);
        form.add(new JLabel("Frete (R$):"));
        form.add(freteField);
        form.add(new JLabel("Custo Variável (R$):"));
        form.add(custoVariavelField);
        form.add(new JLabel("Custo Fixo (R$):"));
        form.add(custoFixoField);
        form.add(new JLabel("Margem de Lucro (%):"));
        form.add(margemLucroField);
        form.add(new JLabel("Data Processamento (dd/MM/yyyy):"));
        form.add(dataProcessamentoField);

        int result = JOptionPane.showConfirmDialog(this, form, "Novo Custo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Double imposto = Double.parseDouble(impostoField.getText());
                Double frete = Double.parseDouble(freteField.getText());
                Double custoVariavel = Double.parseDouble(custoVariavelField.getText());
                Double custoFixo = Double.parseDouble(custoFixoField.getText());
                Double margemLucro = Double.parseDouble(margemLucroField.getText());
                Date dataProcessamento = dateFormat.parse(dataProcessamentoField.getText());

                CustoRequest newCusto = new CustoRequest(imposto, frete, custoVariavel, custoFixo, margemLucro, dataProcessamento);
                custoService.create(newCusto);
                atualizarTabela();
            } catch (NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados de entrada. Verifique os formatos (números e data - dd/MM/yyyy).", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar custo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerCusto(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                CustoResponse selectedCusto = currentCustos.get(selectedRow);
                custoService.delete(selectedCusto.getId());
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover custo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um custo para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"ID", "Imposto (%)", "Frete (R$)", "Custo Variável (R$)", "Custo Fixo (R$)", "Margem Lucro (%)", "Data Processamento"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            this.currentCustos = custoService.list(0, 1000, "id", Sort.Direction.ASC).getContent();

            for (CustoResponse c : currentCustos) {
                model.addRow(new Object[]{
                        c.getId(),
                        String.format("%.2f", c.getImposto()),
                        String.format("%.2f", c.getFrete()),
                        String.format("%.2f", c.getCustoVariavel()),
                        String.format("%.2f", c.getCustoFixo()),
                        String.format("%.2f", c.getMargemLucro()),
                        dateFormat.format(c.getDataProcessamento())
                });
            }

            table.setModel(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            this.currentCustos = new ArrayList<>();
            table.setModel(model);
            JOptionPane.showMessageDialog(this, "Erro ao carregar custos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
