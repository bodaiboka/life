package hu.ott_one.gameoflife.ui.settings_screen;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.model.SettingsManager;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsPresenter extends MvpBasePresenter<ISettingsView> {

    public void saveTableSize(int width, int height) {
        GameTable table = new GameTable(width, height);
        SettingsManager.saveTableSettings(table);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().showMessage("Table size saved");
            }
        }
    }

    public void initSettings() {
        GameTable table = SettingsManager.getTableSettings();
        if (isViewAttached()) {
            if (getView() != null) {
                getView().showSettings(table);
            }
        }
    }
}
