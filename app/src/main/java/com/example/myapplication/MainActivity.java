package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button btn_add;
    private ProgressBar pb_bottom;
    private int progress=0;
    private EditText edit;
    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add= (Button) findViewById(R.id.btn_add);
        pb_bottom= (ProgressBar) findViewById(R.id.pb_botton);
        edit = findViewById(R.id.edit_01);
        timer = (Chronometer) findViewById(R.id.timer);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progress<100){
                    progress+=10;
                    pb_bottom.setProgress(progress);
                }else {
                    progress=0;
                    pb_bottom.setProgress(progress);
                }
            }

        });
    }
    public void btnClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 3600 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();
           }
    public void stopClick(View view) {
                timer.stop();
            }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString().trim();
        save(inputText);
    }

    public void save(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (writer !=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}}
