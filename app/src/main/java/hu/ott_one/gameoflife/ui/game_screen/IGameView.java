package hu.ott_one.gameoflife.ui.game_screen;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by richardbodai on 2/1/17.
 */
public interface IGameView extends MvpView {
    void onNextButtonPressed();
    void onPlayButtonPressed();
    void onPauseButtonPressed();

    void displayGeneration();
}
