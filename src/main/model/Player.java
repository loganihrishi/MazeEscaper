package main.model;

public class Player {
    private Maze maze; // the game instance associated with the player
    private int x,y;

    // REQUIRES: a non-null game object so that player can be assigned a game object
    public Player(Maze maze) {
        this.maze = maze;
        // the initial position of the player is x
        this.x = 0;
        this.y = 0;
    }

    // EFFECTS: returns true if the player has reached the end of the Maze
    public boolean hasWon() {
        return x== maze.getWidth() && y== maze.getHeight();
    }

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


}
