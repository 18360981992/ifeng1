package com.ifeng_tech.treasuryyitong.fragmet.zi_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.rili_view.TakeCalendar_Dialog;

/**
 * Created by zzt on 2018/5/14.
 *
 *  注册提货信息页
 */

public class Pick_up_goods_Zhuce extends Fragment  {
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

    private String dataFormate = "yyyy-MM-dd" ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_up_goods_zhuce, container, false);

        initView(view);

        activity = (Pick_up_goods_Activity) getActivity();

        WarehouseBean.DataBean.ListBean warehouseBean = (WarehouseBean.DataBean.ListBean) activity.getIntent().getSerializableExtra("WarehouseBean");
        if(warehouseBean!=null){
            up_goods_zhuce_name.setText(warehouseBean.getGoodsName());
            up_goods_zhuce_word.setText(warehouseBean.getGoodsCode());
            up_goods_zhuce_word.requestFocus();
            up_goods_zhuce_word.setSelection(warehouseBean.getGoodsCode().length());
            up_goods_zhuce_jiaoge_jianshu.setHint("您的最大交割件数"+warehouseBean.getAvailableQty());
        }

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

        up_goods_tijiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                MyUtils.setToast("点击提交。。。");
            }
        });
        up_goods_chongzhi.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                MyUtils.setToast("点击充值。。。");
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

    }


    // 弹出日历的pop框
    private void showPopupWindow(View v) {

        final TakeCalendar_Dialog dialog = new TakeCalendar_Dialog(activity, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(activity,dialog);

        dialog.setOnItemClick(new TakeCalendar_Dialog.OnItemClick() {
            @Override
            public void onItemClick(String date) {
                MyUtils.setToast("点击了：" + date);
                up_goods_zhuce_rili.setText(date);
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
