package com.wordpress.jonyonandroidcraftsmanship.sharedpreferencedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NextActivity extends Activity {

    private EditText etName;
    private EditText etPassword;
    private static final String DEFAULT = "N/A";
    private static final String EMPTY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        initialize();
    }

    private void initialize() {
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void load(View view) {
        /** SharedPreference
         SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
         String name=sharedPreferences.getString(getResources().getString(R.string.name), DEFAULT);
         String password=sharedPreferences.getString(getResources().getString(R.string.password),DEFAULT);

         if (name.equals(DEFAULT)||password.equals(DEFAULT)){
         Logger.toast(this,"No Data was saved");
         }else{
         etName.setText(name);
         etPassword.setText(password);
         }*/

        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = null;
        int read = -1;
        try {
            fileInputStream = openFileInput("Jony.txt");
            stringBuffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1) {
                stringBuffer.append((char) read);
            }

            String name = stringBuffer.substring(0, stringBuffer.indexOf(" "));
            String password = stringBuffer.substring(stringBuffer.indexOf(" ") + 1);

            if (name.equals(EMPTY) || password.equals(EMPTY)) {
                Logger.toast(this, "No Data was saved");
            } else {
                etName.setText(name);
                etPassword.setText(password);
            }
        } catch (FileNotFoundException e) {
            Logger.log(e.toString());
        } catch (IOException e) {
            Logger.log(e.toString());
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                Logger.log(e.toString());
            }
        }
    }

    public void previous(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
