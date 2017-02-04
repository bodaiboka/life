package hu.ott_one.gameoflife.application;

import android.app.Application;

import hu.ott_one.gameoflife.interactor.SettingsInteractor;

/**
 * Created by richardbodai on 2/1/17.
 */
public class LifeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SettingsInteractor.initSharedPreferences(this);
    }
}
