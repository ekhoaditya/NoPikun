package com.example.nopikun.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.nopikun.R;
import com.goodiebag.pinview.Pinview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CreatePasswordActivity extends AppCompatActivity {

 Pinview pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create_password);

        pin = findViewById( R.id.pinview );

        pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {


                new AlertDialog.Builder(CreatePasswordActivity.this)
                        .setTitle("Pin Confirmation")
                        .setMessage("Are You Sure?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                         //Intent + Shared Preferences

                                SharedPreferences sp = getSharedPreferences("Prefs" , SplashActivity.MODE_PRIVATE);
                                sp.edit().putString("pin",pin.getValue()).commit();

                                Intent intent = new Intent(CreatePasswordActivity.this, EnterPasswordActivity.class);
                                startActivity(intent);
                                finish();

                            }})
                        .setNegativeButton( android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pin.setValue("");
                            }
                        }).show();


            }
        });
    }




}
