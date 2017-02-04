package hu.ott_one.gameoflife.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import hu.ott_one.gameoflife.interactor.SettingsInteractor;
import hu.ott_one.gameoflife.model.LifModel;
import hu.ott_one.gameoflife.model.Point;

/**
 * .LIF file beolvasó osztály
 * Created by richardbodai on 2/4/17.
 */
public class LifReader {

    Context context;

    final String LIFE_VERSION = "#Life";
    final String NORMAL = "#N";
    final String RULES = "#R";
    final String PATTERN = "#P";

    ArrayList<Point> lifeCells = new ArrayList<>();

    int upperLeftCornerX;
    int upperLeftCornerY;
    int minX, minY, maxX, maxY;
    int yDelta = 0;

    public LifReader(Context context) {
        this.context = context;
    }

    public void readFile(String path) {
        lifeCells.clear();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                processLine(line);
                line = bufferedReader.readLine();
            }
            LifModel initModel = new LifModel(lifeCells, minX, minY, maxX, maxY);
            SettingsInteractor.saveInitPattern(initModel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line) {
        // .lif file verzió
        if (line.startsWith(LIFE_VERSION)) {

        }
        // normál szabályok
        if (line.startsWith(NORMAL)) {

        }
        // speciális szabályok
        if (line.startsWith(RULES)) {

        }
        // egy összefüggő blokk bal felső csúcsa, amit maga a minta követ
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
