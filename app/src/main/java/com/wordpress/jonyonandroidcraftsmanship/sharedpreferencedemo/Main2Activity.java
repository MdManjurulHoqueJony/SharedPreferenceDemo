package com.wordpress.jonyonandroidcraftsmanship.sharedpreferencedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends Activity {

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initialize();
    }

    private void initialize() {
        etUserName = (EditText) findViewById(R.id.etUserName);
    }

    public void saveInternalCache(View view) {
        String data=etUserName.getText().toString();
        File folder= getCacheDir();
        File myFile=new File(folder,"MyData1.txt");
        writeFile(myFile,data);
    }

    public void saveExternalCache(View view) {
        String data=etUserName.getText().toString();
        File folder= getExternalCacheDir();
        File myFile=new File(folder,"MyData2.txt");
        writeFile(myFile,data);
    }

    public void savePrivateExternalFile(View view) {
        String data=etUserName.getText().toString();
        File folder= getExternalFilesDir("Jony");
        File myFile=new File(folder,"MyData3.txt");
        writeFile(myFile,data);
    }

    public void savePublicExternalFile(View view) {
        String data=etUserName.getText().toString();
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile=new File(folder,"MyData4.txt");
        writeFile(myFile,data);
    }

    private void writeFile(File myFile, String data) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            Logger.toast(this,data+" was written successfully "+myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            Logger.log(e.toString());
        } catch (IOException e) {
            Logger.log(e.toString());
        } finally{
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Logger.log(e.toString());
                }
            }
        }
    }

    public void next(View view) {
        Intent intent = new Intent(this, Next2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
