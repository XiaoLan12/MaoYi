package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.ui.type.TypeFragment;
import com.yizhisha.maoyi.utils.DensityUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小蓝 on 2018/3/20.
 */

public class TypeTabAdapter  extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> group = new ArrayList<>();
    private List<List<String>> child = new ArrayList<>();

    public TypeTabAdapter(Context context,List<String> group, List<List<String>> child){
        this.mContext=context;
        this.group=group;
        this.child=child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tabmenu, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.titel = (TextView) convertView
                    .findViewById(R.id.tab_menu_tv);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.titel.setText(group.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_item, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.title = (TextView) convertView
                    .findViewById(R.id.tab_item_tv);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.title.setText(child.get(groupPosition).get(childPosition));
        if(groupPosition==0){
            if(childPosition==TypeFragment.POSITION){
                childViewHolder.title.setBackground(RescourseUtil.getDrawable(R.drawable.tab_selector));
                childViewHolder.title.setTextColor(RescourseUtil.getColor(R.color.common_color));
            }else{
                childViewHolder.title.setBackgroundColor(Color.WHITE);
                childViewHolder.title.setTextColor(RescourseUtil.getColor(R.color.common_h1));
            }
        }else if(groupPosition==1){
            if(child.get(0).size()==TypeFragment.POSITION){
                childViewHolder.title.setBackground(RescourseUtil.getDrawable(R.drawable.tab_selector));
                childViewHolder.title.setTextColor(RescourseUtil.getColor(R.color.common_color));
            }else{
                childViewHolder.title.setBackgroundColor(Color.WHITE);
                childViewHolder.title.setTextColor(RescourseUtil.getColor(R.color.common_h1));
            }
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder {
        TextView titel;

    }

    class ChildViewHolder {
        TextView title;

    }
}
