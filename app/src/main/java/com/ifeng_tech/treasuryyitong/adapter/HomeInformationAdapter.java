package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.InformationBean;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

class HomeInformationAdapter extends BaseAdapter {
    Context context;
    List<InformationBean> informationlist;

    public HomeInformationAdapter(Context context, List<InformationBean> informationlist) {
        this.context = context;
        this.informationlist = informationlist;
    }

    @Override
    public int getCount() {
        return informationlist.size();
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
            convertView = View.inflate(context, R.layout.home_zixun_buju, null);
        }

        TextView home_zixun_title = convertView.findViewById(R.id.home_zixun_title);
        TextView home_zixun_text = convertView.findViewById(R.id.home_zixun_text);
        TextView home_zixun_time = convertView.findViewById(R.id.home_zixun_time);

        home_zixun_title.setText(informationlist.get(position).getTitle());
        home_zixun_text.setText(informationlist.get(position).getText());
        home_zixun_time.setText(informationlist.get(position).getTime());

        return convertView;
    }
}
