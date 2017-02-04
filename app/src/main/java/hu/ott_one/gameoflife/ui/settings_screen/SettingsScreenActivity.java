package hu.ott_one.gameoflife.ui.settings_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.util.LifReader;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsScreenActivity extends MvpActivity<ISettingsScreenView, SettingsScreenPresenter> implements ISettingsScreenView {

    @BindView(R.id.et_table_width) EditText etWidth;
    @BindView(R.id.et_table_height) EditText etHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initSettings();
    }

    @NonNull
    @Override
    public SettingsScreenPresenter createPresenter() {
        return new SettingsScreenPresenter();
    }

    @OnClick(R.id.btn_save_table)
    @Override
    public void onSaveButtonPressed() {
        int width;
        int height;
        try {
            width = Integer.parseInt(etWidth.getText().toString());
            height = Integer.parseInt(etHeight.getText().toString());
            LifReader lifReader = new LifReader(this);
            lifReader.readFile("lifs/ACORN.LIF");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input parameters", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.saveTableSize(width, height);
    }

    @Override
    public void showSettings(GameTable table) {
        etWidth.setText(""+table.getWidth());
        etHeight.setText(""+table.getHeight());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
