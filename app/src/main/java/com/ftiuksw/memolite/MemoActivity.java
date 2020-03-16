package com.ftiuksw.memolite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MemoActivity extends AppCompatActivity {
    private DBHelperMemo mydb;

    TextView txtJudul;
    TextView txtCatatan;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        txtJudul =(TextView) findViewById(R.id.txtJudul);
        txtCatatan =(TextView) findViewById(R.id.txtCatatan);
        mydb = new DBHelperMemo(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String judul = rs.getString(rs.getColumnIndex(DBHelperMemo.COLUMN_NAME));
                String catatan = rs.getString(rs.getColumnIndex(DBHelperMemo.COLUMN_CONTENT));
                if (!rs.isClosed()) rs.close();
                txtJudul.setText((CharSequence) judul);
                txtCatatan.setText((CharSequence) catatan);
                Toast.makeText(this,"proses ubah data " + String.valueOf(Value),Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this,"proses tambah data",Toast.LENGTH_LONG).show();
        }
    }


    public void btnClicked(View v){
        switch (v.getId()) {
            case R.id.btnSimpan:
                Snackbar.make(v, "simpan", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                simpan(v);
                break;
            case R.id.btnHapus:
                Snackbar.make(v, "hapus", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                hapus(v);
                break;
            case R.id.btnKembali:
                this.finish();
                break;
        }
    }
    private void simpan(View v){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int id = extras.getInt("id");
            if (id==0){
                try {
                    if (mydb.insertData(txtJudul.getText().toString(), txtCatatan.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "data tercatat",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "ada kesalahan",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.d("________________bepung",e.getMessage());
                }
            } else { //update data
                try {
                    if (mydb.updateData(id,txtJudul.getText().toString(), txtCatatan.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "data "+String.valueOf(id)+" tersimpan",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "ada kesalahan",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.d("________________bepung",e.getMessage());
                }
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
    }

    private void hapus(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteContact)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mydb.deleteData(id_To_Update);
                        Toast.makeText(getApplicationContext(), "data terhapus",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "batal hapus",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog d = builder.create();
        d.setTitle("Are you sure");
        d.show();
    }
}
