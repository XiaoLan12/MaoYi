package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.TypeContentBean;
import com.yizhisha.maoyi.ui.home.activity.SpecialDetailActivity;
import com.yizhisha.maoyi.ui.home.activity.StudioShopActivity;
import com.yizhisha.maoyi.ui.type.avtivity.TypeShopListActivity;
import com.yizhisha.maoyi.ui.type.avtivity.TypeStudioListActivity;
import com.yizhisha.maoyi.widget.MyGridView;

import java.util.List;

/**
 * Created by 小蓝 on 2018/3/20.
 */

public class TypeContentAdapter extends BaseAdapter{
    private Context mContext;
    private List<TypeContentBean> dataLists;
    public TypeContentAdapter(Context context,List<TypeContentBean> list){
        this.mContext=context;
        this.dataLists=list;
    }

    @Override
    public int getCount() {
        return dataLists.size();
    }

    @Override
    public Object getItem(int i) {
        return dataLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HodeView hodeView=null;
        if(convertView==null){
            hodeView=new HodeView();
            convertView = View.inflate(mContext, R.layout.item_type_content, null);
            hodeView.gridView= (MyGridView) convertView.findViewById(R.id.mygridview);
            hodeView.top=(TextView)convertView.findViewById(R.id.top);
            convertView.setTag(hodeView);
        }else{
            hodeView=(HodeView)convertView.getTag();
        }
        final TypeContentItemAdapter adapter = new TypeContentItemAdapter(mContext, dataLists.get(position).getContentBenaList());
        final List<TypeContentBean.ContentBena> list=dataLists.get(position).getContentBenaList();
        hodeView.gridView.setAdapter(adapter);
        hodeView.top.setText(dataLists.get(position).getTitle());
        hodeView.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                // TODO Auto-generated method stub
                if(list.get(i).getType()==3){
                    Intent intent = new Intent(mContext, TypeStudioListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("wid", list.get(i).getCid());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext, TypeShopListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cid", list.get(i).getCid() + "");
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            }
        });
        return convertView;
    }
    class HodeView {
        public MyGridView gridView;
        public TextView top;
    }
}
