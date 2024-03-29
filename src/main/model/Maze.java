package main.model;

import main.Exceptions.IllegalDimensionException;

import java.util.Random;

/**
 * This class represents a Maze. This class has various methods getX(), getY(), and getPosition().
 * We have two constructors to create a Maze, one which will parse an existing Maze and another one will be randomly
 * generated by the computer program.
 */

public class Maze {
    private final int width;
    private final int height;

    private boolean [][] maze;

    public Maze(int width, int height) throws IllegalDimensionException {
        if (width!= height) {
            throw new IllegalDimensionException("The dimensions of the maze are not equal" + "Width=" + width +
                    "Height=" + height);
        }
        this.width = width;
        this.height = height;
        init();
    }

    // EFFECTS: creates a maze using an existing 2D array
    public Maze(boolean[][] maze) {
        this.maze = maze;
        this.width = maze.length;
        this.height = maze.length;
    }

    private void init() {
        maze = new boolean[width][height];
        fillMaze();
    }

    private void fillMaze() {
        // Initialize the maze with all walls
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = false;
            }
        }

        // Start recursive backtracking from the top-left corner
        recursiveBacktracking(0, 0);
        int lastX = width-1;
        int lastY = height -1;
        maze[lastX][lastY] = true;
        maze[lastX-1][lastY] = true;
    }

    private void recursiveBacktracking(int row, int col) {
        // Mark the current cell as open
        maze[row][col] = true;

        // Generate a random order of directions to explore
        int[] directions = { 0, 1, 2, 3 };
        shuffleArray(directions);

        // Explore each direction in a random order
        for (int direction : directions) {
            int newRow = row;
            int newCol = col;

            if (direction == 0 && newRow >= 2 && !maze[newRow - 2][newCol]) {
                newRow -= 2; // Move up
            } else if (direction == 1 && newCol < width - 2 && !maze[newRow][newCol + 2]) {
                newCol += 2; // Move right
            } else if (direction == 2 && newRow < height - 2 && !maze[newRow + 2][newCol]) {
                newRow += 2; // Move down
            } else if (direction == 3 && newCol >= 2 && !maze[newRow][newCol - 2]) {
                newCol -= 2; // Move left
            }

            // Check if the new cell is a valid unvisited cell
            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width && !maze[newRow][newCol]) {
                // Connect the current cell and the new cell
                maze[newRow][newCol] = true;
                maze[newRow + (row - newRow) / 2][newCol + (col - newCol) / 2] = true;

                // Recursively backtrack from the new cell
                recursiveBacktracking(newRow, newCol);
            }
        }
    }


    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }


    // EFFECTS: returns the maze in the form of a string
    public String printMaze() {
        String result ="";
        for (int i=0; i<width; i++) {
            for (int j=0; j< height; j++) {
                if (maze[i][j]) {
                    result+="_ ";
                } else {
                    result += "* ";
                }
            }
            result+="\n";
        }
        return result;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getPosition(int x, int y) {
        return maze[x][y];
    }
}
