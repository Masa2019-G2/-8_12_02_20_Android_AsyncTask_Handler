package com.example.a12_02_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTxt, countTxt;
    Button startBtn;
    ProgressBar myProgress;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        handler = new Handler(callback);
        resultTxt = findViewById(R.id.resultTxt);
        countTxt = findViewById(R.id.countTxt);

        startBtn = findViewById(R.id.startBtn);
        myProgress = findViewById(R.id.myProgress);

        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.startBtn){
            new Thread(new Worker(handler)).start();
        }
    }

    Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    myProgress.setVisibility(View.VISIBLE);
                    startBtn.setEnabled(false);
                    return true;
                case 2:
                    myProgress.setVisibility(View.INVISIBLE);
                    startBtn.setEnabled(true);
                    return true;
                case 3:
                    resultTxt.setText(String.valueOf(msg.obj));
                    return true;
                case 4:
                    countTxt.setText(String.valueOf(msg.arg1));
                    return true;
            }
            return false;
        }
    };
}
