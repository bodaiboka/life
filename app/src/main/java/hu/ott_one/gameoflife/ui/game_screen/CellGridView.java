package hu.ott_one.gameoflife.ui.game_screen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import hu.ott_one.gameoflife.ui.game_screen.base_presenter_view.BaseCellGridView;

/**
 * Created by richardbodai on 2/1/17.
 */
public class CellGridView extends BaseCellGridView {

    private GameScreenPresenter presenter;
    private boolean isVisibleGrids = false;
    private boolean isTouchable = true;

    public CellGridView(Context context) {
        this(context, null);
    }

    public CellGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cellChecked[i][j]) {

                    Rect rect = new Rect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight);

                    /*canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            rectPaint);*/

                    canvas.drawBitmap(bitmap, bitmapRect, rect, null);
                }
            }
        }

        if (isVisibleGrids) {
            for (int i = 1; i < numColumns; i++) {
                canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
            }

            for (int i = 1; i < numRows; i++) {
                canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isTouchable) {
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);

            cellChecked[column][row] = !cellChecked[column][row];
            invalidate();
            presenter.onCellClicked(column, row, cellChecked[column][row]);
        }

        return true;
    }

    public void setPresenter(GameScreenPresenter presenter) {
        this.presenter = presenter;
    }

    public void showGrids(boolean checked) {
        isVisibleGrids = checked;
        invalidate();
    }

    public void setTouchable(boolean touchable) {
        isTouchable = touchable;
    }

}