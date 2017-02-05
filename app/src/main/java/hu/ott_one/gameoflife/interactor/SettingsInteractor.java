package hu.ott_one.gameoflife.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import hu.ott_one.gameoflife.model.GameModel;
import hu.ott_one.gameoflife.model.GameTable;
import hu.ott_one.gameoflife.model.LifModel;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsInteractor {

    private static final String KEY_TABLE_SETTINGS = "key_table_settings";
    private static final String KEY_INIT_PATTERN = "key_init_pattern";
    private static final String KEY_CHECKBOX = "key_checkbox";
    private static final String KEY_SELECTED_PATTERN = "key_selected_pattern";
    static SharedPreferences mSharedPreferences;

    public static void initSharedPreferences(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveTableSettings(GameTable table) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(table);
        prefsEditor.putString(KEY_TABLE_SETTINGS, json);
        prefsEditor.commit();
    }

    public static GameTable getTableSettings() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(KEY_TABLE_SETTINGS, "");
        GameTable table;
        if (json.equals("")) {
            table = new GameTable(18, 18);
        }
        else {
            table = gson.fromJson(json, GameTable.class);
        }
        return table;
    }

    public static void saveInitPattern(LifModel lifModel) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json;
        if (lifModel == null) {
            json = "";
        } else {
            json = gson.toJson(lifModel);
        }
        prefsEditor.putString(KEY_INIT_PATTERN, json);
        prefsEditor.commit();
    }

    public static LifModel getLifModel() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(KEY_INIT_PATTERN, "");
        LifModel lifModel;
        if (json.equals("")) {
            return null;
        }
        else {
            lifModel = gson.fromJson(json, LifModel.class);
        }
        return lifModel;
    }

    public static void saveCheckBoxState(boolean state) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        prefsEditor.putBoolean(KEY_CHECKBOX, state);
        prefsEditor.commit();
    }

    public static boolean getCheckBoxState() {
        return mSharedPreferences.getBoolean(KEY_CHECKBOX, false);
    }

    public static void saveSelectedPattern(String pattern) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString(KEY_SELECTED_PATTERN, pattern);
        prefsEditor.commit();
    }

    public static String getSelectedPattern() {
        return mSharedPreferences.getString(KEY_SELECTED_PATTERN, "ACORN.LIF");
    }

}
