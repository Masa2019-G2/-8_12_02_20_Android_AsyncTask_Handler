package com.example.a12_02_20;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Worker implements Runnable {
    private Handler handler;

    public Worker(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(1);
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("MY_TAG", "run: " + i);
            Message msg = handler.obtainMessage(4,i,0);
            handler.sendMessage(msg);
        }

        handler.sendEmptyMessage(2);
        Message msg = handler.obtainMessage();
        msg.what = 3;
        msg.obj = "All done!";
        handler.sendMessage(msg);
    }
}
