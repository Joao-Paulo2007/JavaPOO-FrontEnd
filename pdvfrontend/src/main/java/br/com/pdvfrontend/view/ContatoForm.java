package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Contato;
import com.br.pdvpostocombustivel.api.contato.ContatoService;

import javax.swing.*;
import java.awt.*;

public class ContatoForm extends JFrame {
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private ContatoService contatoService;
    private ContatoList contatoList;

    public ContatoForm(ContatoService service, ContatoList list) {
        this.contatoService = service;
        this.contatoList = list;

        setTitle("Cadastro de Contato");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        add(txtTelefone);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        add(txtEndereco);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarContato());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarContato() {
        Contato contato = new Contato(
                txtTelefone.getText(),
                txtEmail.getText(),
                txtEndereco.getText()
        );

        contatoService.addContato(contato);
        contatoList.atualizarTabela();
        dispose();
    }
}