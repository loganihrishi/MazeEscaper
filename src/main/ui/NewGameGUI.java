package main.ui;

import main.Exceptions.IllegalDimensionException;
import main.model.Maze;
import main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewGameGUI extends JFrame implements KeyListener {

    private final int width = 800;
    private final int height = 600;
    private Player player;

    public NewGameGUI() {
        init();
    }

    private void init() {
        try {
            this.player = new Player(new Maze(50, 50));
        } catch (IllegalDimensionException e) {
            System.out.println("Dimensions are incorrect");
        }
        initializeFrame();
        startGameLoop();
    }

    private void initializeFrame() {
        this.setTitle("Maze Game");
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(width, height));
        addKeyListener(this);
        setFocusable(true);
    }

    private void startGameLoop() {
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

    private void renderMaze() {
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
        if (keyCode == KeyEvent.VK_W) {
            player.moveUp();
        } else if (keyCode == KeyEvent.VK_S) {
            player.moveDown();
        } else if (keyCode == KeyEvent.VK_A) {
            player.moveLeft();
        } else if (keyCode == KeyEvent.VK_D) {
            player.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // nothing to do here
    }
}
