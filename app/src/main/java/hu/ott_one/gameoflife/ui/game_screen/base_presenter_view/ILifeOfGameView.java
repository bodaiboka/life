package hu.ott_one.gameoflife.ui.game_screen.base_presenter_view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/1/17.
 */
public interface ILifeOfGameView extends MvpView {
    void initDisplay(GameTable table);
    void displayGeneration(boolean[][] cells);
}
