package hu.ott_one.gameoflife.ui.game_screen;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hannesdorfmann.mosby.mvp.layout.MvpLinearLayout;

import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/2/17.
 */
public class LifeLinearLayout extends MvpLinearLayout<IGameView, GamePresenter> implements IGameView {

    CellGridView cellsView;
    private final int TICK_TIME = 80;
    final Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (presenter != null) {
                presenter.createNextGeneration();
            }
            handler.postDelayed(this, TICK_TIME);
        }
    };

    public LifeLinearLayout(Context context) {
        super(context);
    }

    public LifeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LifeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        GameTable table = new GameTable(10, 16);
        presenter.customInit(table);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    @Override
    public GamePresenter createPresenter() {
        return new GamePresenter();
    }

    @Override
    public void onNextButtonPressed() {

    }

    @Override
    public void onPlayButtonPressed() {

    }

    @Override
    public void onPauseButtonPressed() {

    }

    @Override
    public void onShowGridsPressed() {

    }

    @Override
    public void onClearButtonPressed() {

    }

    @Override
    public void initDisplay(GameTable table) {
        cellsView = new CellGridView(getContext());
        cellsView.setTouchable(false);
        cellsView.setPresenter(presenter);
        cellsView.setNumColumns(table.getWidth());
        cellsView.setNumRows(table.getHeight());
        cellsView.showGrids(false);
        cellsView.setBitmap(R.drawable.block_green);
        boolean[][] pattern = new boolean[table.getWidth()][table.getHeight()];
        pattern[2][1] = true;
        pattern[3][2] = true;
        pattern[1][3] = true;
        pattern[2][3] = true;
        pattern[3][3] = true;
        cellsView.initPattern(pattern);
        addView(cellsView);
    }

    @Override
    public void displayGeneration(boolean[][] cells) {
        cellsView.setCellChecked(cells);
    }

    @Override
    public void updateTick(int i) {

    }

    public void onResume() {
        handler.postDelayed(runnable, TICK_TIME);
    }

    public void onStop() {
        handler.removeCallbacks(runnable);
    }
}
