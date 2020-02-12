package com.example.a12_02_20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar myProgress, horProgress;
    Button startBtn, stopBtn;
    TextView resultTxt,countTxt;
    MyTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        myProgress = findViewById(R.id.myProgress);
        horProgress = findViewById(R.id.horProgress);
        resultTxt = findViewById(R.id.resultTxt);
        countTxt = findViewById(R.id.countTxt);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.startBtn){
            horProgress.setMax(200);
            task = new MyTask();
            task.execute(200,10000);

//            doSome("String",100,new int[5]);
//            doSome("String",10,new int[10]);
//            doSome("String",10,new int[0],30,40);
//            int[] arr = {1,2,3,4,5};
//            doSome("String",100,arr,10);
        }else if(v.getId() == R.id.stopBtn){
            task.cancel(true);
        }
    }

    public void doSome(String str, int i,int[] arr1,int... arr){
        System.out.println(arr.length);
    }

    class MyTask extends AsyncTask<Integer,Integer,String>{
        @Override
        protected void onPreExecute() {
            myProgress.setVisibility(View.VISIBLE);
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            countTxt.setText(String.valueOf(values[0]));
            horProgress.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Integer... values) {
            for(int i = 0; i < values[0]; i++){
                try {
                    Thread.sleep(values[1]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i+1);
                Log.d("MY_TAG", "doInBackground: " + i);
                if(isCancelled()){
                    break;
                }
            }
            return "All done";
        }

        @Override
        protected void onCancelled(String s) {
            onPostExecute(s);
        }

        @Override
        protected void onPostExecute(String result) {
            myProgress.setVisibility(View.INVISIBLE);
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            resultTxt.setText(result);
        }
    }
}
