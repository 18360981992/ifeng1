package com.ifeng_tech.treasuryyitong.ui.my.bangzhu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Help_Activity_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.bangzu.Help_One_Bean;
import com.ifeng_tech.treasuryyitong.bean.bangzu.Help_Two_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyExpandableListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 帮助
 */
public class Help_Activity extends BaseMVPActivity<Help_Activity, MyPresenter<Help_Activity>> {

    private RelativeLayout help_Fan;
    private MyExpandableListView help_MyExpandableListView;
    public Help_Activity_Adapter help_activity_adapter;
    private LinearLayout help_null;

    @Override
    public MyPresenter<Help_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_);
        initView();

        help_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, "正在加载...");

        myPresenter.getPreContent(APIs.getColumns, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if (code.equals("2000")) {
                        Help_One_Bean help_one_bean = new Gson().fromJson(json, Help_One_Bean.class);
                        List<Help_One_Bean.DataBean.ListBean> list = help_one_bean.getData().getList();
                        setHelp_Adapter(list);
                    } else {
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
            }
        });
    }

    public void setHelp_Adapter(final List<Help_One_Bean.DataBean.ListBean> list) {
        if (list.size() > 0) {
            help_null.setVisibility(View.GONE);
            help_MyExpandableListView.setVisibility(View.VISIBLE);
            if (help_activity_adapter == null) {
                help_activity_adapter = new Help_Activity_Adapter(Help_Activity.this, list,help_MyExpandableListView);
                help_MyExpandableListView.setAdapter(help_activity_adapter);
            } else {
                help_activity_adapter.notifyDataSetChanged();
            }


            help_activity_adapter.setHelp_Activity_Adapter_JieKou(new Help_Activity_Adapter.Help_Activity_Adapter_JieKou() {
                @Override
                public void one_chuan(final int position) {
                    //  进度框
                    final ProgressDialog aniDialog = MyUtils.getProgressDialog(Help_Activity.this, "正在加载...");
                    String articleListByMainColumnId = APIs.getArticleListByMainColumnId(list.get(position).getId());
                    myPresenter.getPreContent(articleListByMainColumnId, new MyInterfaces() {
                        @Override
                        public void chenggong(String json) {
                            aniDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String code = (String) jsonObject.get("code");
                                if (code.equals("2000")) {
                                    Help_Two_Bean help_two_bean = new Gson().fromJson(json, Help_Two_Bean.class);
                                    List<Help_Two_Bean.DataBean.ListBean> list1 = help_two_bean.getData().getList();
                                    list.get(position).setList(list1);
                                    help_activity_adapter.notifyDataSetChanged();
                                    help_MyExpandableListView.expandGroup(position);
                                }else {
                                    MyUtils.setToast((String) jsonObject.get("message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void shibai(String ss) {
                            aniDialog.dismiss();
                            MyUtils.setToast(ss);
                        }
                    });
                }

                @Override
                public void two_chuan(int groupPosition, int childPosition) {
//                    MyUtils.setToast("点击了二级列表。。。");
                    Intent intent = new Intent(Help_Activity.this, Help_Dails_Activity.class);
                    intent.putExtra("erjid",list.get(groupPosition).getList().get(childPosition).getId());
                    intent.putExtra("index",list.get(groupPosition).getList().get(childPosition).getRowno());
                    intent.putExtra("mainId",list.get(groupPosition).getId());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            });

        } else {
            help_null.setVisibility(View.VISIBLE);
            help_MyExpandableListView.setVisibility(View.GONE);
        }


    }

    private void initView() {
        help_Fan = (RelativeLayout) findViewById(R.id.help_Fan);
        help_MyExpandableListView = (MyExpandableListView) findViewById(R.id.help_MyExpandableListView);
        help_null = (LinearLayout) findViewById(R.id.help_null);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
