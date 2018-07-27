package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by zzt on 2018/7/12.
 */

public class TakeImage_Dialog extends Dialog {

    private ImageView takeImage_Dialog_img;
    private EditText takeImage_Dialog_ed1;
    private EditText takeImage_Dialog_ed2;
    private EditText takeImage_Dialog_ed3;
    private EditText takeImage_Dialog_ed4;
    private Button takeImage_Dialog_btn;

    Context context;

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            takeImage_Dialog_JieKou.chuan(ss);
        }
    };
    private String ss;

    public TakeImage_Dialog(@NonNull Context context) {
        super(context);
        this.show();
        this.context=  context;
    }

    public TakeImage_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.show();
        this.context=  context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeimage_dialog);
        takeImage_Dialog_img = findViewById(R.id.takeImage_Dialog_img);
        takeImage_Dialog_ed1 = findViewById(R.id.takeImage_Dialog_ed1);
        takeImage_Dialog_ed2 = findViewById(R.id.takeImage_Dialog_ed2);
        takeImage_Dialog_ed3 = findViewById(R.id.takeImage_Dialog_ed3);
        takeImage_Dialog_ed4 = findViewById(R.id.takeImage_Dialog_ed4);
        takeImage_Dialog_btn = findViewById(R.id.takeImage_Dialog_btn);



        takeImage_Dialog_ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    showSoftInputFromWindow(takeImage_Dialog_ed2);
                }
            }
        });
        takeImage_Dialog_ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    showSoftInputFromWindow(takeImage_Dialog_ed3);
                }else{
                    showSoftInputFromWindow(takeImage_Dialog_ed1);
                }
            }
        });
        takeImage_Dialog_ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    showSoftInputFromWindow(takeImage_Dialog_ed4);
                }else{
                    showSoftInputFromWindow(takeImage_Dialog_ed2);
                }
            }
        });
        takeImage_Dialog_ed4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<=0){
                    showSoftInputFromWindow(takeImage_Dialog_ed3);
                }
            }
        });

        takeImage_Dialog_img.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                takeImage_Dialog_JieKou.qiehuan(takeImage_Dialog_img);
            }
        });

        takeImage_Dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(takeImage_Dialog_ed4.getWindowToken(), 0);

                String ed1 = takeImage_Dialog_ed1.getText().toString().trim();
                if (TextUtils.isEmpty(ed1)) {
                    MyUtils.setToast("请输入正确图形验证码");
                    return;
                }
                String ed2 = takeImage_Dialog_ed2.getText().toString().trim();
                if (TextUtils.isEmpty(ed2)) {
                    MyUtils.setToast("请输入正确图形验证码");
                    return;
                }
                String ed3 = takeImage_Dialog_ed3.getText().toString().trim();
                if (TextUtils.isEmpty(ed3)) {
                    MyUtils.setToast("请输入正确图形验证码");
                    return;
                }
                String ed4 = takeImage_Dialog_ed4.getText().toString().trim();
                if (TextUtils.isEmpty(ed4)) {
                    MyUtils.setToast("请输入正确图形验证码");
                    return;
                }

                ss = ed1+ed2+ed3+ed4;
                h.sendEmptyMessageDelayed(0,300);
            }
        });
    }

    // 设置图形验证码
    public void setTakeImage_Dialog_img(Bitmap bitmap){
        takeImage_Dialog_img.setImageBitmap(bitmap);
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public  void showSoftInputFromWindow(final EditText editText) {
        editText.setSelection(editText.length());
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //将输入法切换到英文
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        DashApplication.getAppHanler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager =(InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 100);
    }

    @Override
    public void show() {
        super.show();
        showSoftInputFromWindow(takeImage_Dialog_ed1);
    }

    public interface TakeImage_Dialog_JieKou{
        void qiehuan(ImageView takeImage_Dialog_img);
        void chuan(String ss);
    }
    TakeImage_Dialog_JieKou takeImage_Dialog_JieKou;

    public void setTakeImage_Dialog_JieKou(TakeImage_Dialog_JieKou takeImage_Dialog_JieKou) {
        this.takeImage_Dialog_JieKou = takeImage_Dialog_JieKou;
    }
}
