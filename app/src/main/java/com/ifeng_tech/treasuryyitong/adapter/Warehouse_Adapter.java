package com.ifeng_tech.treasuryyitong.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.ui.my.Donation_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.tihuo.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.bind_email.Bind_Email_Activity1;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.TakeCommonDialog;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 *
 *  一级页面的仓库
 */

public class Warehouse_Adapter extends BaseAdapter{

    Context context;
    List<WarehouseBean.DataBean.ListBean> warehouselist;
    private final Activity activity;
    WarehouseBean warehouseBean;
    public Warehouse_Adapter(Context context, List<WarehouseBean.DataBean.ListBean> warehouselist,WarehouseBean warehouseBean) {
        this.context = context;
        this.warehouselist = warehouselist;
        this.warehouseBean= warehouseBean;
        activity = (Activity) context;
    }
    @Override
    public int getCount() {
        return warehouselist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        convertView=View.inflate(context,R.layout.warehouse_xrecycle_item,null);

        TextView warehouse_shopping_cword = convertView.findViewById(R.id.warehouse_shopping_cword);
        TextView warehouse_shopping_name = convertView.findViewById(R.id.warehouse_shopping_name);
        TextView warehouse_keyong_num = convertView.findViewById(R.id.warehouse_keyong_num);
        LinearLayout warehouse_item_touming = convertView.findViewById(R.id.warehouse_item_touming);
        TextView warehouse_item_zhuanzeng = convertView.findViewById(R.id.warehouse_item_zhuanzeng);
        TextView warehouse_item_tihuo = convertView.findViewById(R.id.warehouse_item_tihuo);

        if(warehouselist.get(position).getGoodsName().length()>20){
            String name = warehouselist.get(position).getGoodsName().substring(0, 20);
            warehouse_shopping_name.setText(name+"...");
        }else{
            warehouse_shopping_name.setText(warehouselist.get(position).getGoodsName());
        }

        warehouse_shopping_cword.setText("编号："+warehouselist.get(position).getGoodsCode());

        warehouse_keyong_num.setText(warehouselist.get(position).getAvailableQty()+"（枚）");

        if(warehouselist.get(position).isFlag()){
            warehouse_item_touming.setVisibility(View.VISIBLE);
        }else{
            warehouse_item_touming.setVisibility(View.GONE);
        }

        warehouse_item_zhuanzeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击转赠");
                WarehouseBean.DataBean.ListBean listBean = warehouselist.get(position);
                listBean.setFlag(false);
                warehouselist.set(position,listBean);
                notifyDataSetChanged();

                // 判断是否有绑定过业务密码
                String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                if(yewu_pass.equals("0")){
                    Intent intent = new Intent(activity, Donation_Activity.class);
                    intent.putExtra("WarehouseBean", warehouselist.get(position));
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    // 使用自定义的dialog框
                    final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(activity, R.style.dialog_setting,"请先设置业务密码！");
                    MyUtils.getPuTongDiaLog(activity,takeCommonDialog);
                    takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                        @Override
                        public void quxiao() {
                            takeCommonDialog.dismiss();
                        }

                        @Override
                        public void queren() {
                            takeCommonDialog.dismiss();
                            Intent intent = new Intent(activity, Bind_Email_Activity1.class);
                            intent.putExtra("title","业务密码（设置）");
                            intent.putExtra("select",SP_String.YEWUMIMA);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                    });
                }

            }
        });

        warehouse_item_tihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击提货");
                WarehouseBean.DataBean.ListBean listBean = warehouselist.get(position);
                listBean.setFlag(false);
                warehouselist.set(position,listBean);
                notifyDataSetChanged();
                WarehouseBean.DataBean.MaxTrasferableAmountAndFeeBean maxTrasferableAmountAndFee = warehouseBean.getData().getMaxTrasferableAmountAndFee();

                // 判断是否有绑定过业务密码
                String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                if(yewu_pass.equals("0")){
                    Intent intent1 = new Intent(activity, Pick_up_goods_Activity.class);
                    intent1.putExtra("WarehouseBean", warehouselist.get(position));
                    intent1.putExtra("MaxTrasferableAmountAndFeeBean",maxTrasferableAmountAndFee);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    // 使用自定义的dialog框
                    final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(activity, R.style.dialog_setting,"请先设置业务密码！");
                    MyUtils.getPuTongDiaLog(activity,takeCommonDialog);
                    takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                        @Override
                        public void quxiao() {
                            takeCommonDialog.dismiss();
                        }

                        @Override
                        public void queren() {
                            takeCommonDialog.dismiss();
                            Intent intent = new Intent(activity, Bind_Email_Activity1.class);
                            intent.putExtra("title","业务密码（设置）");
                            intent.putExtra("select",SP_String.YEWUMIMA);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                    });
                }

            }
        });

        warehouse_item_touming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarehouseBean.DataBean.ListBean listBean = warehouselist.get(position);
                listBean.setFlag(false);
                warehouselist.set(position,listBean);
                notifyDataSetChanged();
            }
        });




        return convertView;
    }

}
