package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.estoque.EstoqueService;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueResponse;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstoqueList extends JFrame {
    private final EstoqueService estoqueService;
    private JTable table;
    private List<EstoqueResponse> currentEstoques;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public EstoqueList(EstoqueService service) {
        this.estoqueService = service;
        this.currentEstoques = new ArrayList<>();
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Estoque");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 550);
        setLocationRelativeTo(null);

        Color marrom = new Color(139, 69, 19);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE ESTOQUE ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(marrom);
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
        table.setSelectionBackground(marrom.brighter());
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

        JButton btnAdicionar = criarBotao("➕ Adicionar", marrom, branco);
        JButton btnRemover = criarBotao("🗑️ Remover", marrom, branco);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", marrom, branco);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(this::adicionarEstoque);
        btnRemover.addActionListener(this::removerEstoque);
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

    private void adicionarEstoque(ActionEvent e) {
        JTextField quantidadeField = new JTextField();
        JTextField localTanqueField = new JTextField();
        JTextField localEnderecoField = new JTextField();
        JTextField loteFabricacaoField = new JTextField();
        JTextField dataValidadeField = new JTextField();

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.add(new JLabel("Quantidade:"));
        form.add(quantidadeField);
        form.add(new JLabel("Local Tanque:"));
        form.add(localTanqueField);
        form.add(new JLabel("Local Endereço:"));
        form.add(localEnderecoField);
        form.add(new JLabel("Lote Fabricação:"));
        form.add(loteFabricacaoField);
        form.add(new JLabel("Data Validade (dd/MM/yyyy):"));
        form.add(dataValidadeField);

        int result = JOptionPane.showConfirmDialog(this, form, "Novo Item de Estoque", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                BigDecimal quantidade = new BigDecimal(quantidadeField.getText());
                String localTanque = localTanqueField.getText();
                String localEndereco = localEnderecoField.getText();
                String loteFabricacao = loteFabricacaoField.getText();
                Date dataValidade = dateFormat.parse(dataValidadeField.getText());

                EstoqueRequest newEstoque = new EstoqueRequest(quantidade, localTanque, localEndereco, loteFabricacao, dataValidade);
                estoqueService.create(newEstoque);
                atualizarTabela();
            } catch (NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados de entrada. Verifique os formatos (números e data - dd/MM/yyyy).", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar item de estoque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerEstoque(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                EstoqueResponse selectedEstoque = currentEstoques.get(selectedRow);
                estoqueService.delete(selectedEstoque.getId());
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover item de estoque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item de estoque para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"ID", "Quantidade", "Local Tanque", "Local Endereço", "Lote Fabricação", "Data Validade"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            this.currentEstoques = estoqueService.list(0, 1000, "id", Sort.Direction.ASC).getContent();

            for (EstoqueResponse est : currentEstoques) {
                model.addRow(new Object[]{
                        est.getId(),
                        est.getQuantidade().toString(),
                        est.getLocalTanque(),
                        est.getLocalEndereco(),
                        est.getLoteFabricacao(),
                        dateFormat.format(est.getDataValidade())
                });
            }

            table.setModel(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            this.currentEstoques = new ArrayList<>();
            table.setModel(model);
            JOptionPane.showMessageDialog(this, "Erro ao carregar estoque: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
