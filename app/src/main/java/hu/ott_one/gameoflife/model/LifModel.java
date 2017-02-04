package hu.ott_one.gameoflife.model;

import java.util.ArrayList;

/**
 * Created by richardbodai on 2/4/17.
 */
public class LifModel {

    ArrayList<Point> lifeCells;
    int minX;
    int minY;
    int maxX;
    int maxY;

    public LifModel(ArrayList<Point> lifeCells, int minX, int minY, int maxX, int maxY) {
        this.lifeCells = lifeCells;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }


    /*
    * // a beolvasott élő cellákból összerakjuk a tábla mintát
            pattern = new boolean[Math.abs(maxX - minX)+1][Math.abs(maxY - minY)+1];
            for (int i = 0; i < lifeCells.size(); i++) {
                pattern[lifeCells.get(i).x + Math.abs(minX)][lifeCells.get(i).y + Math.abs(minY)] = true;
            }
    * */

    public ArrayList<Point> getLifeCells() {
        return lifeCells;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
