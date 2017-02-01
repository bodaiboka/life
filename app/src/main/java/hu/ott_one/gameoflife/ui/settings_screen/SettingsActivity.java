package hu.ott_one.gameoflife.ui.settings_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import hu.ott_one.gameoflife.R;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsActivity extends MvpActivity<ISettingsView, SettingsPresenter> implements ISettingsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @NonNull
    @Override
    public SettingsPresenter createPresenter() {
        return new SettingsPresenter();
    }
}
