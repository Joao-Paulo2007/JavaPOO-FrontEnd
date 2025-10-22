package br.com.pdvfrontend.view;

import br.com.pdvfrontend.model.Acesso;
import com.br.pdvpostocombustivel.api.acesso.AcessoService;

import javax.swing.*;
import java.awt.*;

public class AcessoForm extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private AcessoService acessoService;
    private AcessoList acessoList;

    public AcessoForm(AcessoService service, AcessoList list) {
        this.acessoService = service;
        this.acessoList = list;

        setTitle("Cadastro de Acesso");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarAcesso());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarAcesso() {
        Acesso acesso = new Acesso(
                txtUsuario.getText(),
                new String(txtSenha.getPassword())
        );

        acessoService.addAcesso(acesso);
        acessoList.atualizarTabela();
        dispose();
    }
}