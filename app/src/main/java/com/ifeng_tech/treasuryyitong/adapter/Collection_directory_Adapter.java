package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.cangpin.Collection_directory_Fragment_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/24.
 * 948592563
 */

public class Collection_directory_Adapter extends BaseAdapter{
    Context context;
    List<Collection_directory_Fragment_Bean.DataBean.ListBean> topList;

    public Collection_directory_Adapter(Context context,  List<Collection_directory_Fragment_Bean.DataBean.ListBean> topList) {
        this.context = context;
        this.topList = topList;
    }

    @Override
    public int getCount() {
        return topList.size();
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

        convertView = View.inflate(context, R.layout.collection_mulu_item, null);
        ImageView collection_mulu_item_img = convertView.findViewById(R.id.collection_mulu_item_img);
        TextView collection_mulu_item_name = convertView.findViewById(R.id.collection_mulu_item_name);
        TextView collection_mulu_item_cword = convertView.findViewById(R.id.collection_mulu_item_cword);

        collection_mulu_item_img.setTag(R.id.collection_mulu_item_img,topList.get(position).getCommodityLink()+"");

        String tag_first=collection_mulu_item_img.getTag(R.id.collection_mulu_item_img).toString();

        if(topList.get(position).getCommodityLink()==null){
            collection_mulu_item_img.setImageResource(R.drawable.img_erroy);
        }else{
            Glide.with(context).load(tag_first).error(R.drawable.img_erroy).into(collection_mulu_item_img);
        }

        if(topList.get(position).getCommodityName().length()>18){
            String name = topList.get(position).getCommodityName().substring(0, 18);
            collection_mulu_item_name.setText(name+"...");
        }else{
            collection_mulu_item_name.setText(topList.get(position).getCommodityName());
        }

        collection_mulu_item_cword.setText(""+topList.get(position).getCommodityCode());

//        ViewHolde viewHolde;
//        if(convertView==null){
//            convertView = View.inflate(context, R.layout.collection_mulu_item, null);
//             viewHolde = new ViewHolde();
//
//            viewHolde. collection_mulu_item_img = convertView.findViewById(collection_mulu_item_img);
//            viewHolde. collection_mulu_item_name = convertView.findViewById(collection_mulu_item_name);
//            viewHolde. collection_mulu_item_cword = convertView.findViewById(collection_mulu_item_cword);
//            viewHolde.collection_mulu_item_img.setTag(collection_mulu_item_img,topList.get(position).getCommodityLink()+"");
//
//            convertView.setTag(viewHolde);
//        }else{
//            viewHolde = (ViewHolde) convertView.getTag();
//            viewHolde.collection_mulu_item_img.setTag(collection_mulu_item_img,topList.get(position).getCommodityLink()+"");
//        }
//
//        /**
//         * 为什么图片会产生错位 闪烁?????图片是异步加载+复用的优化的原因
//         *
//         * 1.imageView要跟自己的url路径匹配起来
//         */
//        viewHolde.collection_mulu_item_img.setTag(topList.get(position).getCommodityLink());
//
//        //3.设置默认显示的图片
//        viewHolde.collection_mulu_item_img.setImageResource(R.drawable.img_erroy);
//        String tag_first=viewHolde.collection_mulu_item_img.getTag(collection_mulu_item_img).toString();
//        if (viewHolde.collection_mulu_item_img.getTag().equals(topList.get(position).getCommodityLink())){
//            Glide.with(context).load(tag_first).error(R.drawable.img_erroy).into(viewHolde.collection_mulu_item_img);
//        }
////
////
////        if(topList.get(position).getCommodityLink()==null){
////            viewHolde.collection_mulu_item_img.setImageResource(R.drawable.img_erroy);
////        }else{
////            Glide.with(context).load(tag_first).error(R.drawable.img_erroy).into(viewHolde.collection_mulu_item_img);
////        }
//
//        if(topList.get(position).getCommodityName().length()>18){
//            String name = topList.get(position).getCommodityName().substring(0, 18);
//            viewHolde.collection_mulu_item_name.setText(name+"...");
//        }else{
//            viewHolde.collection_mulu_item_name.setText(topList.get(position).getCommodityName());
//        }
//
//        viewHolde.collection_mulu_item_cword.setText(""+topList.get(position).getCommodityCode());

        return convertView;
    }

     class ViewHolde{
         ImageView collection_mulu_item_img;
         TextView collection_mulu_item_name;
         TextView collection_mulu_item_cword;
     }
}
