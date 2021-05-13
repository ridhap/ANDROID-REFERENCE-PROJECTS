package com.example.stopwaatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView currentTime;
    Handler handler;
    int timeCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTime=findViewById(R.id.current_time);

        handler=new Handler();

    }

    public void onStartClicked(View view) {
        handler=null;
        handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String temp= Integer.toString(timeCount);
                currentTime.setText(temp);
                timeCount++;
                if(handler!=null)
                {
                    handler.postDelayed(this,60);
                }

            }
        });
    }
    public void demoThread(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Looper.loop();
            }
        });
    }
    public void onStopClicked(View view) {
        handler=null;
        handler=new Handler();
    }

    public void onResetClicked(View view) {
        handler=null;
        timeCount=0;

    }
}
