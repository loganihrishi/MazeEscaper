package test.model;

import main.Exceptions.IllegalDimensionException;
import main.model.Maze;
import main.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {
    private Maze g;
    private Player p;

    @BeforeEach
    public void setUp() throws IllegalDimensionException {
        g = new Maze(10, 10);
        p = new Player(g);
    }

    @Test
    public void TestConstructor() {
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        assertEquals(g, p.getMaze());
        assertFalse(p.hasWon());
    }

    @Test
    public void testToCSV() {
        String result = p.toCSV();
        boolean expected = result.contains("Maze") &&
                result.contains("Player") &&
                result.contains("1") &&
                result.contains("0");
        assertTrue(expected);
    }

}
