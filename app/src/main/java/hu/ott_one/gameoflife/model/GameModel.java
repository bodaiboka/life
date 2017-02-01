package hu.ott_one.gameoflife.model;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameModel {

    private boolean[][] cells;
    private int width;
    private int height;

    public GameModel(int width, int height) {
        cells = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getCells() {
        return cells;
    }

    public void setCells(boolean[][] cells) {
        this.cells = cells;
    }

    public void clear() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = false;
            }
        }
    }

    public int getNeighbourCount(int i, int j) {
        int neighbourCount = 0;

        int left = (i-1) % cells.length;
        if (left < 0) left += cells.length;
        int right = (i+1) % cells.length;
        if (right < 0) right += cells.length;
        int up = (j-1) % cells[i].length;
        if (up < 0) up += cells[i].length;
        int down = (j+1) % cells[i].length;
        if (down < 0) down += cells[i].length;

        if (cells[left][j]) neighbourCount++;
        if (cells[i][up]) neighbourCount++;
        if (cells[left][up]) neighbourCount++;
        if (cells[left][down]) neighbourCount++;
        if (cells[right][up]) neighbourCount++;
        if (cells[right][j]) neighbourCount++;
        if (cells[i][down]) neighbourCount++;
        if (cells[right][down]) neighbourCount++;
        return neighbourCount;
    }

    public boolean generateCellNextState(int i, int j) {
        boolean state = cells[i][j];
        int neighbours = getNeighbourCount(i, j);
        if (state == false) {
            if (neighbours == 3) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if ((neighbours > 1) && (neighbours < 4)) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
