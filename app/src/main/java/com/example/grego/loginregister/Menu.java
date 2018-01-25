package com.example.grego.loginregister;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Grego on 10/01/2018.
 */

public class Menu extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.textView);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT name FROM user WHERE email = '" +
                getIntent().getStringExtra("emailvalue") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText("Welcome " + cursor.getString(0).toString());

        }
    }
}

