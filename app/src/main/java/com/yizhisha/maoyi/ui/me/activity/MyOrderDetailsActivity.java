package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class MyOrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsContract.View{
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
    private String orderNo="";
    private MyOrderListBean orderList;
    private List<MyOrderListBean.Goods> dataList=new ArrayList<>();
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
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            orderNo=bundle.getString("ORDERNO");
        }
        initAdapter();
        load(orderNo,true);
    }
    private void load(String orderNo,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("orderno",orderNo);
        mPresenter.loadOrderDetails(map,false);
    }


    private void initAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter=new MyOrderDetailsAdapter(this,dataList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void init(){
        if(orderList==null){
            return;
        }
        String state[]=new String[]{"您还未支付该订单","买家已付款,待发货","商家已发货","交易成功","已评价"};
        mTvOrderInfo.setText(state[orderList.getStatus()]);

        mTvOrderNo.setText(orderList.getOrderno());
        MyOrderListBean.Address adress=orderList.getAddress();
        if(adress!=null){
            mTvConsignee.setText(adress.getLinkman());
            mTvConsigneePhone.setText(adress.getMobile());
            mTvShipAddress.setText(adress.getAddress());
        }
        mTvOrderTime.setText(DateUtil.getDateToString1(orderList.getAddtime()*1000));
       /* int payment=orderList.getPayment();
        if(payment==2){
            mTvPayWay.setText("支付宝");
        }else if(payment==3){
            mTvPayWay.setText("到付");
        }else if(payment==5){
            mTvPayWay.setText("微信支付");
        }*/
        int express_type=orderList.getExpress_id();
        if(express_type==1){
            mTvTradelPaymentPay.setText("物流发货");
        }else if(express_type==2){
            mTvTradelPaymentPay.setText("快递发货");
        }else if(express_type==3){
            mTvTradelPaymentPay.setText(" 朗通快递");
        }
        mTvTradelTotal.setText(orderList.getTotalprice()+"");
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
    }
    @Override
    public void loadoSuccess(List<MyOrderListBean> data) {
        dataList.clear();
        orderList=data.get(0);
        init();
        dataList=orderList.getGoods();
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
}
