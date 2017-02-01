package hu.ott_one.gameoflife.ui.game_screen;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameActivity extends MvpActivity<IGameView, GamePresenter> implements IGameView {

    @BindView(R.id.btn_play) Button btnPlay;
    @BindView(R.id.btn_pause) Button btnPause;
    @BindView(R.id.btn_next) Button btnNext;

    private final int TICK_TIME = 200;

    @BindView(R.id.frame_cells) FrameLayout cellsFrame;
    CellGridView cellsView;
    final Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            presenter.createNextGeneration();
            handler.postDelayed(this, TICK_TIME);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.init();
    }

    @OnClick(R.id.btn_next)
    @Override
    public void onNextButtonPressed() {
        presenter.createNextGeneration();
    }

    @OnClick(R.id.btn_play)
    @Override
    public void onPlayButtonPressed() {
        handler.postDelayed(runnable, TICK_TIME);
        btnNext.setEnabled(false);
        btnPlay.setEnabled(false);
        btnPause.setEnabled(true);
    }

    @OnClick(R.id.btn_pause)
    @Override
    public void onPauseButtonPressed() {
        handler.removeCallbacks(runnable);
        btnNext.setEnabled(true);
        btnPlay.setEnabled(true);
        btnPause.setEnabled(false);
    }

    @Override
    public void initDisplay(GameTable table) {
        cellsView = new CellGridView(this);
        cellsView.setPresenter(presenter);
        cellsView.setNumColumns(table.getWidth());
        cellsView.setNumRows(table.getHeight());
        cellsFrame.addView(cellsView);
    }

    @Override
    public void displayGeneration(boolean[][] cells) {
        cellsView.setCellChecked(cells);
    }

    @NonNull
    @Override
    public GamePresenter createPresenter() {
        return new GamePresenter();
    }
}
