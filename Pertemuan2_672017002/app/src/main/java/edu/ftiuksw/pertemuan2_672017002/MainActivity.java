package edu.ftiuksw.pertemuan2_672017002;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    public static String public_username = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
    }

    public void Logout(View v){
        setContentView(R.layout.activity_main);
    }

    public void Login(View v){
        String user = txtUsername.getText().toString();
        String pass = txtPassword.getText().toString();

        if (user.equals("admin") && pass.equals("admin")) {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra(public_username, user);
            startActivity(intent);
        } else {
            txtUsername.setError("login gagal");
            txtUsername.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
//            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }

    }

}
