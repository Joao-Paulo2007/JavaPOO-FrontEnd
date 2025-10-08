package br.com.pdvfrontend.view;

import br.com.pdvfrontend.util.HttpClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PessoaList extends JPanel {

    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);
    private JButton btnAdd = new JButton("Nova Pessoa");
    private JButton btnEdit = new JButton("Editar");
    private JButton btnDelete = new JButton("Excluir");

    public PessoaList() {
        setLayout(new BorderLayout());
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(btnAdd);
        buttons.add(btnEdit);
        buttons.add(btnDelete);
        add(buttons, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> abrirFormulario(null));
        btnEdit.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) abrirFormulario(selected);
        });
        btnDelete.addActionListener(e -> deletarPessoa());

        carregarPessoas();
    }

    private void carregarPessoas() {
        try {
            String json = HttpClient.get("/");
            model.clear();

            // Conversão básica sem Gson (esperando lista de objetos simples)
            json = json.replace("[", "").replace("]", "").replace("{", "").replace("}", "");
            String[] pessoas = json.split("},\\{");
            for (String p : pessoas) {
                String nome = extrairCampo(p, "\"nome\":\"");
                if (!nome.isEmpty()) model.addElement(nome);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar pessoas: " + ex.getMessage());
        }
    }

    private void deletarPessoa() {
        String nome = list.getSelectedValue();
        if (nome == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para excluir.");
            return;
        }
        try {
            HttpClient.delete("/" + nome);
            carregarPessoas();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void abrirFormulario(String pessoaSelecionada) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new PessoaForm(pessoaSelecionada, this));
        frame.revalidate();
    }

    public void voltarParaLista() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new PessoaList());
        frame.revalidate();
    }

    private String extrairCampo(String json, String chave) {
        int start = json.indexOf(chave);
        if (start == -1) return "";
        start += chave.length();
        int end = json.indexOf("\"", start);
        return (end == -1) ? "" : json.substring(start, end);
    }
}

