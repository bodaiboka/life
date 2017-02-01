package hu.ott_one.gameoflife.ui.settings_screen;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsActivity extends MvpActivity<ISettingsView, SettingsPresenter> implements ISettingsView {

    @NonNull
    @Override
    public SettingsPresenter createPresenter() {
        return null;
    }
}
