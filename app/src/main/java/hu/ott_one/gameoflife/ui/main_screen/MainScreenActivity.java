package hu.ott_one.gameoflife.ui.main_screen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.ui.game_screen.GameScreenActivity;
import hu.ott_one.gameoflife.ui.custom_view.LifeLinearLayout;
import hu.ott_one.gameoflife.ui.settings_screen.SettingsScreenActivity;

public class MainScreenActivity extends MvpActivity<IMainScreenView, MainScreenPresenter> implements IMainScreenView {

    @BindView(R.id.ll_background) LifeLinearLayout llBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @NonNull
    @Override
    public MainScreenPresenter createPresenter() {
        return new MainScreenPresenter();
    }

    @OnClick(R.id.btn_start_game)
    @Override
    public void onNewGameButtonPressed() {
        presenter.startNewGame();
    }

    @OnClick(R.id.btn_open_settings)
    @Override
    public void onSettingsButtonPressed() {
        presenter.openSettings();
    }

    @Override
    public void showNewGame() {
        Intent intent = new Intent();
        intent.setClass(this, GameScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSettings() {
        Intent intent = new Intent();
        intent.setClass(this, SettingsScreenActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        llBackground.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        llBackground.onStop();
    }
}
