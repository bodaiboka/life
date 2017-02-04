package hu.ott_one.gameoflife.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import hu.ott_one.gameoflife.model.GameTable;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsInteractor {

    private static final String KEY_TABLE_SETTINGS = "key_table_settings";
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

}