package main.persistence;

import main.model.Maze;
import main.model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader {
    private String filePath;

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public Player readFile() {
        File file = new File(filePath);
        Maze maze;
        int x,y;
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            maze = readMaze(line);
            String[] values = scanner.nextLine().split(",");
            x = Integer.parseInt(values[0]);
            y = Integer.parseInt(values[1]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Player(maze, x, y);
    }

    private Maze readMaze(String line) {
        String[] values = line.split(",");
        int sliceWidth = (int) Math.sqrt(values.length);
        int index=0;
        boolean [][] res = new boolean[sliceWidth][sliceWidth];
        for (int i=0; i<sliceWidth; i++) {
            for (int j=0; j<sliceWidth; j++) {
                int toBeAdded = Integer.parseInt(values[index]);
                if (toBeAdded==1) {
                    res[i][j] = true;
                } else {
                    res[i][j] = false;
                }
                index++;
            }
        }
        return new Maze(res);
    }

    public String getFilePath() {
        return this.filePath;
    }
}
