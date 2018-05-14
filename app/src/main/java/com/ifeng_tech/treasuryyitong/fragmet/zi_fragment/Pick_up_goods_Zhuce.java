package com.ifeng_tech.treasuryyitong.fragmet.zi_fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.ui.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.rili_utils.DataUtils;
import com.ifeng_tech.treasuryyitong.view.rili_view.DatePopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzt on 2018/5/14.
 */

public class Pick_up_goods_Zhuce extends Fragment implements View.OnClickListener {
    public LinearLayout fu;
    private EditText up_goods_zhuce_name;
    private EditText up_goods_zhuce_word;
    private TextView up_goods_zhuce_cangku;
    private LinearLayout up_goods_zhuce_cangku_img;
    private EditText up_goods_zhuce_jiaoge_jianshu;
    private EditText up_goods_zhuce_jiaoge_shuliang;
    private TextView up_goods_zhuce_rili;
    private ImageView up_goods_zhuce_rili_img;
    private TextView up_goods_zhuce_pass_tishi;
    private EditText up_goods_zhuce_pass;
    private TextView up_goods_zhuce_zai_tishi;
    private EditText up_goods_zhuce_zai;
    private Button up_goods_tijiao;
    private Button up_goods_chongzhi;
    private Pick_up_goods_Activity activity;

    private DatePopupWindow popupWindow;
    private String dataFormate = "yyyy-MM-dd" ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_up_goods_zhuce, container, false);

        initView(view);

        activity = (Pick_up_goods_Activity) getActivity();

        up_goods_zhuce_cangku_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了仓库选择。。。");
            }
        });

        up_goods_zhuce_rili_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(up_goods_zhuce_rili_img);
            }
        });
        return view;
    }

    private void initView(View view) {
        fu = (LinearLayout)view.findViewById(R.id.fu);
        up_goods_zhuce_name = (EditText) view.findViewById(R.id.up_goods_zhuce_name);
        up_goods_zhuce_word = (EditText) view.findViewById(R.id.up_goods_zhuce_word);
        up_goods_zhuce_cangku = (TextView) view.findViewById(R.id.up_goods_zhuce_cangku);
        up_goods_zhuce_cangku_img = (LinearLayout) view.findViewById(R.id.up_goods_zhuce_cangku_img);
        up_goods_zhuce_jiaoge_jianshu = (EditText) view.findViewById(R.id.up_goods_zhuce_jiaoge_jianshu);
        up_goods_zhuce_jiaoge_shuliang = (EditText) view.findViewById(R.id.up_goods_zhuce_jiaoge_shuliang);
        up_goods_zhuce_rili = (TextView) view.findViewById(R.id.up_goods_zhuce_rili);
        up_goods_zhuce_rili_img = (ImageView) view.findViewById(R.id.up_goods_zhuce_rili_img);
        up_goods_zhuce_pass_tishi = (TextView) view.findViewById(R.id.up_goods_zhuce_pass_tishi);
        up_goods_zhuce_pass = (EditText) view.findViewById(R.id.up_goods_zhuce_pass);
        up_goods_zhuce_zai_tishi = (TextView) view.findViewById(R.id.up_goods_zhuce_zai_tishi);
        up_goods_zhuce_zai = (EditText) view.findViewById(R.id.up_goods_zhuce_zai);
        up_goods_tijiao = (Button) view.findViewById(R.id.up_goods_tijiao);
        up_goods_chongzhi = (Button) view.findViewById(R.id.up_goods_chongzhi);

        up_goods_tijiao.setOnClickListener(this);
        up_goods_chongzhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_goods_tijiao:

                break;
            case R.id.up_goods_chongzhi:

                break;
        }
    }

    // 弹出日历的pop框
    private void showPopupWindow(View v) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);

        popupWindow = new DatePopupWindow(activity, format);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(fu, Gravity.CENTER, 0, 0);
        popupWindow.setOnItemClick(new DatePopupWindow.OnItemClick() {
            @Override
            public void onItemClick(String date) {
                up_goods_zhuce_rili.setText(""+DataUtils.formatDate(date,dataFormate));
            }
        });

    }

    private void submit() {
        // validate
        String name = up_goods_zhuce_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "请输入藏品名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String word = up_goods_zhuce_word.getText().toString().trim();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(getContext(), "请输入藏品代码", Toast.LENGTH_SHORT).show();
            return;
        }

        String jianshu = up_goods_zhuce_jiaoge_jianshu.getText().toString().trim();
        if (TextUtils.isEmpty(jianshu)) {
            Toast.makeText(getContext(), "您的最大交割件数1000", Toast.LENGTH_SHORT).show();
            return;
        }

        String shuliang = up_goods_zhuce_jiaoge_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)) {
            Toast.makeText(getContext(), "请输入交割数量", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass = up_goods_zhuce_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getContext(), "6-10个字符,区分大小写", Toast.LENGTH_SHORT).show();
            return;
        }

        String zai = up_goods_zhuce_zai.getText().toString().trim();
        if (TextUtils.isEmpty(zai)) {
            Toast.makeText(getContext(), "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
