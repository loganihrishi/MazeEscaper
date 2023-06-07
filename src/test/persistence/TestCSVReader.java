package test.persistence;

import main.model.Maze;
import main.model.Player;
import main.persistence.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCSVReader {
    private CSVReader reader;
    private final String filePath = "data/testData.csv";

    @BeforeEach
    public void setUp() {
        this.reader = new CSVReader(filePath);
    }

    @Test
    public void testConstructor() {
        assertEquals(filePath, reader.getFilePath());
    }

    @Test
    public void testCSVReader() {
        Player player = reader.readFile();
        assertEquals(2, player.getY());
        assertEquals(2, player.getX());
        Maze maze = player.getMaze();
        assertEquals(5, maze.getHeight());
        assertEquals(5, maze.getWidth());
    }
}
