package main.ui;

import main.persistence.CSVReader;
/**
 * This class is used to load an already saved game. It will read the contents of the file and then start the game loop
 * to render it.
 */

public class ExistingGame extends GameGUI{

    private CSVReader reader = new CSVReader("data/data.csv");
    public ExistingGame() {
        init();
    }

    private void init() {
        try {
            this.player = reader.readFile();
        } catch (Exception e) {
            System.out.println("Error running the application: " + e.getMessage());
        }
        initializeFrame();
        startGameLoop();
    }
}
