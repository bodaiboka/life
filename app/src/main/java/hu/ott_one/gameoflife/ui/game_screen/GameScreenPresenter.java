package hu.ott_one.gameoflife.ui.game_screen;

import hu.ott_one.gameoflife.model.GameModel;
import hu.ott_one.gameoflife.interactor.GameModelInteractor;
import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.interactor.SettingsInteractor;
import hu.ott_one.gameoflife.model.LifModel;
import hu.ott_one.gameoflife.ui.game_screen.base_presenter_view.LifeOfGamePresenter;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameScreenPresenter extends LifeOfGamePresenter<IGameScreenView> {

    public void init() {
        GameModel gameModel;
        LifModel lifModel = SettingsInteractor.getLifModel();
        GameTable table = SettingsInteractor.getTableSettings();
        if (lifModel != null) {
            gameModel = new GameModel(lifModel, table.getWidth(), table.getHeight());
        } else {
            gameModel = new GameModel(table.getWidth(), table.getHeight());
        }
        gameHandler = new GameModelInteractor(gameModel);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().initDisplay(table);
                getView().displayGeneration(gameHandler.getGameModel().getCells());
                getView().updateTick(gameHandler.getTickNum());
            }
        }
    }

    public void createNextGeneration() {
        gameHandler.tick();
        if (isViewAttached()) {
            if (getView() != null) {
                getView().displayGeneration(gameHandler.getGameModel().getCells());
                getView().updateTick(gameHandler.getTickNum());
            }
        }
    }

    public void onCellClicked(int column, int row, boolean isAlive) {
        gameHandler.setCell(column, row, isAlive);
        // ha szerkesztjük a cellákat, akkor lenullázzuk a tick számlálót,
        // mintha előlről kezdenénk a generálást
        gameHandler.resetTickNum();
        if (isViewAttached()) {
            if (getView() != null) {
                getView().updateTick(gameHandler.getTickNum());
            }
        }
    }
}
