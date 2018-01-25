package com.example.grego.loginregister;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Grego on 10/01/2018.
 */

public class Register extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button regist,back;
    EditText text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        regist = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.button2);

        //registration validation
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(text2.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Name can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (text1.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Email can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String query="SELECT * FROM user WHERE email='"+text1.getText().toString()+"'";
                cursor = db.rawQuery(query,null);
                cursor.moveToFirst();

                    if(cursor.getCount()==0) {
                        String email = text1.getText().toString().trim();

                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                        if (email.matches(emailPattern)) {
                            if(text3.getText().toString().matches("")){
                                Toast.makeText(getApplicationContext(), "Password can't be empty", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else {
                                SQLiteDatabase dbi = dbHelper.getWritableDatabase();
                                dbi.execSQL("insert into user(email, name, password) values('" +
                                        text1.getText().toString() + "','" +
                                        text2.getText().toString() + "','" +
                                        text3.getText().toString() + "');");
                                Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                        else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
                    }
                }



            }
        });
        //back button
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent (getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }
    public void onBackPressed() {

        return;
    }
}
