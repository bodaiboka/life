package hu.ott_one.gameoflife.interactor;

import hu.ott_one.gameoflife.model.GameModel;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameModelInteractor {

    private int tickNum;
    private GameModel gameModel;
    private GameModel[] gameModels = new GameModel[2];

    public GameModelInteractor(GameModel gameModel) {
        tickNum = 0;
        gameModels[0] = new GameModel(gameModel.getWidth(), gameModel.getHeight());
        gameModels[1] = new GameModel(gameModel.getWidth(), gameModel.getHeight());
        initGameModels(gameModel);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setCell(int i, int j, boolean isAlive) {
        gameModel.getCells()[i][j] = isAlive;
    }

    public void initGameModels(GameModel gameModel) {
        for (int i = 0; i < gameModel.getCells().length; i++) {
            for (int j = 0; j < gameModel.getCells()[i].length; j++) {
                gameModels[0].getCells()[i][j] = gameModel.getCells()[i][j];
            }
        }
        gameModels[1].clear();
        this.gameModel = gameModels[0];
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
        modelTo.clear();
        for (int i = 0; i < modelFrom.getCells().length; i++) {
            for (int j = 0; j < modelFrom.getCells()[i].length; j++) {
                modelTo.getCells()[i][j] = modelFrom.generateCellNextState(i, j);
            }
        }
        gameModel = modelTo;
    }

    public int getTickNum() {
        return tickNum;
    }

    // Ez a függvény azt a célt szolgálja, hogy amikor szerkesztés miatt nullázzuk a tick számot, a
    // akkor is a megfelelő tömbre mutasson a gameModel, páratlan tick szám esetén cserélni kell
    // a tömböket, mert különben .
    public void resetTickNum() {
        if (tickNum % 2 == 1) {
            for (int i = 0; i < gameModels[0].getCells().length; i++) {
                for (int j = 0; j < gameModels[0].getCells().length; j++) {
                    gameModels[0].getCells()[i][j] = gameModels[1].getCells()[i][j];
                }
            }
        }
        gameModel = gameModels[0];
        tickNum = 0;
    }
}
