package hu.ott_one.gameoflife.ui.game_screen.base_presenter_view;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import hu.ott_one.gameoflife.model.GameModel;
import hu.ott_one.gameoflife.interactor.GameModelInteractor;
import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/1/17.
 */
public class LifeOfGamePresenter<T extends ILifeOfGameView> extends MvpBasePresenter<T> {

    protected GameModelInteractor gameHandler;

    public void init(GameTable table) {
        GameModel gameModel = new GameModel(table.getWidth(), table.getHeight());
        gameHandler = new GameModelInteractor(gameModel);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().initDisplay(table);
            }
        }
    }

    public void createNextGeneration() {
        gameHandler.tick();
        if (isViewAttached()) {
            if (getView() != null) {
                getView().displayGeneration(gameHandler.getGameModel().getCells());
            }
        }
    }

    public void initPattern(boolean[][] pattern) {
        gameHandler.getGameModel().setCells(pattern);
    }
}
