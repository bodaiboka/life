package hu.ott_one.gameoflife.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * .LIF file beolvasó osztály
 * Created by richardbodai on 2/4/17.
 */
public class LifReader {

    class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final String LIFE_VERSION = "#L";
    final String NORMAL = "#N";
    final String RULES = "#R";
    final String PATTERN = "#P";

    ArrayList<Point> lifeCells = new ArrayList<>();

    boolean[][] pattern;

    int upperLeftCornerX;
    int upperLeftCornerY;
    int minX, minY, maxX, maxY;
    int yDelta = 0;

    boolean currentlyParsingPattern = false;

    public void readFile(String path) {
        lifeCells.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = bufferedReader.readLine();
            while (line != null) {
                processLine(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line) {
        if (line.startsWith(LIFE_VERSION)) {

        }
        if (line.startsWith(NORMAL)) {

        }
        if (line.startsWith(RULES)) {

        }
        if (line.startsWith(PATTERN)) {
            yDelta = 0;
            String[] parts = line.split(" ");
            upperLeftCornerX = Integer.parseInt(parts[1]);
            upperLeftCornerY = Integer.parseInt(parts[2]);
        }
        else {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '*') {
                    Point point = new Point(upperLeftCornerX + i, upperLeftCornerY - yDelta);
                    if (lifeCells.size() == 0) {
                        minX = point.x;
                        minY = point.y;
                        maxX = point.x;
                        maxY = point.y;
                    } else {
                        if (point.x < minX) minX = point.x;
                        if (point.x > maxX) maxX = point.x;
                        if (point.y > maxY) maxY = point.y;
                        if (point.y < minY) minY = point.y;
                    }
                    lifeCells.add(new Point(upperLeftCornerX + i, upperLeftCornerY - yDelta));
                }
            }
            yDelta++;
        }
    }


}
