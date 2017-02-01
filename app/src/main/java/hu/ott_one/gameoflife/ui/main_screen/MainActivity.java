package hu.ott_one.gameoflife.ui.main_screen;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;

public class MainActivity extends MvpActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.btn_start_game) Button btnStartGame;
    @BindView(R.id.btn_open_settings) Button btnOpenSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
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
}
