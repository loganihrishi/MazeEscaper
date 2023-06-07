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
        this.setTitle("MazeEscaper");
        this.setVisible(true);
        this.setBackground(Color.BLUE);
        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        this.add(addPanel());
    }

    private JPanel addPanel() {
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
        result.setBackground(Color.BLUE);
        JLabel mainLabel = addMainLabel();
        result.add(mainLabel);
        result.add(Box.createVerticalGlue()); // Add glue to push components to the top
        result.add(Box.createVerticalStrut(20)); // Add vertical spacing
        result.add(getButton("NEW GAME"));
        result.add(Box.createVerticalStrut(10)); // Add vertical spacing
        result.add(getButton("LOAD SAVED GAME"));
        result.add(Box.createVerticalStrut(20)); // Add vertical spacing

        result.add(Box.createVerticalGlue()); // Add glue to push components to the bottom
        return result;
    }

    private JButton getButton(String label) {
        JButton result = new JButton(label);
        result.setBackground(Color.RED);
        result.setAlignmentX(Component.CENTER_ALIGNMENT);
        return result;
    }

    private JLabel addMainLabel() {
        JLabel result = new JLabel("Welcome to MazeEscaper");
        result.setForeground(Color.WHITE);
        result.setFont(new Font("Arial", Font.BOLD, 30));
        result.setVerticalAlignment(JLabel.TOP);
        result.setHorizontalAlignment(JLabel.CENTER);
        return result;
    }
}
