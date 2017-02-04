package hu.ott_one.gameoflife.ui.game_screen.base_presenter_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import hu.ott_one.gameoflife.R;

/**
 * Created by richardbodai on 2/1/17.
 */
public class BaseCellGridView extends View {
    protected int numColumns, numRows;
    protected int cellWidth, cellHeight;
    protected Paint blackPaint = new Paint();
    protected Paint rectPaint = new Paint();
    protected boolean[][] cellChecked;
    protected LifeOfGamePresenter presenter;
    protected Bitmap bitmap;
    protected Rect bitmapRect;

    public BaseCellGridView(Context context) {
        this(context, null);
    }

    public BaseCellGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        blackPaint.setColor(getResources().getColor(R.color.light_gray));
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.retro_block);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        rectPaint.setShader(shader);
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    protected void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        cellChecked = new boolean[numColumns][numRows];

        invalidate();
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
    }

    public void setPresenter(LifeOfGamePresenter presenter) {
        this.presenter = presenter;
    }

    public void setCellChecked(boolean[][] cellChecked) {
        this.cellChecked = cellChecked;
        invalidate();
    }

    public void initPattern(boolean[][] pattern) {
        cellChecked = pattern;
        presenter.initPattern(pattern);
        invalidate();
    }

    public void setBitmap(int bitmapId) {
        bitmap = BitmapFactory.decodeResource(getResources(), bitmapId);
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }
}