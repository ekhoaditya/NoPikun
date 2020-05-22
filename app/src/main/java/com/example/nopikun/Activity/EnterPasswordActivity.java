package com.example.nopikun.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nopikun.R;
import com.goodiebag.pinview.Pinview;

import androidx.appcompat.app.AppCompatActivity;


public class EnterPasswordActivity extends AppCompatActivity {

    Pinview pinview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_enter_password );


        pinview =  findViewById( R.id.pinview );

        SharedPreferences sp = getSharedPreferences( "Prefs", EnterPasswordActivity.MODE_PRIVATE );
        final String pin = sp.getString( "pin", "" );



        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {


                if(pinview.getValue().equals( pin )){
                    Toast.makeText(getApplicationContext(),"Pin Correct",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Pin Incorrect",Toast.LENGTH_SHORT).show();
                    pinview.setValue("");
                }

            }
        });
    }




    }




