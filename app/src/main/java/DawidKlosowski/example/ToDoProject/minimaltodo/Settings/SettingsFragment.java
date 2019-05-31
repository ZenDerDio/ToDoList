package DawidKlosowski.example.ToDoProject.minimaltodo.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;

import DawidKlosowski.example.ToDoProject.minimaltodo.Analytics.AnalyticsApplication;
import DawidKlosowski.example.ToDoProject.minimaltodo.Main.MainFragment;
import com.example.Project.minimaltodo.R;
import DawidKlosowski.example.ToDoProject.minimaltodo.Utility.PreferenceKeys;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    AnalyticsApplication app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_layout);
        app = (AnalyticsApplication) getActivity().getApplication();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PreferenceKeys preferenceKeys = new PreferenceKeys(getResources());
        if (key.equals(preferenceKeys.night_mode_pref_key)) {
            SharedPreferences themePreferences = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor themeEditor = themePreferences.edit();
            //We tell our MainLayout to recreate itself because mode has changed
            themeEditor.putBoolean(MainFragment.RECREATE_ACTIVITY, true);

            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(preferenceKeys.night_mode_pref_key);
            if (checkBoxPreference.isChecked()) {
                //if not using Google Analytics
                app.send(this, "Settings", "Night Mode used");
                themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.DARKTHEME);
            } else {
                themeEditor.putString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
            }
            themeEditor.apply();

            getActivity().recreate();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
