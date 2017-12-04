package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderDetailsAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.PayReqBean;
import com.yizhisha.maoyi.bean.json.WeChatPayStateBean;
import com.yizhisha.maoyi.ui.me.contract.OrderDetailsContract;
import com.yizhisha.maoyi.ui.me.presenter.OrderDetailsPresenter;
import com.yizhisha.maoyi.utils.DateUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyOrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsContract.View {
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

    @Bind(R.id.orderInfo_tv)
    TextView mTvOrderInfo;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.distributionway_orderdetail_tv)
    TextView distributionwayOrderdetailTv;
    @Bind(R.id.distributiontime_orderdetail)
    TextView distributiontimeOrderdetail;

    @Bind(R.id.refunds_ll)
    LinearLayout refundsTv;
    @Bind(R.id.confirm_goods_ll)
    LinearLayout confirmGoodsTv;
    @Bind(R.id.againbuy_ll)
    LinearLayout againbuyTv;
    @Bind(R.id.immediate_payment_ll)
    LinearLayout immediatePaymentTv;

    private MyOrderDetailsAdapter mAdapter;
    private String orderNo = "";
    private MyOrderListBean orderList;
    private List<MyOrderListBean.Goods> dataList = new ArrayList<>();

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
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderNo = bundle.getString("ORDERNO");
        }
        initAdapter();
        load(orderNo, true);
    }

    private void load(String orderNo, boolean isShowLoad) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(AppConstant.UID));
        map.put("orderno", orderNo);
        mPresenter.loadOrderDetails(map, false);
    }


    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new MyOrderDetailsAdapter(this, dataList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void init() {
        if (orderList == null) {
            return;
        }
        String state[] = new String[]{"您还未支付该订单", "买家已付款,待发货", "商家已发货", "交易成功", "已评价"};
        mTvOrderInfo.setText(state[orderList.getStatus()]);

        mTvOrderNo.setText(orderList.getOrderno());

        mTvConsignee.setText(orderList.getLinkman());
        mTvConsigneePhone.setText(orderList.getMobile());
        mTvShipAddress.setText(orderList.getAddress());
        mTvOrderTime.setText(DateUtil.getDateToString1(orderList.getAddtime() * 1000));
        mTvPayTime.setText(DateUtil.getDateToString1(orderList.getPaytime() * 1000));
        distributiontimeOrderdetail.setText(DateUtil.getDateToString1(orderList.getShiptime() * 1000));
       /* int payment=orderList.getPayment();
        if(payment==2){
            mTvPayWay.setText("支付宝");
        }else if(payment==3){
            mTvPayWay.setText("到付");
        }else if(payment==5){
            mTvPayWay.setText("微信支付");
        }*/
        int express_type = orderList.getExpress_id();
        if (express_type == 1) {
            mTvTradelPaymentPay.setText("物流发货");
        } else if (express_type == 2) {
            mTvTradelPaymentPay.setText("快递发货");
        } else if (express_type == 3) {
            mTvTradelPaymentPay.setText(" 朗通快递");
        }
        mTvTradelTotal.setText(orderList.getTotalprice() + "");
       /* if(payment==3){
            mTvPayTime.setText("货到付款");
        } else if(order.getPaytime()==0){
            mTvPayTime.setText("未支付");
        }else {
            mTvPayTime.setText(DateUtil.getDateToString1(order.getPaytime()*1000));
        }
        if(order.getShiptime()==0){
            mTvDistributionTime.setText("未支付");
        }else {
            mTvDistributionTime.setText(DateUtil.getDateToString1(order.getShiptime()*1000));
        }

        switchState(order.getStatus(),order.getPayment());*/
       switchState(orderList.getStatus());
    }

    @Override
    public void loadoSuccess(List<MyOrderListBean> data) {
        dataList.clear();
        orderList = data.get(0);
        init();
        dataList = orderList.getGoods();
        mAdapter.setNewData(dataList);
    }

    @Override
    public void changePayWaySuccess(String info) {

    }

    @Override
    public void sureGoodsSuuccess(String msg) {

    }

    @Override
    public void cancleOrder(String msg) {

    }

    @Override
    public void weChatPay(PayReqBean bean) {

    }

    @Override
    public void loadWeChatPayState(WeChatPayStateBean bean) {

    }

    @Override
    public void showLoading() {
        mLoadingView.load();
    }

    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void loadFail(int code, String msg) {

    }

    @Override
    public void loadWeChatPayStateFail(String msg) {

    }

    /**
     * 根据交易状态，切换布局显示
     *
     * @param paystate 0 未付款
     *                 1 待发货
     *                 2 待收货
     *                 3 已收货
     */
    private void switchState(int paystate){
        switch (paystate){
            case 0:
                refundsTv.setVisibility(View.GONE);
                confirmGoodsTv.setVisibility(View.GONE);
                againbuyTv.setVisibility(View.GONE);
                immediatePaymentTv.setVisibility(View.VISIBLE);
                break;
            case 1:
                refundsTv.setVisibility(View.VISIBLE);
                confirmGoodsTv.setVisibility(View.GONE);
                againbuyTv.setVisibility(View.GONE);
                immediatePaymentTv.setVisibility(View.GONE);
                break;
            case 2:
                refundsTv.setVisibility(View.VISIBLE);
                confirmGoodsTv.setVisibility(View.VISIBLE);
                againbuyTv.setVisibility(View.GONE);
                immediatePaymentTv.setVisibility(View.GONE);
                break;
            case 3:
                refundsTv.setVisibility(View.VISIBLE);
                confirmGoodsTv.setVisibility(View.GONE);
                againbuyTv.setVisibility(View.VISIBLE);
                immediatePaymentTv.setVisibility(View.GONE);
                break;
            case 4:
                refundsTv.setVisibility(View.GONE);
                confirmGoodsTv.setVisibility(View.GONE);
                againbuyTv.setVisibility(View.VISIBLE);
                immediatePaymentTv.setVisibility(View.GONE);
                break;
        }
    }
}
