package com.dvidal.beastweather.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.dvidal.beastweather.R;
import com.dvidal.beastweather.infrastructure.BeastWeatherApplication;

/**
 * Created by diegovidal on 07/12/2017.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SortPreferenceFragment())
                .commit();
    }

    public static class SortPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_general);

            bindPreferenceSummaryToValue(findPreference(getString(R.string.preference_location_key)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.preference_units_key)));
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            setPreferenceSummary(preference, newValue);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (preference instanceof  EditTextPreference){
                editor.putString(BeastWeatherApplication.LOCATION_PREFERENCE, newValue.toString()).apply();
            } else if (preference instanceof ListPreference){
                editor.putString(BeastWeatherApplication.UNIT_PREFERENCE, newValue.toString()).apply();
            }

            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference){

            preference.setOnPreferenceChangeListener(this);
            setPreferenceSummary(preference,
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                    .getString(preference.getKey(), ""));
        }

        private void setPreferenceSummary(Preference preference, Object value){

            String stringValue = value.toString();

            if (preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int preferenceIndex = listPreference.findIndexOfValue(stringValue);

                if (preferenceIndex >= 0){
                    preference.setSummary(listPreference.getEntries() [preferenceIndex]);
                }

            } else if (preference instanceof EditTextPreference){
                preference.setSummary(stringValue);
            }
        }
    }
}
