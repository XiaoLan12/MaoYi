package com.yizhisha.maoyi.common.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.GoodsScreesBean;
import com.yizhisha.maoyi.bean.json.GoodsScreesContentBean;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.bean.json.OrderHeadBean;
import com.yizhisha.maoyi.bean.json.SortedBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.http.PUT;

/**
 * Created by 小熊 on 2018/2/9.
 */

public class GoodsScressPopuwindow extends PopupWindow{
    private View mContentView;
    private Context mActivity;
    private TagFlowLayout flowlayout1,flowlayout2,flowlayout3;
    private EditText lowestPriceEt,highestPriceEt;
    private TextView searchTv;
    private  LayoutInflater mInflater;
    private List<SortedBean> mTabVal1;
    private List<SortedBean> mTabVal2;
    private List<SortedBean> mTabVal3;
    public interface OnSearchOnClick{
        void onSearchLisenter();
    }
    public GoodsScressPopuwindow(Context activity){
        mActivity=activity;
        // 获得屏幕的宽度和高度
        WindowManager wm = (WindowManager) activity
                .getSystemService(Context.WINDOW_SERVICE);
        int mScreenWidth = wm.getDefaultDisplay().getWidth();
        setWidth((int) (mScreenWidth*0.6));
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setAnimationStyle(R.style.showPopupAnimation);
        mContentView = LayoutInflater.from(activity).inflate(R.layout.popu_goods_scress, null);
        setContentView(mContentView);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setOutsideTouchable(false);
        setTouchable(true);

          mInflater = LayoutInflater.from(activity);
        flowlayout1= (TagFlowLayout ) mContentView.findViewById(R.id.id_flowlayout1);
        flowlayout2= (TagFlowLayout ) mContentView.findViewById(R.id.id_flowlayout2);
        flowlayout3= (TagFlowLayout ) mContentView.findViewById(R.id.id_flowlayout3);
        lowestPriceEt=mContentView.findViewById(R.id.lowest_price_et);
        highestPriceEt=mContentView.findViewById(R.id.highest_price_et);
        searchTv=mContentView.findViewById(R.id.tv_search);
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchOnClick.onSearchLisenter();
            }
        });


    }
    public void serData1(List<SortedListBean.SortedsBean> sortedsBeanList) {
        mTabVal1=new ArrayList<>();
        mTabVal2=new ArrayList<>();
        mTabVal3=new ArrayList<>();
        List<SortedListBean.SortedsBean> list=new ArrayList<>();
        list.addAll(sortedsBeanList);
        int lenght=list.size();
        for(int i=0;i<lenght;i++){
            if(list.get(i).getName().equals("上装")){
                mTabVal1.addAll(list.get(i).getCat());
            }else if(list.get(i).getName().equals("裙装")){
                mTabVal2.addAll(list.get(i).getCat());
            }else if(list.get(i).getName().equals("裤装")){
                mTabVal3.addAll(list.get(i).getCat());
            }

        }
        flowlayout1.setAdapter(new TagAdapter<SortedBean>(mTabVal1)
        {
            @Override
            public View getView(FlowLayout parent, int position, SortedBean s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        flowlayout1, false);
                tv.setText(s.getCat_name());
                return tv;
            }
        });
        flowlayout2.setAdapter(new TagAdapter<SortedBean>(mTabVal2)
        {
            @Override
            public View getView(FlowLayout parent, int position, SortedBean s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        flowlayout2, false);
                tv.setText(s.getCat_name());
                return tv;
            }
        });
        flowlayout3.setAdapter(new TagAdapter<SortedBean>(mTabVal3)
        {
            @Override
            public View getView(FlowLayout parent, int position, SortedBean s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        flowlayout3, false);
                tv.setText(s.getCat_name());
                return tv;
            }
        });
    }

    public List<Integer> getSelectData(){
        List<Integer> data=new ArrayList<>();
        Set<Integer> list1=flowlayout1.getSelectedList();
        for(int i:list1){
            data.add(mTabVal1.get(i).getCat_id());
        }
        Set<Integer> list2=flowlayout2.getSelectedList();
        for(int i:list2){
            data.add(mTabVal2.get(i).getCat_id());
        }
        Set<Integer> list3=flowlayout3.getSelectedList();
        for(int i:list3){
            data.add(mTabVal3.get(i).getCat_id());
        }
        return data;
    }
    public String getPrice(){
        String value="";
        String price1=lowestPriceEt.getText().toString().trim();
        String price2=highestPriceEt.getText().toString().trim();
        if(!price1.equals("")||!price2.equals("")){
            if(price1.equals("")){
                price1="0";
            }
            if(price2.equals("")){
                price2="0";
            }
            StringBuffer str=new StringBuffer();
            str.append(price1).append(",").append(price2);
            value=str.toString();
        }
        return value;
    }
    OnSearchOnClick onSearchOnClick;
    public void setOnSearchOnClick(OnSearchOnClick onSearchOnClick){
        this.onSearchOnClick=onSearchOnClick;
    }


}
