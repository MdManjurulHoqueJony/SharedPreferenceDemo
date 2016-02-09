package com.wordpress.jonyonandroidcraftsmanship.sharedpreferencedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Next2Activity extends Activity {

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2);
        initialize();
    }

    private void initialize() {
        etUserName= (EditText) findViewById(R.id.etUserName);
    }

    public void loadInternalCache(View view) {
        File folder= getCacheDir();
        File myFile=new File(folder,"MyData1.txt");
        String data=readData(myFile);
        showData(data);
    }

    public void loadExternalCache(View view) {
        File folder= getExternalCacheDir();
        File myFile=new File(folder,"MyData2.txt");
        String data=readData(myFile);
        showData(data);
    }

    public void loadPrivateExternalFile(View view) {
        File folder= getExternalFilesDir("Jony");
        File myFile=new File(folder,"MyData3.txt");
        String data=readData(myFile);
        showData(data);
    }

    public void loadPublicExternalFile(View view) {
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile=new File(folder,"MyData4.txt");
        String data=readData(myFile);
        showData(data);
    }

    private String readData(File myFile){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream(myFile);
            int read=-1;
            StringBuffer stringBuffer=new StringBuffer();
            while((read=fileInputStream.read())!=-1){
                stringBuffer.append((char)read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            Logger.log(e.toString());
        } catch (IOException e) {
            Logger.log(e.toString());
        } finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Logger.log(e.toString());
                }
            }
        }
        return null;
    }

    private void showData(String data) {
        if (data!=null){
            etUserName.setText(data);
        }else{
            etUserName.setText("No Data");
        }
    }

    public void previous(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
