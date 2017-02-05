package hu.ott_one.gameoflife.ui.game_screen;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/1/17.
 */
public class GameScreenActivity extends MvpActivity<IGameScreenView, GameScreenPresenter> implements IGameScreenView {

    @BindView(R.id.btn_play) Button btnPlay;
    @BindView(R.id.btn_pause) Button btnPause;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.cb_show_grids) CheckBox cbShowGrids;
    @BindView(R.id.tv_tick) TextView tvTick;
    @BindView(R.id.sb_velocity) SeekBar sbVelocity;
    @BindView(R.id.tv_lif_description) TextView tvLifDescription;
    @BindView(R.id.cb_show_description) CheckBox cbShowDescription;
    @BindView(R.id.ll_lif_description) LinearLayout llLifDescription;

    // legalacsonyabb idő két tick között
    private final int TICK_TIME = 1000;
    // sebbesség változtató seeker maximuma
    private final int SEEKBAR_MAX = 94;
    // seeker kezdő pozíciója
    private final int SEEKER_INIT = 80;

    double playVelocityScale;

    @BindView(R.id.frame_cells) FrameLayout cellsFrame;
    CellGridView cellsView;
    final Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            presenter.createNextGeneration();
            handler.postDelayed(this, (long)(TICK_TIME * playVelocityScale));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        initSeeker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.init();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.btn_next)
    @Override
    public void onNextButtonPressed() {
        presenter.createNextGeneration();
    }

    @OnClick(R.id.btn_play)
    @Override
    public void onPlayButtonPressed() {
        handler.postDelayed(runnable, (long)(TICK_TIME * playVelocityScale));
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

    @OnClick(R.id.cb_show_grids)
    @Override
    public void onShowGridsPressed() {
        cellsView.showGrids(cbShowGrids.isChecked());
    }



    @OnClick(R.id.btn_clear)
    @Override
    public void onClearButtonPressed() {
        onPauseButtonPressed();
        presenter.init();
    }

    @Override
    public void initDisplay(GameTable table) {
        cellsFrame.removeAllViews();
        cellsView = new CellGridView(this);
        cellsView.setPresenter(presenter);
        cellsView.setNumColumns(table.getWidth());
        cellsView.setNumRows(table.getHeight());
        cellsView.showGrids(cbShowGrids.isChecked());
        cellsFrame.addView(cellsView);
    }

    @Override
    public void displayGeneration(boolean[][] cells) {
        cellsView.setCellChecked(cells);
    }

    @Override
    public void updateTick(int i) {
        tvTick.setText(""+i);
    }

    @Override
    public void showDescription(String description) {
        tvLifDescription.setText(description);
        llLifDescription.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.cb_show_description)
    @Override
    public void onShowDescriptionPressed() {
        if (cbShowDescription.isChecked()) {
            presenter.checkDescription();
        } else {
            llLifDescription.setVisibility(View.INVISIBLE);
        }

    }

    @NonNull
    @Override
    public GameScreenPresenter createPresenter() {
        return new GameScreenPresenter();
    }

    private void initSeeker() {
        sbVelocity.setMax(SEEKBAR_MAX);
        sbVelocity.setProgress(SEEKER_INIT);
        playVelocityScale = 1.0 - ((double)(sbVelocity.getProgress()) / 100.0);
        sbVelocity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                playVelocityScale = 1.0 - ((double)i / 100.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}