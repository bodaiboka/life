package hu.ott_one.gameoflife.model;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameModelAccessObject {

    private GameModel gameModel;

    public GameModelAccessObject(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
