package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.contato.ContatoService;
import com.br.pdvpostocombustivel.api.contato.dto.ContatoRequest;

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
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String endereco = txtEndereco.getText();

        if (telefone.isEmpty() || email.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ContatoRequest contatoRequest = new ContatoRequest(telefone, email, endereco);

        try {
            contatoService.create(contatoRequest);
            contatoList.atualizarTabela();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar contato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}