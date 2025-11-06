package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.util.SessionManager;
import br.com.PdvFrontEnd.service.AcessoService;
import br.com.PdvFrontEnd.model.Acesso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    // Nova paleta de cores - Tema Verde/Cinza Moderno
    private static final Color PRIMARY_COLOR = new Color(76, 175, 80);
    private static final Color SECONDARY_COLOR = new Color(33, 33, 33);
    private static final Color ACCENT_COLOR = new Color(255, 193, 7);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(56, 142, 60);
    private static final Color FIELD_BG = new Color(66, 66, 66);

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JRadioButton rbFrentista;
    private JRadioButton rbAdmin;
    private SessionManager sessionManager;
    private AcessoService acessoService;

    public LoginView() {
        this.sessionManager = SessionManager.getInstance();
        this.acessoService = new AcessoService();
        initComponents();
    }

    private void initComponents() {
        setTitle("🔐 Login - Sistema PDV");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(SECONDARY_COLOR);
        setLayout(new BorderLayout());

        // Painel principal com gradiente visual
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, SECONDARY_COLOR, 0, h, SECONDARY_COLOR.darker());
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

        // Ícone e Título
        JLabel lblIcon = new JLabel("⛽");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 72));
        lblIcon.setForeground(PRIMARY_COLOR);
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblIcon);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblTitle = new JLabel("PDV POSTO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Sistema de Gerenciamento");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(189, 189, 189));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblSubtitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Separador
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(300, 2));
        separator.setForeground(PRIMARY_COLOR);
        separator.setBackground(PRIMARY_COLOR);
        mainPanel.add(separator);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Tipo de usuário com visual moderno
        JLabel lblTipo = new JLabel("Selecione o tipo de acesso");
        lblTipo.setForeground(TEXT_COLOR);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTipo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        radioPanel.setOpaque(false);

        rbFrentista = createStyledRadioButton("👤 Frentista");
        rbFrentista.setSelected(true);

        rbAdmin = createStyledRadioButton("🔑 Admin");

        ButtonGroup group = new ButtonGroup();
        group.add(rbFrentista);
        group.add(rbAdmin);

        radioPanel.add(rbFrentista);
        radioPanel.add(rbAdmin);
        mainPanel.add(radioPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Username com estilo
        JLabel lblUsername = new JLabel("Usuário");
        lblUsername.setForeground(new Color(189, 189, 189));
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtUsername = createStyledTextField();
        mainPanel.add(txtUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Campo Password com estilo
        JLabel lblPassword = new JLabel("Senha");
        lblPassword.setForeground(new Color(189, 189, 189));
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtPassword = createStyledPasswordField();
        mainPanel.add(txtPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Botão de Login
        JButton btnLogin = createStyledButton("ENTRAR");
        btnLogin.addActionListener(e -> handleLogin());
        mainPanel.add(btnLogin);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        add(mainPanel, BorderLayout.CENTER);

        // Adiciona Enter para fazer login
        getRootPane().setDefaultButton(btnLogin);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(320, 42));
        field.setPreferredSize(new Dimension(320, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(FIELD_BG);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(PRIMARY_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(320, 42));
        field.setPreferredSize(new Dimension(320, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(FIELD_BG);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(PRIMARY_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }

    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton rb = new JRadioButton(text);
        rb.setForeground(TEXT_COLOR);
        rb.setFont(new Font("Segoe UI", Font.BOLD, 13));
        rb.setOpaque(false);
        rb.setFocusPainted(false);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return rb;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(320, 45));
        button.setPreferredSize(new Dimension(320, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        return button;
    }

    private JButton createLinkButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(320, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(ACCENT_COLOR);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(ACCENT_COLOR.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(ACCENT_COLOR);
            }
        });

        return button;
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showErrorDialog("Por favor, preencha todos os campos!");
            return;
        }

        String role = rbAdmin.isSelected() ? "ADMIN" : "FRENTISTA";

        try {
            Acesso acesso = acessoService.login(username, password);

            if (acesso != null && acesso.getRole() != null && acesso.getRole().equalsIgnoreCase(role)) {
                sessionManager.login(username, acesso.getNomeCompleto(), role);
                showSuccessDialog("Login realizado com sucesso!\nBem-vindo, " + username + "!");
                dispose();
                MainApp.showMainApp();
            } else if (acesso != null && acesso.getRole() != null) {
                showErrorDialog("Tipo de acesso incorreto!\n\nVerifique se selecionou " + acesso.getRole() + ".");
            } else {
                showErrorDialog("Credenciais inválidas!\n\nVerifique seu usuário e senha.");
            }
        } catch (Exception ex) {
            showErrorDialog("Erro ao fazer login!\n\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "⚠️ Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "✅ Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}

