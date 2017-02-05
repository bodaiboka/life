package hu.ott_one.gameoflife.ui.settings_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ott_one.gameoflife.R;
import hu.ott_one.gameoflife.interactor.SettingsInteractor;
import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.util.LifReader;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsScreenActivity extends MvpActivity<ISettingsScreenView, SettingsScreenPresenter> implements ISettingsScreenView {

    @BindView(R.id.et_table_width) EditText etWidth;
    @BindView(R.id.et_table_height) EditText etHeight;
    @BindView(R.id.list_view) ListView patternListView;
    @BindView(R.id.tv_selected_file) TextView tvSelectedFile;
    @BindView(R.id.cb_start_from_pattern) CheckBox cbStartFromPattern;

    String[] from = {"FILE_NAME"};
    int[] to = {R.id.tv_file_name};
    String[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvSelectedFile.setText(SettingsInteractor.getSelectedPattern());
        cbStartFromPattern.setChecked(SettingsInteractor.getCheckBoxState());
        try {
            list = getAssets().list("lifs");
            ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                HashMap<String, String> map = new HashMap<>();
                map.put("FILE_NAME", list[i]);
                mapList.add(map);
                SimpleAdapter adapter = new SimpleAdapter(this, mapList, R.layout.patter_list_item, from, to);
                patternListView.setAdapter(adapter);
                patternListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        tvSelectedFile.setText(list[i]);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (cbStartFromPattern.isChecked()) {
                LifReader lifReader = new LifReader(this);
                lifReader.readFile("lifs/" + tvSelectedFile.getText().toString());
            } else {
                SettingsInteractor.saveInitPattern(null);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input parameters", Toast.LENGTH_SHORT).show();
            return;
        }
        SettingsInteractor.saveCheckBoxState(cbStartFromPattern.isChecked());
        SettingsInteractor.saveSelectedPattern(tvSelectedFile.getText().toString());
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
