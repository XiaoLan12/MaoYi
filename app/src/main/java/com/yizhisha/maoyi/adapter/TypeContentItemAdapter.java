package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.TypeContentBean;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.MyGridView;

import java.util.List;

/**
 * Created by 小蓝 on 2018/3/20.
 */

public class TypeContentItemAdapter extends BaseAdapter{
    private List<TypeContentBean.ContentBena> dataList;
    private Context mContext;
    public TypeContentItemAdapter(Context context,List<TypeContentBean.ContentBena> list){
        this.mContext=context;
        this.dataList=list;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HodeView hodeView=null;
        if(convertView==null){
            hodeView=new HodeView();
            convertView = View.inflate(mContext, R.layout.item_classify_detail, null);
            hodeView.icon= (ImageView) convertView.findViewById(R.id.ivAvatar);
            hodeView.titel=(TextView)convertView.findViewById(R.id.tvCity);
            convertView.setTag(hodeView);
        }else{
            hodeView=(HodeView)convertView.getTag();
        }
            hodeView.titel.setText(dataList.get(position).getTitle());
            if(dataList.get(position).getType()==3){
                GlideUtil.getInstance().LoadContextBitmap(mContext,dataList.get(position).getImgsrc(),hodeView.icon,GlideUtil.LOAD_BITMAP);
            }else{
                hodeView.icon.setImageResource(dataList.get(position).getUrl());
            }

        return convertView;
    }
    class HodeView {
        public ImageView icon;
        public TextView titel;
    }
}
