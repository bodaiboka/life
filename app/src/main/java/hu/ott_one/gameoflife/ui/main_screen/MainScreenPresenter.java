package hu.ott_one.gameoflife.ui.main_screen;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by richardbodai on 2/1/17.
 */
public class MainScreenPresenter extends MvpBasePresenter<IMainScreenView> {

    public void startNewGame() {
        if (isViewAttached()) {
            if (getView() != null) {
                getView().showNewGame();
            }
        }
    }

    public void openSettings() {
        if (isViewAttached()) {
            if (getView() != null) {
                getView().showSettings();
            }
        }
    }

}
