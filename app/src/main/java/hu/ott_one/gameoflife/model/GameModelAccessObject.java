package hu.ott_one.gameoflife.model;

import java.util.ArrayList;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameModelAccessObject {

    private int tickNum;
    private GameModel gameModel;
    private GameModel[] gameModels = new GameModel[2];

    public GameModelAccessObject(GameModel gameModel) {
        tickNum = 0;
        gameModels[0] = gameModel;
        gameModels[1] = gameModel;
        this.gameModel = gameModels[0];
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void populateCell(int i, int j) {
        gameModel.getCells()[i][j] = true;
    }

    public void unPopulateCell(int i, int j) {
        gameModel.getCells()[i][j] = false;
    }

    public void setCell(int i, int j, boolean isAlive) {
        gameModel.getCells()[i][j] = isAlive;
    }

    public void tick() {
        tickNum++;
        GameModel modelFrom;
        GameModel modelTo;
        if ((tickNum % 2) == 1) {
            modelFrom = gameModels[0];
            modelTo = gameModels[1];
        }
        else {
            modelFrom = gameModels[1];
            modelTo = gameModels[0];
        }
        for (int i = 0; i < modelFrom.getCells().length; i++) {
            for (int j = 0; j < modelFrom.getCells()[i].length; j++) {
                modelTo.getCells()[i][j] = modelFrom.generateCellNextState(i, j);
            }
        }
        gameModel = modelTo;
    }






}
