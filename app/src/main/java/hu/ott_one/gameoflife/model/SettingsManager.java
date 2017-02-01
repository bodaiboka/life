package hu.ott_one.gameoflife.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by richardbodai on 2/1/17.
 */
public class SettingsManager {

    private static final String KEY_TABLE_SETTINGS = "key_table_settings";
    static SharedPreferences mSharedPreferences;
    static private Context mContext;

    public static void initSharedPreferences(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveTableSettings(GameTable table) {

    }

    public static GameTable getTableSettings() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(KEY_TABLE_SETTINGS, "");
        GameTable table;
        if (json.equals("")) {
            table = new GameTable(15, 15);
        }
        else {
            table = gson.fromJson(json, GameTable.class);
        }
        return table;
    }

}
