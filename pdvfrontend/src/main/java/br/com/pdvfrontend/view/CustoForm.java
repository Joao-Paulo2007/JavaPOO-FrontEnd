package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.custo.CustoService;
import com.br.pdvpostocombustivel.api.custo.dto.CustoRequest;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustoForm extends JFrame {
    private JTextField txtImposto;
    private JTextField txtFrete;
    private JTextField txtCustoVariavel;
    private JTextField txtCustoFixo;
    private JTextField txtMargemLucro;
    private JTextField txtDataProcessamento;
    private CustoService custoService;
    private CustoList custoList;

    public CustoForm(CustoService service, CustoList list) {
        this.custoService = service;
        this.custoList = list;

        setTitle("Cadastro de Custo");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel("Imposto:"));
        txtImposto = new JTextField();
        add(txtImposto);

        add(new JLabel("Frete:"));
        txtFrete = new JTextField();
        add(txtFrete);

        add(new JLabel("Custo Variável:"));
        txtCustoVariavel = new JTextField();
        add(txtCustoVariavel);

        add(new JLabel("Custo Fixo:"));
        txtCustoFixo = new JTextField();
        add(txtCustoFixo);

        add(new JLabel("Margem de Lucro:"));
        txtMargemLucro = new JTextField();
        add(txtMargemLucro);

        add(new JLabel("Data de Processamento (dd/MM/yyyy):"));
        txtDataProcessamento = new JTextField();
        add(txtDataProcessamento);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarCusto());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarCusto() {
        try {
            Double imposto = Double.parseDouble(txtImposto.getText());
            Double frete = Double.parseDouble(txtFrete.getText());
            Double custoVariavel = Double.parseDouble(txtCustoVariavel.getText());
            Double custoFixo = Double.parseDouble(txtCustoFixo.getText());
            Double margemLucro = Double.parseDouble(txtMargemLucro.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataProcessamento = dateFormat.parse(txtDataProcessamento.getText());

            CustoRequest custoRequest = new CustoRequest(imposto, frete, custoVariavel, custoFixo, margemLucro, dataProcessamento);

            custoService.create(custoRequest);
            custoList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar custo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}