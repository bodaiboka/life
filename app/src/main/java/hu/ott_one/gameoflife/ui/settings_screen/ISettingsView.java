package hu.ott_one.gameoflife.ui.settings_screen;

import com.hannesdorfmann.mosby.mvp.MvpView;

import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/1/17.
 */
public interface ISettingsView extends MvpView {

    void onSaveButtonPressed();
    void showSettings(GameTable table);
    void showMessage(String message);
}
