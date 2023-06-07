package main.persistence;

import main.model.Player;

public interface Writable {
    // EFFECTS: converts the object to the CSV object and returns as a String
    public String toCSV();

    // EFFECTS: converts the CSV data to the player object
    public Player fromCSVToPlayer(String filePath);
}
