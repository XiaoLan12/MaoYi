package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderDetailsAdapter;
import com.yizhisha.maoyi.adapter.OrderInfoAdapter;
import com.yizhisha.maoyi.adapter.RefundOrderDetailsAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.MyOrderGoodsBean;
import com.yizhisha.maoyi.bean.json.OrderInfoBean;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ReFundOrderDetailsActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.recyclerview1)
    RecyclerView mRecyclerView1;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    private RefundOrderDetailsAdapter mAdapter;
    private List<MyOrderGoodsBean> dataList=new ArrayList<>();

    private OrderInfoAdapter mAdapter1;
    private List<OrderInfoBean> dataList1=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_re_fund_order_details;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(ReFundOrderDetailsActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        mLoadingView.loadSuccess();
        data();
        data1();
        initAdapter();
    }
    private void initAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter=new RefundOrderDetailsAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView1.setNestedScrollingEnabled(false);
        mAdapter1=new OrderInfoAdapter(dataList1);
        mRecyclerView1.setAdapter(mAdapter1);

    }
    private void data(){
            MyOrderGoodsBean goodsBean=new MyOrderGoodsBean();
            dataList.add(goodsBean);
    }
    private void data1(){
        dataList1.add(new OrderInfoBean("2016-09-18 08:33:50","您的订单开始处理"));
        dataList1.add(new OrderInfoBean("2016-09-18 08:40:23","您的订单待配货"));
        dataList1.add(new OrderInfoBean("2016-09-18 08:51:33","您的包裹已出库"));
        dataList1.add(new OrderInfoBean("2016-09-18 21:12:53","【深圳市龙华函件中心】已收寄"));
        dataList1.add(new OrderInfoBean("2016-09-18 17:44:20","到达【深圳】"));
        dataList1.add(new OrderInfoBean("2016-09-18 21:26:51","离开【深圳市龙华函件中心】，下一站【深圳市】"));
        dataList1.add(new OrderInfoBean("2016-09-18 23:18:21","到达【深圳市处理中心】"));
        dataList1.add(new OrderInfoBean("2016-09-19 01:14:30","离开【深圳市处理中心】，下一站【广州市】"));
        dataList1.add(new OrderInfoBean("2016-09-19 04:42:11","到达【广东省广州邮件处理中心】"));
    }
}
