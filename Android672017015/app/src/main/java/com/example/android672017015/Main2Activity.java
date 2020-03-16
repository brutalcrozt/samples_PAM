package com.example.android672017015;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView txtWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtWelcome = findViewById(R.id.txtWelcome);

        Intent intent =getIntent();

        String username= Intent.getStringExtra(MainActivity.public_username);
        String text ="Welcome,"+username;
        txtWelcome.setText(text);
    }
}
