package com.anastasiiao.android.sugarfree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.anastasiiao.android.sugarfree.R.id;
import static com.anastasiiao.android.sugarfree.R.layout;
import static com.anastasiiao.android.sugarfree.R.string;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView showTimePassed;
    private SharedPreferences _sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        showTimePassed = (TextView) findViewById(id.text_view);
        setupSharedPreferences();
        setTitle(string.toolbar_header);
        MobileAds.initialize(this,getString(R.string.adUnitId));
        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adView);

        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this._sharedPreferences = sharedPreferences;
        setTimePassed();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals("quit")) {
            setTimePassed();
        }
    }

    private void setTimePassed() {
        showTimePassed.setText(getString(R.string.no_eat_message) + " " + calculateNumberOfDays());
    }

    private String calculateNumberOfDays() {
        Date d1;
        Date d2;
        String resultDiff = "";
        String quitDate = _sharedPreferences.getString("quit", "0");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String todayDate = formatter.format(new Date());

        try {
            d1 = formatter.parse(quitDate);
            d2 = formatter.parse(todayDate);

            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);
            Period p = new Period(dt1, dt2, PeriodType.yearMonthDayTime());
            int yearsBetween = p.getYears();
            int monthBetween = p.getMonths();
            int daysBetween = p.getDays();
            if (yearsBetween != 0) {
                resultDiff = yearsBetween + " Y ";
            }
            if (monthBetween != 0) {
                resultDiff += monthBetween + " M ";
            }
            resultDiff += daysBetween + " D.";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultDiff;

    }
}
