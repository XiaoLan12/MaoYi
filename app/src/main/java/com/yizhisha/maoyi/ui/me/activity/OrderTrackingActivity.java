package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderDetailsAdapter;
import com.yizhisha.maoyi.adapter.OrderInfoAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.OrderInfoBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.ui.me.contract.OrderTrackContract;
import com.yizhisha.maoyi.ui.me.presenter.OrderTrackPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class OrderTrackingActivity extends BaseActivity<OrderTrackPresenter> implements OrderTrackContract.View{
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private OrderInfoAdapter mAdapter;
    private List<OrderInfoBean> dataList=new ArrayList<>();
    private String refundNo = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_tracking;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            refundNo = bundle.getString("REFUNDNO", "");
        }
        initAdapter();
        mPresenter.loadRefundExpressDetail(refundNo);
    }
    private void initAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter=new OrderInfoAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void data(){
        dataList.add(new OrderInfoBean("2016-09-18 08:33:50","您的订单开始处理"));
        dataList.add(new OrderInfoBean("2016-09-18 08:40:23","您的订单待配货"));
        dataList.add(new OrderInfoBean("2016-09-18 08:51:33","您的包裹已出库"));
        dataList.add(new OrderInfoBean("2016-09-18 21:12:53","【深圳市龙华函件中心】已收寄"));
        dataList.add(new OrderInfoBean("2016-09-18 17:44:20","到达【深圳】"));
        dataList.add(new OrderInfoBean("2016-09-18 21:26:51","离开【深圳市龙华函件中心】，下一站【深圳市】"));
        dataList.add(new OrderInfoBean("2016-09-18 23:18:21","到达【深圳市处理中心】"));
        dataList.add(new OrderInfoBean("2016-09-19 01:14:30","离开【深圳市处理中心】，下一站【广州市】"));
        dataList.add(new OrderInfoBean("2016-09-19 04:42:11","到达【广东省广州邮件处理中心】"));
    }

    @Override
    public void loadRefundExpressSuccess(RefundExpressBean data) {
        dataList.clear();
        List<RefundExpressBean.Result> result=data.getResult();
        if(result.size()==0){
           return;
        }
        for(RefundExpressBean.Result result1:result){
            OrderInfoBean orderInfoBean=new OrderInfoBean(result1.getStatus(),result1.getTime());
            dataList.add(orderInfoBean);
        }
        mAdapter.setNewData(dataList);
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadFail(String msg) {

    }
}
