package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderDetailsAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.MyOrderGoodsBean;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MyOrderDetailsActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Bind(R.id.consigneeName_tv)
    TextView mTvConsignee;
    @Bind(R.id.consigneePhone_tv)
    TextView mTvConsigneePhone;
    @Bind(R.id.shippingaddress_tv)
    TextView mTvShipAddress;

    @Bind(R.id.orderno_tv)
    TextView mTvOrderNo;
    @Bind(R.id.ordertime_tv)
    TextView mTvOrderTime;
    @Bind(R.id.payway_tv)
    TextView mTvPayWay;
    @Bind(R.id.paytime_tv)
    TextView mTvPayTime;

    @Bind(R.id.tradeltotal_tv)
    TextView mTvTradelTotal;
    @Bind(R.id.tradelpaymentpay_tv)
    TextView mTvTradelPaymentPay;
    @Bind(R.id.contactSeller_ll)
    LinearLayout mLlContactSeller;
    @Bind(R.id.confirm_goods_tv)
    TextView mTvConfirmGoods;
    @Bind(R.id.refound_ll)
    LinearLayout mLlRefound;
    @Bind(R.id.againbuy_tv)
    TextView mTvAgeinBuy;

    @Bind(R.id.orderInfo_tv)
    TextView mTvOrderInfo;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    private MyOrderDetailsAdapter mAdapter;
    private List<MyOrderGoodsBean> dataList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_details;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(MyOrderDetailsActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        mLoadingView.loadSuccess();
        data();
        initAdapter();
    }
    private void initAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter=new MyOrderDetailsAdapter(this,dataList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void data(){
        for(int i=0;i<2;i++){
            MyOrderGoodsBean goodsBean=new MyOrderGoodsBean();
            dataList.add(goodsBean);
        }
    }
}
