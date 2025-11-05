package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.pessoa.PessoaService;
import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaRequest;
import com.br.pdvpostocombustivel.enums.TipoPessoa;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaForm extends JFrame {
    private JTextField txtNomeCompleto;
    private JTextField txtCpfCnpj;
    private JTextField txtNumeroCtps;
    private JTextField txtDataNascimento;
    private JComboBox<TipoPessoa> comboTipoPessoa;
    private PessoaService pessoaService;
    private PessoaList pessoaList;

    public PessoaForm(PessoaService service, PessoaList list) {
        this.pessoaService = service;
        this.pessoaList = list;

        setTitle("Cadastro de Pessoa");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Nome Completo:"));
        txtNomeCompleto = new JTextField();
        add(txtNomeCompleto);

        add(new JLabel("CPF/CNPJ:"));
        txtCpfCnpj = new JTextField();
        add(txtCpfCnpj);

        add(new JLabel("Número CTPS:"));
        txtNumeroCtps = new JTextField();
        add(txtNumeroCtps);

        add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        txtDataNascimento = new JTextField();
        add(txtDataNascimento);

        add(new JLabel("Tipo de Pessoa:"));
        comboTipoPessoa = new JComboBox<>(TipoPessoa.values());
        add(comboTipoPessoa);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPessoa());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarPessoa() {
        try {
            String nomeCompleto = txtNomeCompleto.getText();
            String cpfCnpj = txtCpfCnpj.getText();
            Long numeroCtps = Long.parseLong(txtNumeroCtps.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText(), formatter);
            TipoPessoa tipoPessoa = (TipoPessoa) comboTipoPessoa.getSelectedItem();

            PessoaRequest pessoaRequest = new PessoaRequest(nomeCompleto, cpfCnpj, numeroCtps, dataNascimento, tipoPessoa);

            pessoaService.create(pessoaRequest);
            pessoaList.atualizarTabela();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar pessoa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}