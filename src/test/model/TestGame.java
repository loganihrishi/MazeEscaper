package test.model;

import main.Exceptions.IllegalDimensionException;
import main.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private Game game1;

    @BeforeEach
    public void setUp() throws IllegalDimensionException {
        this.game1 = new Game(10, 10);
    }

    @Test
    public void TestConstructor() {
        assertEquals(10, game1.getWidth());
        assertEquals(10, game1.getHeight());
    }

    @Test
    public void TestConstructorExpectException() {
        try {
            new Game(10, 20);
            fail();
        } catch (IllegalDimensionException e) {
            // all good
        }
    }

    @Test
    public void TestConstructorExpectNoException() {
        Game g = null;
        try {
             g=new Game(20, 20);
        } catch (IllegalDimensionException e) {
            fail();
        }
        assertEquals(20, g.getWidth());
        assertEquals(20, g.getHeight());
    }

    @Test
    public void TestPrintMaze() {
        assertNotEquals("", game1.printMaze());
    }


}
