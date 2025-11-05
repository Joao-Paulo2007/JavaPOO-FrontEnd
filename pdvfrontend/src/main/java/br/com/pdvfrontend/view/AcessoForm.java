package br.com.pdvfrontend.view;

import com.br.pdvpostocombustivel.api.acesso.AcessoService;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;

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
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AcessoRequest acessoRequest = new AcessoRequest(usuario, senha);

        try {
            acessoService.create(acessoRequest);
            acessoList.atualizarTabela();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar acesso: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}