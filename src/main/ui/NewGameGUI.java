package main.ui;

import main.Exceptions.IllegalDimensionException;
import main.model.Maze;
import main.model.Player;

import java.util.Random;

/**
 * This class is used to create a new Game.
 */

public class NewGameGUI extends GameGUI {

    public NewGameGUI() {
        init();
    }

    private void init() {

        int[] difficulty = {50,60,70};
        Random random = new Random();
        int mazeLength = difficulty[random.nextInt(difficulty.length)];
        try {
            this.player = new Player(new Maze(mazeLength, mazeLength));
        } catch (IllegalDimensionException e) {
            System.out.println("Dimensions are incorrect");
        }
        initializeFrame();
        startGameLoop();
    }
}
