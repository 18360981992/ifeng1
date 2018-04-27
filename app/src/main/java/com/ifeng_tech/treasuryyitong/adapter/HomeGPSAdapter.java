package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

class HomeGPSAdapter extends BaseAdapter {

    Context context;
    List<FirstGpsBean> gpslist;

    public HomeGPSAdapter(Context context, List<FirstGpsBean> gpslist) {
        this.context = context;
        this.gpslist = gpslist;
    }

    @Override
    public int getCount() {
        return gpslist.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context, R.layout.home_daohang_buju, null);
        }

        ImageView imgid =  convertView.findViewById(R.id.home_daohang_img);
        TextView name = convertView.findViewById(R.id.home_daohang_name);
        imgid.setImageResource(gpslist.get(position).getImg());
        name.setText(gpslist.get(position).getName());
        return convertView;
    }
}
