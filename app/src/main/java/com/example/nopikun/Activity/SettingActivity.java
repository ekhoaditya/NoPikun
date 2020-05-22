package com.example.nopikun.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.nopikun.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    Switch aSwitch;
    SharedPreferences sp;
    Boolean theme;
    TextView textTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setting );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aSwitch = findViewById(R.id.SwitchTheme);
        textTheme = findViewById(R.id.SwitchText);

        sp = getSharedPreferences("Prefs" , SplashActivity.MODE_PRIVATE);

        theme = sp.getBoolean( "theme", false );

        if(theme){
            aSwitch.setChecked( true );
            textTheme.setText( R.string.dark );
        }else{
            aSwitch.setChecked( false );
            textTheme.setText( R.string.light );
        }



        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (aSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES );
                    sp.edit().putBoolean("theme",aSwitch.isChecked()).commit();
                    textTheme.setText( R.string.dark );
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO );
                    sp.edit().putBoolean("theme",false).commit();
                    textTheme.setText( R.string.light );
                    recreate();
                }

            }
        });



    }

    public void reset(View view) {
        Intent intent_to_reset = new Intent( SettingActivity.this,CreatePasswordActivity.class );
        startActivity( intent_to_reset );
        finish();
    }
}
