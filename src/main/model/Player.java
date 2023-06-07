package main.model;

import main.persistence.Writable;

public class Player implements Writable {
    private Maze maze; // the game instance associated with the player
    private int x,y;

    // REQUIRES: a non-null game object so that player can be assigned a game object
    public Player(Maze maze) {
        this.maze = maze;
        // the initial position of the player is (0,0)
        this.x = 0;
        this.y = 0;
    }

    // EFFECTS: returns true if the player has reached the end of the Maze
    public boolean hasWon() {
        return x== maze.getWidth()-1 && y== maze.getHeight()-1;
    }

    // REQUIRES: this maze has not won
    // MODIFIES: this
    // EFFECTS: moves the player to the right, if possible
    public void moveRight() {
        if (x+1>=maze.getWidth()) {
            return;
        }

        if (!maze.getPosition(x+1, y)) {
            return;
        }

        x++;

    }

    // REQUIRES: this maze has not won
    // MODIFIES: this
    // EFFECTS: moves the player to the Left, if possible
    public void moveLeft() {
        if (x-1 < 0) {
            return;
        }

        if (!maze.getPosition(x-1, y)) {
            return;
        }

        x--;

    }

    // REQUIRES: this maze has not won
    // MODIFIES: this
    // EFFECTS: moves the player down, if possible
    public void moveDown() {
        if (y+1 >= maze.getHeight()) {
            return;
        }
        if (!maze.getPosition(x, y+1)) {
            return;
        }

        y++;

    }

    // REQUIRES: this maze has not won
    // MODIFIES: this
    // EFFECTS: moves the player UP, if possible
    public void moveUp() {
        if (y-1 < 0) {
            return;
        }
        if (!maze.getPosition(x, y-1)) {
            return;
        }
        y--;

    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Maze getMaze() {
        return this.maze;
    }

    @Override
    public String toCSV() {
        StringBuilder result = new StringBuilder();
        // Append the maze data to the CSV
        result.append("Maze,");
        for (int i = 0; i < maze.getWidth(); i++) {
            for (int j = 0; j < maze.getHeight(); j++) {
                result.append(maze.getPosition(i, j) ? "1," : "0,");
            }
            result.append("\n");
        }
        result.append("\n");
        // Append player position data to the CSV
        result.append("Player,");
        result.append(x).append(",").append(y).append("\n");

        return result.toString();
    }

}
