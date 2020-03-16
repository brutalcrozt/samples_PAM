package com.example.preferens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu (Menu menu){
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.pengaturan, menu);
    return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        Intent i = new Intent(this, MenuPengaturan.class);
        startActivity(i);
        return true;
    }

    public void buttonClicked(View v) {
        SharedPreferences sp_pengaturan = PreferenceManager.getDefaultSharedPreferences(this);
        String sl = sp_pengaturan.getString("key_edit", "");
        TextView tv = (TextView) findViewById(R.id.teks);
        tv.setText(sl);

        boolean cek = sp_pengaturan.getBoolean("key_check", false);
        if (cek){
            tv.setText(sl);
        }
    }
}
