package hu.ott_one.gameoflife.ui.game_screen;

import hu.ott_one.gameoflife.ui.game_screen.base_presenter_view.ILifeOfGameView;

/**
 * Created by richardbodai on 2/1/17.
 */
public interface IGameScreenView extends ILifeOfGameView {
    void onNextButtonPressed();
    void onPlayButtonPressed();
    void onPauseButtonPressed();
    void onShowGridsPressed();
    void onClearButtonPressed();
    void updateTick(int i);
    void showDescription(String description);
    void onShowDescriptionPressed();
}
