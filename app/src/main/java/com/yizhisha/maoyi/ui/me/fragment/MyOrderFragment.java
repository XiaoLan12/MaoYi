package com.yizhisha.maoyi.ui.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.MyOrderFootBean;
import com.yizhisha.maoyi.bean.json.MyOrderGoodsBean;
import com.yizhisha.maoyi.bean.json.MyOrderHeadBean;
import com.yizhisha.maoyi.ui.me.activity.ApplyRefundActivity;
import com.yizhisha.maoyi.ui.me.activity.MyOrderDetailsActivity;
import com.yizhisha.maoyi.ui.me.activity.OrderTrackingActivity;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/25.
 */

public class MyOrderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyOrderAdapter mAdapter;
    private int mType=0;
    private ArrayList<Object> dataList=new ArrayList<>();

    public static MyOrderFragment getInstance(int type) {
        MyOrderFragment sf = new MyOrderFragment();
        sf.mType = type;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myorder;
    }
    @Override
    protected void initView() {
        dataList.addAll(getDataAfterHandle());
        mLoadingView.loadSuccess();
        initAdapter();
        mAdapter.setNewData(dataList);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new MyOrderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mAdapter.setOnItemTypeClickListener(new MyOrderAdapter.OnItemTypeClickListener() {
            @Override
            public void onItemClick(View view, int type, int position) {
                if(type==2) {
                    startActivity(MyOrderDetailsActivity.class);
                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.contact_the_merchant_tv:
                        //startActivity(OrderTrackingActivity.class);
                        startActivity(ApplyRefundActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @return
     */
    public static ArrayList<Object> getDataAfterHandle() {

        ArrayList<Object> dataList = new ArrayList<Object>();

        //遍历每一张大订单
            //大订单支付的金额核定单状态
            MyOrderHeadBean orderHeadBean = new MyOrderHeadBean();
            orderHeadBean.setStatus(1);
            orderHeadBean.setCompany("天知道，你个2");
            orderHeadBean.setPayment(1);
            dataList.add(orderHeadBean);


            List<MyOrderGoodsBean> goodses=new ArrayList<>();
            //遍历每个大订单里面的小订单
            for (int i=0;i<2;i++) {
                MyOrderGoodsBean goodsBean=new MyOrderGoodsBean();
                dataList.add(goodsBean);
            }
            MyOrderFootBean orderFootBean=new MyOrderFootBean();
            orderFootBean.setStatus(1);
            dataList.add(orderFootBean);
        return dataList;
    }
}
