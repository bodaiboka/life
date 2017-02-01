package hu.ott_one.gameoflife.application;

import android.app.Application;

import hu.ott_one.gameoflife.model.SettingsManager;

/**
 * Created by richardbodai on 2/1/17.
 */
public class LifeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SettingsManager.initSharedPreferences(this);
    }
}
