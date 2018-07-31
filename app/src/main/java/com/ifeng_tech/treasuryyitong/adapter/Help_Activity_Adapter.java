package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.bangzu.Help_One_Bean;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.MyExpandableListView;

import java.util.List;

/**
 * Created by zzt on 2018/7/30.
 */

public class Help_Activity_Adapter extends BaseExpandableListAdapter {

    Context context;
    List<Help_One_Bean.DataBean.ListBean> list;
    MyExpandableListView help_MyExpandableListView;
    public Help_Activity_Adapter(Context context, List<Help_One_Bean.DataBean.ListBean> list,MyExpandableListView help_MyExpandableListView) {
        this.context = context;
        this.list = list;
        this.help_MyExpandableListView=help_MyExpandableListView;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    boolean flag=true;
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.help_one_item,null);
        }
        final TextView help_one_item_text = convertView.findViewById(R.id.help_one_item_text);
        help_one_item_text.setText(list.get(groupPosition).getName()+"");

        help_one_item_text.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {

                if(help_MyExpandableListView.isGroupExpanded(groupPosition)){  // 判断是否展开
                    help_MyExpandableListView.collapseGroup(groupPosition);  // 关闭分组
                }else{
                    help_Activity_Adapter_JieKou.one_chuan(groupPosition);
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.help_two_item,null);
        }
        TextView help_two_item_text = convertView.findViewById(R.id.help_two_item_text);
        help_two_item_text.setText(list.get(groupPosition).getList().get(childPosition).getTitle()+"");
        if(list.get(groupPosition).getList().get(childPosition).isFlag()){
            help_two_item_text.setTextColor(context.getResources().getColor(R.color.zhuse));
        }else{
            help_two_item_text.setTextColor(context.getResources().getColor(R.color.color_666666));
        }
        help_two_item_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getList()!=null){
                        for (int j=0;j<list.get(i).getList().size();j++){
                            list.get(i).getList().get(j).setFlag(false);
                            list.get(i).getList().set(j,list.get(i).getList().get(j));
                        }
                        list.set(i,list.get(i));
                    }else{
                        break;
                    }

                }
                list.get(groupPosition).getList().get(childPosition).setFlag(true);
                list.get(groupPosition).getList().set(childPosition,list.get(groupPosition).getList().get(childPosition));
                list.set(groupPosition,list.get(groupPosition));
                notifyDataSetChanged();
                help_Activity_Adapter_JieKou.two_chuan(groupPosition,childPosition);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public interface Help_Activity_Adapter_JieKou{
        void one_chuan(int position);
        void two_chuan(int groupPosition, int childPosition);
    }
    Help_Activity_Adapter_JieKou help_Activity_Adapter_JieKou;

    public void setHelp_Activity_Adapter_JieKou(Help_Activity_Adapter_JieKou help_Activity_Adapter_JieKou) {
        this.help_Activity_Adapter_JieKou = help_Activity_Adapter_JieKou;
    }
}
