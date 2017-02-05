package hu.ott_one.gameoflife.ui.settings_screen;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.interactor.SettingsInteractor;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsScreenPresenter extends MvpBasePresenter<ISettingsScreenView> {

    public void saveTableSize(int width, int height) {
        GameTable table = new GameTable(width, height);
        SettingsInteractor.saveTableSettings(table);
        if (isViewAttached()) {
            if (getView() != null) {
                getView().showMessage("Settings saved");
            }
        }
    }

    public void initSettings() {
        GameTable table = SettingsInteractor.getTableSettings();
        if (isViewAttached()) {
            if (getView() != null) {
                getView().showSettings(table);
            }
        }
    }
}
