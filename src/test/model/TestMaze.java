package test.model;

import main.Exceptions.IllegalDimensionException;
import main.model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMaze {
    private Maze maze1;

    @BeforeEach
    public void setUp() throws IllegalDimensionException {
        this.maze1 = new Maze(10, 10);
    }

    @Test
    public void TestConstructor() {
        assertEquals(10, maze1.getWidth());
        assertEquals(10, maze1.getHeight());
    }

    @Test
    public void TestConstructorExpectException() {
        try {
            new Maze(10, 20);
            fail();
        } catch (IllegalDimensionException e) {
            // all good
        }
    }

    @Test
    public void TestConstructorExpectNoException() {
        Maze g = null;
        try {
             g=new Maze(20, 20);
        } catch (IllegalDimensionException e) {
            fail();
        }
        assertEquals(20, g.getWidth());
        assertEquals(20, g.getHeight());
    }

    @Test
    public void TestPrintMaze() {
        assertNotEquals("", maze1.printMaze());
    }


}
