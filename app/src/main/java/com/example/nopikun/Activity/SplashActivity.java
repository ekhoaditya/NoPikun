package com.example.nopikun.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.nopikun.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashActivity extends AppCompatActivity {

    String pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sp = getSharedPreferences("Prefs" , EnterPasswordActivity.MODE_PRIVATE);
        pin  = sp.getString("pin","");


        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splash);
        setTheme();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (pin.equals("")){
                    Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent_2 = new Intent(getApplicationContext(),EnterPasswordActivity.class);
                    startActivity(intent_2);
                    finish();
                }



            }

        },3000);

    }

    public void setTheme(){
        SharedPreferences sp = getSharedPreferences("Prefs" , SplashActivity.MODE_PRIVATE);

        boolean theme = sp.getBoolean( "theme", false );

        if(theme){
            AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES );
        }else{
            AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO );
        }
    }

}
