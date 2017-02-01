package hu.ott_one.gameoflife.ui.game_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameActivity extends MvpActivity<IGameView, GamePresenter> implements IGameView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    @Override
    public void onNextButtonPressed() {
        presenter.createNextGeneration();
    }

    @OnClick(R.id.btn_play)
    @Override
    public void onPlayButtonPressed() {
        presenter.playGenerations();
    }

    @OnClick(R.id.btn_pause)
    @Override
    public void onPauseButtonPressed() {
        presenter.pauseGenerations();
    }

    @Override
    public void displayGeneration() {

    }

    @NonNull
    @Override
    public GamePresenter createPresenter() {
        return new GamePresenter();
    }
}
