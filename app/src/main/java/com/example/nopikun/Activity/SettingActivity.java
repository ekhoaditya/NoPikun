package com.example.nopikun.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.nopikun.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    Switch aSwitch;
    SharedPreferences sp;
    Boolean theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setting );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aSwitch = findViewById(R.id.SwitchTheme);

        sp = getSharedPreferences("Prefs" , SplashActivity.MODE_PRIVATE);

        theme = sp.getBoolean( "theme", false );

        if(theme){
            aSwitch.setChecked( true );
        }else{
            aSwitch.setChecked( false );
        }



        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (aSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES );
                    sp.edit().putBoolean("theme",aSwitch.isChecked()).commit();
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO );
                    sp.edit().putBoolean("theme",false).commit();
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
