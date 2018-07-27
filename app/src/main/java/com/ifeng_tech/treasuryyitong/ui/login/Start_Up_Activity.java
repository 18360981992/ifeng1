package com.ifeng_tech.treasuryyitong.ui.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ifeng_tech.treasuryyitong.R;

/**
 * 程序启动页
 */
public class Start_Up_Activity extends AppCompatActivity {

    int i=0;
    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(i==3){
                Intent intent = new Intent(Start_Up_Activity.this, Login_New_Activity.class);
                startActivity(intent);
                finish();
            }else{
                i++;
                h.sendEmptyMessageDelayed(0,1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__up_);
        i++;
        h.sendEmptyMessageDelayed(0,1000);

    }
}
