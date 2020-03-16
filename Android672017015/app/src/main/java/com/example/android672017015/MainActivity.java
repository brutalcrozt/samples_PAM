package com.example.android672017015;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername;
    public static String public_username = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsername = findViewById(R.id.txtEditText);


    }

    public void login(View v){
        String user = txtUsername.getText().toString();
        Toast.makeText(this, user , Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Main2Activity.class);
    }

}
