package main.ui;

import main.Exceptions.IllegalDimensionException;
import main.model.Maze;
import main.model.Player;
/**
 * This class is used to create a new Game.
 */

public class NewGameGUI extends GameGUI {

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
}
