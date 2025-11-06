package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.service.*;
import br.com.PdvFrontEnd.util.SessionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainApp {
    // Cores para a nova interface - Tema Moderno Verde/Cinza
    private static final Color PRIMARY_COLOR = new Color(76, 175, 80); // Verde Material
    private static final Color SECONDARY_COLOR = new Color(33, 33, 33); // Cinza escuro
    private static final Color ACCENT_COLOR = new Color(255, 193, 7); // Amarelo/Dourado
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(56, 142, 60); // Verde mais escuro
    private static final Color ACTIVE_COLOR = new Color(139, 195, 74); // Verde claro
    private static final Color DANGER_COLOR = new Color(244, 67, 54); // Vermelho

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // Inicia o sistema pelo login
            SessionManager sessionManager = SessionManager.getInstance();

            // Verifica se existe pelo menos um admin cadastrado
            if (!sessionManager.adminExists()) {
                JOptionPane.showMessageDialog(null,
                    "🎉 Bem-vindo ao Sistema PDV!\n\n" +
                    "Primeiro acesso detectado.\n" +
                    "Cadastre o usuário Administrador para continuar.",
                    "Primeiro Acesso",
                    JOptionPane.INFORMATION_MESSAGE);
                new RegisterAdminView().setVisible(true);
            } else {
                // Se já existe admin, vai direto para o login
                new LoginView().setVisible(true);
            }
        });
    }

    public static void showMainApp() {
        EventQueue.invokeLater(() -> {
            SessionManager sessionManager = SessionManager.getInstance();
            boolean isAdmin = sessionManager.isAdmin();

            JFrame mainFrame = new JFrame("🏪 PDV Posto de Combustível - " + (isAdmin ? "ADMIN" : "FRENTISTA"));
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(750, 600);  // Tamanho ajustado
            mainFrame.setResizable(true);  // Permite redimensionamento
            mainFrame.getContentPane().setBackground(SECONDARY_COLOR);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(new BorderLayout(15, 15));

            // Painel superior com informações do usuário
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(PRIMARY_COLOR);
            topPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

            // Painel esquerdo do topo com info do usuário
            JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
            userInfoPanel.setBackground(PRIMARY_COLOR);

            JLabel lblUserIcon = new JLabel(isAdmin ? "👤 ADMIN" : "👤 FRENTISTA");
            lblUserIcon.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblUserIcon.setForeground(TEXT_COLOR);

            JLabel lblUser = new JLabel("| " + sessionManager.getCurrentUsername());
            lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            lblUser.setForeground(TEXT_COLOR);

            userInfoPanel.add(lblUserIcon);
            userInfoPanel.add(lblUser);

            JButton btnLogout = createStyledButton("🚪 Sair", e -> {
                sessionManager.logout();
                mainFrame.dispose();
                new LoginView().setVisible(true);
            });
            btnLogout.setBackground(DANGER_COLOR);

            topPanel.add(userInfoPanel, BorderLayout.WEST);
            topPanel.add(btnLogout, BorderLayout.EAST);
            mainFrame.add(topPanel, BorderLayout.NORTH);

            // Painel Central com os Botões de Gerenciamento em formato de cards
            JPanel mainPanel = new JPanel(new GridLayout(3, 3, 15, 15));
            mainPanel.setBackground(SECONDARY_COLOR);
            mainPanel.setBorder(new EmptyBorder(25, 35, 25, 35));

            // Botão Gerenciar Bombas (sempre disponível) - DESTAQUE
            JButton btnBombas = createCardButton("⛽", "Gerenciar Bombas", e -> new BombaListView().setVisible(true));
            btnBombas.setBackground(ACTIVE_COLOR);
            mainPanel.add(btnBombas);

            // Botão Cadastrar Frentista (apenas para Admin)
            JButton btnCadastrarFrentista = createCardButton("➕", "Novo Frentista", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new RegisterView().setVisible(true);
                }
            });
            btnCadastrarFrentista.setBackground(new Color(103, 58, 183)); // Roxo
            if (!isAdmin) btnCadastrarFrentista.setEnabled(false);
            mainPanel.add(btnCadastrarFrentista);

            // Botões de gerenciamento (apenas para Admin)
            JButton btnPessoas = createCardButton("👥", "Pessoas", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new PessoaList(new PessoaService()).setVisible(true);
                }
            });
            if (!isAdmin) btnPessoas.setEnabled(false);
            mainPanel.add(btnPessoas);

            JButton btnPrecos = createCardButton("💰", "Preços", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new PrecoList(new PrecoService()).setVisible(true);
                }
            });
            if (!isAdmin) btnPrecos.setEnabled(false);
            mainPanel.add(btnPrecos);

            JButton btnProdutos = createCardButton("📦", "Produtos", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new ProdutoList(new ProdutoService()).setVisible(true);
                }
            });
            if (!isAdmin) btnProdutos.setEnabled(false);
            mainPanel.add(btnProdutos);

            JButton btnCustos = createCardButton("💳", "Custos", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new CustoList(new CustoService()).setVisible(true);
                }
            });
            if (!isAdmin) btnCustos.setEnabled(false);
            mainPanel.add(btnCustos);

            JButton btnEstoques = createCardButton("📊", "Estoques", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new EstoqueList(new EstoqueService()).setVisible(true);
                }
            });
            if (!isAdmin) btnEstoques.setEnabled(false);
            mainPanel.add(btnEstoques);

            JButton btnAcessos = createCardButton("🔐", "Acessos", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new AcessoList(new AcessoService()).setVisible(true);
                }
            });
            if (!isAdmin) btnAcessos.setEnabled(false);
            mainPanel.add(btnAcessos);

            JButton btnContatos = createCardButton("📞", "Contatos", e -> {
                if (checkAdminAccess(mainFrame)) {
                    new ContatoList(new ContatoService()).setVisible(true);
                }
            });
            if (!isAdmin) btnContatos.setEnabled(false);
            mainPanel.add(btnContatos);

            mainFrame.add(mainPanel, BorderLayout.CENTER);

            // Painel inferior com informações
            JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            footerPanel.setBackground(SECONDARY_COLOR.darker());
            footerPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

            JLabel lblInfo = new JLabel("📋 Sistema PDV - Posto de Combustível v2.0");
            lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
            lblInfo.setForeground(new Color(158, 158, 158));
            footerPanel.add(lblInfo);

            mainFrame.add(footerPanel, BorderLayout.SOUTH);


            mainFrame.setVisible(true);
        });
    }

    private static boolean checkAdminAccess(JFrame parent) {
        SessionManager sessionManager = SessionManager.getInstance();
        if (!sessionManager.isAdmin()) {
            JOptionPane.showMessageDialog(parent,
                    "Acesso negado!\n\nApenas o Administrador pode acessar este recurso.",
                    "Acesso Restrito",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private static JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(actionListener);
        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AbstractButton b = (AbstractButton) c;
                ButtonModel model = b.getModel();

                if (!b.isEnabled()) {
                    g2.setColor(new Color(80, 80, 80));
                } else if (model.isPressed()) {
                    g2.setColor(BUTTON_HOVER_COLOR.darker());
                } else if (model.isRollover()) {
                    g2.setColor(BUTTON_HOVER_COLOR);
                } else {
                    g2.setColor(b.getBackground());
                }

                // Desenha borda arredondada
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 10, 10);
                g2.dispose();
                super.paint(g, c);
            }
        });
        return button;
    }

    private static JButton createCardButton(String icon, String text, ActionListener actionListener) {
        JButton button = new JButton("<html><center><div style='font-size:32px;'>" + icon + "</div><br>" + text + "</center></html>");
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(66, 66, 66), 2),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(actionListener);

        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AbstractButton b = (AbstractButton) c;
                ButtonModel model = b.getModel();

                if (!b.isEnabled()) {
                    g2.setColor(new Color(60, 60, 60));
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                } else if (model.isPressed()) {
                    g2.setColor(b.getBackground().darker());
                } else if (model.isRollover()) {
                    // Efeito de elevação ao passar o mouse
                    g2.setColor(b.getBackground().brighter());
                } else {
                    g2.setColor(b.getBackground());
                }

                // Desenha cartão arredondado
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 15, 15);

                // Adiciona sombra sutil
                if (model.isRollover() && b.isEnabled()) {
                    g2.setColor(ACCENT_COLOR);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRoundRect(1, 1, b.getWidth()-3, b.getHeight()-3, 15, 15);
                }

                g2.dispose();
                super.paint(g, c);
            }
        });
        return button;
    }
}