package hu.ott_one.gameoflife.ui.main_screen;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by richardbodai on 2/1/17.
 */
public interface IMainView extends MvpView {
    void onNewGameButtonPressed();
    void onSettingsButtonPressed();
}