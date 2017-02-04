package hu.ott_one.gameoflife.ui.custom_view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.hannesdorfmann.mosby.mvp.layout.MvpLinearLayout;

import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.ui.game_screen.CellGridView;
import hu.ott_one.gameoflife.ui.game_screen.base_presenter_view.ILifeOfGameView;
import hu.ott_one.gameoflife.ui.game_screen.base_presenter_view.LifeOfGamePresenter;

/**
 * Created by richardbodai on 2/2/17.
 */
public class LifeLinearLayout extends MvpLinearLayout<ILifeOfGameView, LifeOfGamePresenter<ILifeOfGameView>> implements ILifeOfGameView {

    private CellGridView cellsView;
    private final int TICK_TIME = 80;
    private final Handler handler = new Handler();
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
        presenter.init(table);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    @Override
    public LifeOfGamePresenter createPresenter() {
        return new LifeOfGamePresenter<>();
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
        // egy glider-rel inicializálom a grid-et
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

    public void onResume() {
        handler.postDelayed(runnable, TICK_TIME);
    }

    public void onStop() {
        handler.removeCallbacks(runnable);
    }
}
