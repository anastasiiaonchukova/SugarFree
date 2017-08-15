package com.anastasiiao.android.sugarfree.Utils;

import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;


public class DatePreference extends DialogPreference {
    public int day = 0;
    public int month = 0;
    public int year=0;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static int parseDay(String value)
    {
        try
        {
            String[] time = value.split("/");
            return (Integer.parseInt(time[0]));
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static int parseMonth(String value)
    {
        try
        {
            String[] time = value.split("/");
            return (Integer.parseInt(time[1]));
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static int parseYear(String value)
    {
        try
        {
            String[] time = value.split("/");
            return (Integer.parseInt(time[2]));
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static String timeToString(int d, int m, int y)
    {
        return String.format("%02d", d) + "/" + String.format("%02d", m)+ "/" + String.format("%02d", y);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue)
    {
        String value;
        if (restoreValue)
        {
            if (defaultValue == null) value = getPersistedString("00/00/0000");
            else value = getPersistedString(defaultValue.toString());
        }
        else
        {
            value = defaultValue.toString();
        }

        day = parseDay(value);
        month = parseMonth(value);
        year = parseYear(value);
    }

    public void persistStringValue(String value)
    {
        persistString(value);
    }
}