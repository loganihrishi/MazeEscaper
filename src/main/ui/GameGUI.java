package main.ui;

import main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class GameGUI extends JFrame implements KeyListener {

    private final int width = 800;
    private final int height = 600;
    protected Player player;
    private final String filePath = "data/data.csv";
    private boolean shouldSave = false;

    protected void initializeFrame() {
        this.setTitle("Maze Game");
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(width, height));
        addKeyListener(this);
        setFocusable(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmSaveAndExit();
            }
        });
    }

    protected void startGameLoop() {
        new Thread(() -> {
            while (!player.hasWon()) {
                renderMaze(); // Render the maze
                try {
                    Thread.sleep(100); // Delay between each iteration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected void renderMaze() {
        getContentPane().removeAll();
        int mazeWidth = player.getMaze().getWidth();
        int mazeHeight = player.getMaze().getHeight();

        JPanel mainPanel = new JPanel(new GridLayout(mazeHeight, mazeWidth));

        for (int j = 0; j < mazeHeight; j++) {
            for (int i = 0; i < mazeWidth; i++) {
                JLabel cellLabel = new JLabel();
                if (i == player.getX() && j == player.getY()) {
                    cellLabel.setBackground(Color.BLUE);
                } else {
                    if (player.getMaze().getPosition(i, j)) {
                        cellLabel.setBackground(Color.WHITE);
                    } else {
                        cellLabel.setBackground(Color.BLACK);
                    }
                }
                cellLabel.setOpaque(true); // Ensure the background color is visible
                mainPanel.add(cellLabel);
            }
        }

        this.add(mainPanel);
        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing to do here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        renderMaze();
        if (player.hasWon()) {
            JOptionPane.showMessageDialog(null, "You won!"); // Show win message
        } else {
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                player.moveUp();
            } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                player.moveDown();
            } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                player.moveLeft();
            } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                player.moveRight();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // nothing to do here
    }


    protected void confirmSaveAndExit() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to save the game?", "Save Game",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            shouldSave = true;
            saveGame();
        }

        System.exit(0);
    }

    protected void saveGame() {
        if (!shouldSave) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String csvData = player.toCSV();
            writer.write(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
