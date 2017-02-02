package hu.ott_one.gameoflife.ui.game_screen;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import hu.ott_one.gameoflife.model.GameModel;
import hu.ott_one.gameoflife.model.GameModelAccessObject;
import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.model.SettingsManager;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GamePresenter extends MvpBasePresenter<IGameView> {

    private GameModelAccessObject gameHandler;

    public void createNextGeneration() {
        gameHandler.tick();
        if (isViewAttached()) {
            if (getView() != null) {
                getView().displayGeneration(gameHandler.getGameModel().getCells());
                getView().updateTick(gameHandler.getTickNum());
            }
        }
    }

    public void init() {
        GameTable table = SettingsManager.getTableSettings();
        GameModel gameModel = new GameModel(table.getWidth(), table.getHeight());
        gameHandler = new GameModelAccessObject(gameModel);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().initDisplay(table);
                getView().displayGeneration(gameHandler.getGameModel().getCells());
                getView().updateTick(gameHandler.getTickNum());
            }
        }
    }

    public void customInit(GameTable table) {
        GameModel gameModel = new GameModel(table.getWidth(), table.getHeight());
        gameHandler = new GameModelAccessObject(gameModel);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().initDisplay(table);
                /*getView().displayGeneration(gameHandler.getGameModel().getCells());
                getView().updateTick(gameHandler.getTickNum());*/
            }
        }
    }

    public void onCellClicked(int column, int row, boolean isAlive) {
        gameHandler.setCell(column, row, isAlive);
        // ha szerkesztjük a cellákat, akkor lenullázzuk a tick számlálót,
        // mintha előlről kezdenénk a generálást
        gameHandler.setTickNum(0);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().updateTick(gameHandler.getTickNum());
            }
        }
    }

    public void initPattern(boolean[][] pattern) {
        gameHandler.getGameModel().setCells(pattern);
    }
}
