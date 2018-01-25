package com.example.grego.loginregister;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button login;
    EditText text1, text2;
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataHelper(this);
        setContentView(R.layout.activity_main);
        text1 = (EditText) findViewById(R.id.editText2);
        text2 = (EditText) findViewById(R.id.editText);
        login = (Button) findViewById(R.id.button);
        textView1=(TextView) findViewById(R.id.textView);


        //login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SQLiteDatabase db = dbHelper.getReadableDatabase();
                // TODO Auto-generated method stub
                String query="SELECT * FROM user WHERE email='"+text1.getText().toString()+"' and password='"+text2.getText().toString()+"'";

                cursor = db.rawQuery(query,null);
                cursor.moveToFirst();
                if(cursor.getCount()==0){
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_LONG).show();
                }

                //go to Menu Activity
                else {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    Intent a = new Intent (getApplicationContext(),Menu.class);
                    a.putExtra("emailvalue",text1.getText().toString());
                    startActivity(a);
                    finish();
                }


            }
        });

        //register activity
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent (getApplicationContext(),Register.class);
                startActivity(i);

            }
        });
    }
}

