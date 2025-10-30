package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Preco;
import com.br.pdvpostocombustivel.api.preco.PrecoService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrecoForm extends JFrame {
    private JTextField txtValor;
    private JTextField txtDataAlteracao;
    private JTextField txtHoraAlteracao;
    private PrecoService precoService;
    private PrecoList precoList;

    public PrecoForm(PrecoService service, PrecoList list) {
        this.precoService = service;
        this.precoList = list;

        setTitle("Cadastro de Preço");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        add(txtValor);

        add(new JLabel("Data de Alteração (dd/MM/yyyy):"));
        txtDataAlteracao = new JTextField();
        add(txtDataAlteracao);

        add(new JLabel("Hora de Alteração (HH:mm:ss):"));
        txtHoraAlteracao = new JTextField();
        add(txtHoraAlteracao);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPreco());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarPreco() {
        try {
            BigDecimal valor = new BigDecimal(txtValor.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date dataAlteracao = dateFormat.parse(txtDataAlteracao.getText());
            Date horaAlteracao = timeFormat.parse(txtHoraAlteracao.getText());

            Preco preco = new Preco(valor, dataAlteracao, horaAlteracao);

            precoService.addPreco(preco);
            precoList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}