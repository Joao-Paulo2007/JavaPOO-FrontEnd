package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.util.SessionManager;
import br.com.PdvFrontEnd.service.AcessoService;
import br.com.PdvFrontEnd.service.PessoaService;
import br.com.PdvFrontEnd.model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisterAdminView extends JFrame {
    // Cores - Tema Verde/Cinza Moderno
    private static final Color PRIMARY_COLOR = new Color(76, 175, 80);
    private static final Color SECONDARY_COLOR = new Color(33, 33, 33);
    private static final Color ACCENT_COLOR = new Color(103, 58, 183); // Roxo para Admin
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(56, 142, 60);
    private static final Color FIELD_BG = new Color(66, 66, 66);

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private SessionManager sessionManager;
    private AcessoService acessoService;
    private PessoaService pessoaService;

    public RegisterAdminView() {
        this.sessionManager = SessionManager.getInstance();
        this.acessoService = new AcessoService();
        this.pessoaService = new PessoaService();
        initComponents();
    }

    private void initComponents() {
        setTitle("🔑 Cadastro Administrador - Sistema PDV");
        setSize(550, 550);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(SECONDARY_COLOR);
        setLayout(new BorderLayout(10, 10));

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(SECONDARY_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Ícone
        JLabel lblIcon = new JLabel("🔑");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        lblIcon.setForeground(ACCENT_COLOR);
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblIcon);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Título
        JLabel lblTitle = new JLabel("Cadastro Admin");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(ACCENT_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Subtítulo
        JLabel lblSubtitle = new JLabel("Configure a conta do Administrador");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(189, 189, 189));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblSubtitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Campo Username
        JLabel lblUsername = new JLabel("Usuário Admin:");
        lblUsername.setForeground(TEXT_COLOR);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        txtUsername = new JTextField(20);
        txtUsername.setMaximumSize(new Dimension(420, 45));
        txtUsername.setPreferredSize(new Dimension(420, 45));
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Password
        JLabel lblPassword = new JLabel("Senha:");
        lblPassword.setForeground(TEXT_COLOR);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        txtPassword = new JPasswordField(20);
        txtPassword.setMaximumSize(new Dimension(420, 45));
        txtPassword.setPreferredSize(new Dimension(420, 45));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Confirmar Senha
        JLabel lblConfirmPassword = new JLabel("Confirmar Senha:");
        lblConfirmPassword.setForeground(TEXT_COLOR);
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        txtConfirmPassword = new JPasswordField(20);
        txtConfirmPassword.setMaximumSize(new Dimension(420, 45));
        txtConfirmPassword.setPreferredSize(new Dimension(420, 45));
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        // Botão Cadastrar
        JButton btnRegister = createStyledButton("Cadastrar Admin");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(420, 50));
        btnRegister.setPreferredSize(new Dimension(420, 50));
        btnRegister.setBackground(ACCENT_COLOR);
        btnRegister.addActionListener(e -> handleRegister());
        mainPanel.add(btnRegister);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão Voltar
        JButton btnBack = createStyledButton("Voltar");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setMaximumSize(new Dimension(300, 40));
        btnBack.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
        mainPanel.add(btnBack);

        add(mainPanel, BorderLayout.CENTER);

        // Enter para cadastrar
        txtConfirmPassword.addActionListener(e -> handleRegister());
    }

    private void handleRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "⚠️ Campos Obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "O nome de usuário deve ter pelo menos 3 caracteres!",
                    "⚠️ Usuário Inválido",
                    JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "A senha deve ter pelo menos 4 caracteres!",
                    "⚠️ Senha Inválida",
                    JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não coincidem!",
                    "⚠️ Erro na Confirmação",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            txtPassword.requestFocus();
            return;
        }

        try {
            // Verifica se o usuário já existe
            if (acessoService.usuarioJaExiste(username)) {
                JOptionPane.showMessageDialog(this,
                        "Este usuário já está cadastrado no sistema!",
                        "⚠️ Usuário Duplicado",
                        JOptionPane.WARNING_MESSAGE);
                txtUsername.requestFocus();
                return;
            }

            // Primeiro, cria uma pessoa "Admin" no sistema
            Pessoa pessoaAdmin = new Pessoa();
            pessoaAdmin.setNome("Administrador");
            pessoaAdmin.setCpf("00000000000"); // CPF padrão para admin (banco: pdv_posto)
            pessoaAdmin.setTipo("FRENTISTA"); // Tipo pessoa (será admin pelo acesso)
            pessoaAdmin.setDataNascimento("2000-01-01"); // Data padrão

            // Salva a pessoa
            pessoaService.addPessoa(pessoaAdmin);

            // Busca o ID da pessoa recém-criada
            List<Pessoa> pessoas = pessoaService.listPessoas();
            Long pessoaId = null;
            for (Pessoa p : pessoas) {
                if (p.getCpf().equals("00000000000")) {
                    pessoaId = p.getId();
                    break;
                }
            }

            if (pessoaId == null) {
                throw new Exception("Erro ao criar pessoa do administrador");
            }

            // Agora cria o acesso com role ADMIN
            acessoService.addAcessoComPessoa(username, password, pessoaId, "ADMIN");

            // Salva também no arquivo local para compatibilidade
            sessionManager.saveAdminCredentials(username, password);

            JOptionPane.showMessageDialog(this,
                    "✅ Administrador cadastrado com sucesso!\n\n" +
                    "Usuário: " + username + "\n\n" +
                    "Você já pode fazer login no sistema.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Volta para a tela de login
            dispose();
            new LoginView().setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "❌ Erro ao cadastrar administrador:\n\n" + ex.getMessage(),
                    "Erro no Cadastro",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground().equals(ACCENT_COLOR)) {
                    button.setBackground(ACCENT_COLOR);
                } else {
                    button.setBackground(PRIMARY_COLOR);
                }
            }
        });

        return button;
    }
}

