package br.com.pdvfrontend.view;

import br.com.pdvfrontend.util.HttpClient;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PessoaForm extends JPanel {

    private JTextField txtNome = new JTextField(20);
    private JTextField txtIdade = new JTextField(5);
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnCancelar = new JButton("Cancelar");
    private PessoaList parent;

    private String pessoaSelecionada;

    public PessoaForm(String pessoaSelecionada, PessoaList parent) {
        this.parent = parent;
        this.pessoaSelecionada = pessoaSelecionada;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Idade:"), gbc);
        gbc.gridx = 1;
        add(txtIdade, gbc);

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnCancelar);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(botoes, gbc);

        btnSalvar.addActionListener(e -> salvarPessoa());
        btnCancelar.addActionListener(e -> parent.voltarParaLista());
    }

    private void salvarPessoa() {
        try {
            String nome = txtNome.getText();
            String idade = txtIdade.getText();
            String json = "{\"nome\":\"" + nome + "\",\"idade\":" + idade + "}";

            if (pessoaSelecionada == null)
                HttpClient.post("/", json);
            else
                HttpClient.put("/" + pessoaSelecionada, json);

            JOptionPane.showMessageDialog(this, "Pessoa salva com sucesso!");
            parent.voltarParaLista();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }
}
