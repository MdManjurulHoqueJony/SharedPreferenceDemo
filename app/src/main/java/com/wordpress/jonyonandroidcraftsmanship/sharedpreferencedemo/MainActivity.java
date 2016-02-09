package com.wordpress.jonyonandroidcraftsmanship.sharedpreferencedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private EditText etName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void save(View view) {
        /** SharedPreference
         SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
         SharedPreferences.Editor editor=sharedPreferences.edit();
         editor.putString(getResources().getString(R.string.name),etName.getText().toString());
         editor.putString(getResources().getString(R.string.password),etPassword.getText().toString());
         editor.commit();
         Logger.toast(this,"Data was saved successfully");*/

        String name = etName.getText().toString() + " ";
        String password = etPassword.getText().toString();
        File file = null;
        FileOutputStream fileOutputStream = null;

        try {
            file = getFilesDir();
            fileOutputStream = openFileOutput("Jony.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(password.getBytes());
        } catch (FileNotFoundException e) {
            Logger.log(e.toString());
        } catch (IOException e) {
            Logger.log(e.toString());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Logger.log(e.toString());
                }
            }
        }

        Logger.toast(this, "Data was saved successfully in " + file + "/Jony.txt");
    }

    public void next(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
