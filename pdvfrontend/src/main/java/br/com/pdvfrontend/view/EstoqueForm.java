package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.estoque.EstoqueService;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstoqueForm extends JFrame {
    private JTextField txtQuantidade;
    private JTextField txtLocalTanque;
    private JTextField txtLocalEndereco;
    private JTextField txtLoteFabricacao;
    private JTextField txtDataValidade;
    private EstoqueService estoqueService;
    private EstoqueList estoqueList;

    public EstoqueForm(EstoqueService service, EstoqueList list) {
        this.estoqueService = service;
        this.estoqueList = list;

        setTitle("Cadastro de Estoque");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        add(txtQuantidade);

        add(new JLabel("Local Tanque:"));
        txtLocalTanque = new JTextField();
        add(txtLocalTanque);

        add(new JLabel("Local Endereço:"));
        txtLocalEndereco = new JTextField();
        add(txtLocalEndereco);

        add(new JLabel("Lote Fabricação:"));
        txtLoteFabricacao = new JTextField();
        add(txtLoteFabricacao);

        add(new JLabel("Data de Validade (dd/MM/yyyy):"));
        txtDataValidade = new JTextField();
        add(txtDataValidade);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarEstoque());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarEstoque() {
        try {
            BigDecimal quantidade = new BigDecimal(txtQuantidade.getText());
            String localTanque = txtLocalTanque.getText();
            String localEndereco = txtLocalEndereco.getText();
            String loteFabricacao = txtLoteFabricacao.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataValidade = dateFormat.parse(txtDataValidade.getText());

            EstoqueRequest estoqueRequest = new EstoqueRequest(quantidade, localTanque, localEndereco, loteFabricacao, dataValidade);

            estoqueService.create(estoqueRequest);
            estoqueList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar estoque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}