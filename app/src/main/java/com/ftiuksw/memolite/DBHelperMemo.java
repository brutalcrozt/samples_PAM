package com.ftiuksw.memolite;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DBHelperMemo extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bepungMemo.db";
    public static final String TABLE_NAME = "memo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "judul";
    public static final String COLUMN_CONTENT = "catatan";
    private HashMap hp;

    public DBHelperMemo(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table memo " +
                        "(id integer primary key, judul text,catatan text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS memo");
        onCreate(db);
    }

    public boolean insertData(String judul, String catatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("judul", judul);
        contentValues.put("catatan", catatan);
        long i = db.insert("memo", null, contentValues);
        return (i>=0);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from memo where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, String judul, String catatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("judul", judul);
        contentValues.put("catatan", catatan);
        long i = db.update("memo", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return (i>=0);
    }

    public Integer deleteData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("memo",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }


    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor res = db.rawQuery("select * from memo", null);
            Log.d("________bepung_jmlData", Integer.toString(res.getCount()));
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                Log.d("_______bepung_baca_data", res.getString(res.getColumnIndex("judul")));
                array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
                res.moveToNext();
            }
        } catch (Exception e) {
            Log.d("___________bepung", e.getMessage());
        }
        return array_list;
    }
}
