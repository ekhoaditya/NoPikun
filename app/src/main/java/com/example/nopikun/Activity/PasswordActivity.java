package com.example.nopikun.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nopikun.DB.DBHelper;
import com.example.nopikun.Model.Password;
import com.example.nopikun.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.nopikun.Constants.LOWERCASE;
import static com.example.nopikun.Constants.NUMBERS;
import static com.example.nopikun.Constants.PASSWORD_VALUE_EXTRA;
import static com.example.nopikun.Constants.SYMBOLS;
import static com.example.nopikun.Constants.UPPERCASE;

public class PasswordActivity extends AppCompatActivity {


    private EditText passwordTitle;
    private EditText edPassword;
    private EditText edUsername;
    private DBHelper sqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //back arrow in the toolbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        if (!intent.hasExtra(PASSWORD_VALUE_EXTRA)) {
            return;
        }
        sqlDb = new DBHelper(this);

        final String password = intent.getStringExtra(PASSWORD_VALUE_EXTRA);
        edPassword = findViewById(R.id.ed_password);
        edPassword.setText(password);

        edPassword.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                calculatePasswordStrength( edPassword.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

                calculatePasswordStrength( edPassword.getText().toString());

            }
        } );

        passwordTitle = findViewById(R.id.ed_title);
        Button btnSave = findViewById(R.id.btn_save);

        edUsername = findViewById( R.id.ed_username );

        ProgressBar bar = findViewById(R.id.progress_horizontal);
        bar.setProgress(calculatePasswordStrength(password));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(passwordTitle.getText())) {
                    Toast.makeText(PasswordActivity.this, getResources().getString(R.string.enter_password_title), Toast.LENGTH_SHORT).show();
                    return;
                }
                String title = passwordTitle.getText().toString();
                String password = edPassword.getText().toString();
                String username = edUsername.getText().toString();




                Password temp_password = new Password( title, username, password );
                long result = sqlDb.insertInto(temp_password);
                if (result > -1) {
                    Toast.makeText(PasswordActivity.this, getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(PasswordActivity.this, getResources().getString(R.string.fail_to_save), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private static int calculatePasswordStrength(String password) {


        int passwordStrength = 0;

        if (password.length() < 7)
            return 1;
        else if (password.length() >= 10)
            passwordStrength += 2;
        else
            passwordStrength += 1;

        if (password.matches(NUMBERS))
            passwordStrength += 2;

        if (password.matches(LOWERCASE))
            passwordStrength += 2;

        if (password.matches(UPPERCASE))
            passwordStrength += 2;

        if (password.matches(SYMBOLS))
            passwordStrength += 2;

        return passwordStrength;

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(PasswordActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
