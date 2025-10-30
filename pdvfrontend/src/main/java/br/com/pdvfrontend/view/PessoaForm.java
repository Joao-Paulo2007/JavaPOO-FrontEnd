package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Pessoa;
import com.br.pdvpostocombustivel.api.pessoa.PessoaService;

import javax.swing.*;
import java.awt.*;

public class PessoaForm extends JFrame {
    private JTextField txtNome;
    private JTextField txtCtps;
    private JTextField txtDataNascimento;
    private JComboBox<String> comboTipo;
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

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("CTPS:"));
        txtCtps = new JTextField();
        add(txtCtps);

        add(new JLabel("Data de Nascimento:"));
        txtDataNascimento = new JTextField();
        add(txtDataNascimento);

        add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Cliente", "Fornecedor"});
        add(comboTipo);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPessoa());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarPessoa() {
        Pessoa pessoa = new Pessoa(
                null,
                txtNome.getText(),
                txtCtps.getText(),
                txtDataNascimento.getText(),
                (String) comboTipo.getSelectedItem()
        );

        pessoaService.addPessoa(pessoa);
        pessoaList.atualizarTabela();
        dispose();
    }
}