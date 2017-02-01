package hu.ott_one.gameoflife.model;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameTable {
    int width;
    int height;

    public GameTable(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
