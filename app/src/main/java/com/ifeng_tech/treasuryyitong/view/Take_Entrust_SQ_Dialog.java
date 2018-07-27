package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;

/**
 * Created by zzt on 2018/7/20.
 */

public class Take_Entrust_SQ_Dialog extends Dialog {

    Context context;
    private TextView donation_dialog_title;
    private TextView take_Entrust_SQ_Dialog_ptname;
    private TextView take_Entrust_SQ_Dialog_goodsname;
    private TextView take_Entrust_SQ_Dialog_danhao;
    private TextView take_Entrust_SQ_Dialog_shuliang;
    private TextView take_Entrust_SQ_Dialog_riqi;
    private TextView take_Entrust_SQ_Dialog_jiandingfei;
    private Button take_Entrust_SQ_Dialog_queren;
    private Button take_Entrust_SQ_Dialog_quxiao;

    public Take_Entrust_SQ_Dialog(@NonNull Context context) {
        super(context);
        this.context = context;
        this.show();
    }

    public Take_Entrust_SQ_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_entrust_sq_dialog);
        take_Entrust_SQ_Dialog_ptname=findViewById(R.id.take_Entrust_SQ_Dialog_ptname);
        take_Entrust_SQ_Dialog_goodsname=findViewById(R.id.take_Entrust_SQ_Dialog_goodsname);
        take_Entrust_SQ_Dialog_danhao=findViewById(R.id.take_Entrust_SQ_Dialog_danhao);
        take_Entrust_SQ_Dialog_shuliang=findViewById(R.id.take_Entrust_SQ_Dialog_shuliang);
        take_Entrust_SQ_Dialog_riqi=findViewById(R.id.take_Entrust_SQ_Dialog_riqi);
        take_Entrust_SQ_Dialog_jiandingfei=findViewById(R.id.take_Entrust_SQ_Dialog_jiandingfei);
        take_Entrust_SQ_Dialog_queren=findViewById(R.id.take_Entrust_SQ_Dialog_queren);
        take_Entrust_SQ_Dialog_quxiao=findViewById(R.id.take_Entrust_SQ_Dialog_quxiao);

        take_Entrust_SQ_Dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        take_Entrust_SQ_Dialog_queren.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                take_Entrust_SQ_Dialog_JieKou.chuan();
                dismiss();
            }
        });
    }

    public void setTake_Entrust_SQ_Dialog_jiandingfei(TextView take_Entrust_SQ_Dialog_jiandingfei) {
        this.take_Entrust_SQ_Dialog_jiandingfei = take_Entrust_SQ_Dialog_jiandingfei;
    }

    public void setTake_Entrust_SQ_Dialog_ptname(String ptname) {
        take_Entrust_SQ_Dialog_ptname.setText(ptname);
    }

    public void setTake_Entrust_SQ_Dialog_goodsname(String goodsname) {
        take_Entrust_SQ_Dialog_goodsname.setText(goodsname);
    }

    public void setTake_Entrust_SQ_Dialog_danhao(String danhao) {
        take_Entrust_SQ_Dialog_danhao.setText(danhao);
    }

    public void setTake_Entrust_SQ_Dialog_shuliang(String shuliang) {
        take_Entrust_SQ_Dialog_shuliang.setText(shuliang);
    }

    public void setTake_Entrust_SQ_Dialog_riqi(String riqi) {
        take_Entrust_SQ_Dialog_riqi.setText(riqi);
    }

    public interface Take_Entrust_SQ_Dialog_JieKou{
        void chuan();
    }
    Take_Entrust_SQ_Dialog_JieKou take_Entrust_SQ_Dialog_JieKou;

    public void setTake_Entrust_SQ_Dialog_JieKou(Take_Entrust_SQ_Dialog_JieKou take_Entrust_SQ_Dialog_JieKou) {
        this.take_Entrust_SQ_Dialog_JieKou = take_Entrust_SQ_Dialog_JieKou;
    }
}
