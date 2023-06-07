package main.ui;

import javax.swing.*;
import java.awt.*;

public class StartApplication extends JFrame {
    private final int width = 800;
    private final int height = 600;

    public StartApplication() {
        init();
    }

    private void init() {
        setTitle("MazeEscaper");
        setSize(new Dimension(width, height));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(36, 41, 46));

        JLabel titleLabel = createTitleLabel("Welcome to MazeEscaper");
        addButton(mainPanel, titleLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        JButton newGameButton = createButton("NEW GAME");
        newGameButton.addActionListener(e -> startNewGame());
        addButton(mainPanel, newGameButton, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        JButton loadGameButton = createButton("LOAD SAVED GAME");
        loadGameButton.addActionListener(e -> loadSavedGame());
        addButton(mainPanel, loadGameButton, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private JLabel createTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        return titleLabel;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(300, 100));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(44, 151, 222));
        button.setForeground(new Color(0 ,204, 102));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        return button;
    }

    private void addButton(JPanel panel, Component component, int gridx, int gridy,
                           int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(component, gbc);
    }

    private void startNewGame() {
        dispose();
        new NewGameGUI();
    }

    private void loadSavedGame() {
        dispose();
        new ExistingGame();
    }
}
