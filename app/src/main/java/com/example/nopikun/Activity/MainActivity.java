package com.example.nopikun.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.nopikun.Adapter.RecyclerAdapter;
import com.example.nopikun.CustomeClick;
import com.example.nopikun.DB.DBHelper;
import com.example.nopikun.Fragments.BottomFragment;
import com.example.nopikun.Model.Password;
import com.example.nopikun.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.nopikun.Constants.PASSWORD_OBJECT_EXTRA;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    RecyclerView recyclerView;
    TextView textNoPassword;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_setting:
                        Intent intent_to_setting = new Intent( MainActivity.this,SettingActivity.class );
                        startActivity( intent_to_setting );
                        return true;
                    case R.id.menu_exit:
                        finishAndRemoveTask();
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        textNoPassword = findViewById( R.id.textNoPassword );


        dbHelper = new DBHelper(this);

        if(dbHelper.savedPassword().isEmpty()){

            textNoPassword.setVisibility(View.VISIBLE );
        }
        else{
            textNoPassword.setVisibility(View.INVISIBLE );
        }

        recyclerView = findViewById( R.id.recyclerView );
        RecyclerAdapter adapter = new RecyclerAdapter(this, dbHelper.savedPassword(), new CustomeClick() {
            @Override
            public void onClick(Password p) {

                Intent intent = new Intent(MainActivity.this,PasswordInfoActivity.class);
                intent.putExtra(PASSWORD_OBJECT_EXTRA,p);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.fab_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomFragment fragment = new BottomFragment();
                fragment.show(getSupportFragmentManager(),"length");
            }
        });




    }
}
