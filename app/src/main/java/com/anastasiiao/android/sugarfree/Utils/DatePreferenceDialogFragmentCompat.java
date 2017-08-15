package com.anastasiiao.android.sugarfree.Utils;

import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * Created by aonchukova on 14/08/2017.
 */

public class DatePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat implements DialogPreference.TargetFragment {
    DatePicker datePicker = null;

    @Override
    protected View onCreateDialogView(Context context) {
        datePicker = new DatePicker(context);
        return (datePicker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        //timePicker.setIs24HourView(true);
        DatePreference pref = (DatePreference) getPreference();
        //timePicker.setCurrentHour(pref.hour);
        // timePicker.setCurrentMinute(pref.minute);
    }


    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            DatePreference pref = (DatePreference) getPreference();
            pref.day = datePicker.getDayOfMonth();
            pref.month = datePicker.getMonth();
            pref.year = datePicker.getYear();

            String value = DatePreference.timeToString(pref.day, pref.month, pref.year);
            if (pref.callChangeListener(value)) pref.persistStringValue(value);
        }
    }

    @Override
    public Preference findPreference(CharSequence charSequence) {
        return getPreference();
    }
}
