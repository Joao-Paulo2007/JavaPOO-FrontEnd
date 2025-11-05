package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.acesso.AcessoService;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoResponse;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AcessoList extends JFrame {
    private final AcessoService acessoService;
    private JTable table;
    private List<AcessoResponse> currentAcessos;

    public AcessoList(AcessoService service) {
        this.acessoService = service;
        this.currentAcessos = new ArrayList<>();
        initComponents();
        atualizarTabela();
    }

    private void initComponents() {
        setTitle(" Posto de Combustível - Gestão de Acessos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(null);

        Color azul = new Color(0, 100, 200);
        Color preto = new Color(25, 25, 25);
        Color branco = Color.WHITE;
        Color cinzaFundo = new Color(240, 240, 240);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(cinzaFundo);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel(" GERENCIAMENTO DE ACESSOS ", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(azul);
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
        table.setSelectionBackground(azul.brighter());
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

        JButton btnAdicionar = criarBotao("➕ Adicionar", azul, branco);
        JButton btnRemover = criarBotao("🗑️ Remover", azul, branco);
        JButton btnAtualizar = criarBotao("🔄 Atualizar", azul, branco);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(this::adicionarAcesso);
        btnRemover.addActionListener(this::removerAcesso);
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

    private void adicionarAcesso(ActionEvent e) {
        JTextField usuarioField = new JTextField();
        JPasswordField senhaField = new JPasswordField();

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.add(new JLabel("Usuário:"));
        form.add(usuarioField);
        form.add(new JLabel("Senha:"));
        form.add(senhaField);

        int result = JOptionPane.showConfirmDialog(this, form, "Novo Acesso", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());

            if (!usuario.isEmpty() && !senha.isEmpty()) {
                AcessoRequest newAcesso = new AcessoRequest(usuario, senha);
                try {
                    acessoService.create(newAcesso);
                    atualizarTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar acesso: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void removerAcesso(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                AcessoResponse selectedAcesso = currentAcessos.get(selectedRow);
                acessoService.delete(selectedAcesso.getId());
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover acesso: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um acesso para remover!");
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"ID", "Usuário"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            this.currentAcessos = acessoService.list(0, 1000, "id", Sort.Direction.ASC).getContent();

            for (AcessoResponse a : currentAcessos) {
                model.addRow(new Object[]{
                        a.getId(),
                        a.getUsuario()
                });
            }

            table.setModel(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            this.currentAcessos = new ArrayList<>();
            table.setModel(model);
            JOptionPane.showMessageDialog(this, "Erro ao carregar acessos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
