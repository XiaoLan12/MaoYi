package com.yizhisha.maoyi.ui.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderDetailsAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.base.rx.RxBus;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.PayReqBean;
import com.yizhisha.maoyi.bean.json.WeChatPayStateBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.event.WeChatPayEvent;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.ui.home.activity.SureOrderActivity;
import com.yizhisha.maoyi.ui.me.contract.OrderDetailsContract;
import com.yizhisha.maoyi.ui.me.presenter.OrderDetailsPresenter;
import com.yizhisha.maoyi.utils.DateUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.wxapi.WeChatPayService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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

    @Bind(R.id.goodsnum_tv)
    TextView goodsNumTv;
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
    @Bind(R.id.stream_query_tv)
    TextView streamQueryTv;

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

    private Subscription subscription;

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
        event();
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
        String state[] = new String[]{"等待付款", "等待发货", "等待收货", "交易成功", "交易关闭"};
        if(orderList.getStatus()==3){
            streamQueryTv.setVisibility(View.VISIBLE);
            streamQueryTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ORDERNO", orderList.getOrderno());
                    bundle.putInt("TYPE",1);
                    startActivity(OrderTrackingActivity.class,bundle);
                }
            });
        }else{
            streamQueryTv.setVisibility(View.GONE);
        }
        mTvOrderInfo.setText(state[orderList.getStatus()]);

        mTvOrderNo.setText(orderList.getOrderno());

        mTvConsignee.setText(orderList.getLinkman());
        mTvConsigneePhone.setText(orderList.getMobile());
        mTvShipAddress.setText(orderList.getAddress());
        if(orderList.getAddtime()!=0){
            mTvOrderTime.setText(DateUtil.getDateToString1(orderList.getAddtime() * 1000));
        }
        if(orderList.getPaytime()!=0){
            mTvPayTime.setText(DateUtil.getDateToString1(orderList.getPaytime() * 1000));
        }

       /* String express[]=new String[]{"顺丰快递","圆通快递","中通快递","申通快递","韵达快递","京东快递","全峰快递","邮政EMS","安能快递",
                "百世快递","速尔快递","天天快递","邮政包裹"};*/
        goodsNumTv.setText("共计"+orderList.getAmount()+"件商品");
        mTvTradelTotal.setText(orderList.getTotalprice() + "");
       switchState(orderList.getStatus());
    }

    //回调事件，成功调起微信支付后响应该事件
    private void event(){
        subscription= RxBus.$().toObservable(WeChatPayEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatPayEvent>() {
                    @Override
                    public void call(WeChatPayEvent event) {
                        new NormalAlertDialog.Builder(MyOrderDetailsActivity.this)
                                .setTitleText("支付结果")
                                .setContentText("支付成功")
                                .setSingleModel(true)
                                .setSingleText("确认")
                                .setWidth(0.75f)
                                .setHeight(0.33f)
                                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                                    @Override
                                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                        setResult(2);
                                        finish_Activity(MyOrderDetailsActivity.this);
                                        dialog.dismiss();
                                    }
                                }).build().show();
                    }
                });
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
    public void sureGoodsSuuccess(String msg) {
        ToastUtil.showShortToast(msg);
        setResult(2);
        finish_Activity(this);
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
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }
    @Override
    public void loadFail(int code, String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
            return;
        }
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadError(msg);
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(orderNo, true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    @OnClick({R.id.contact_the_merchant_ll,R.id.refunds_ll,R.id.confirm_goods_ll,R.id.againbuy_ll,R.id.immediate_payment_ll})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_the_merchant_ll:
                new NormalAlertDialog.Builder(this)
                        .setContentText("124235")
                        .setContentTextSize(18)
                        .setLeftText("取消")
                        .setRightText("呼叫")
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "234325"));
                                startActivity(phoneIneten);
                                dialog.dismiss();

                            }
                        }).build().show();
                break;
            case R.id.refunds_ll:
                Bundle bundle = new Bundle();
                bundle.putString("ORDERNO", orderList.getOrderno());
                //bundle.putInt("TYPE", orderList.getGoods().get(0).get);
                startActivity(ApplyRefundActivity.class,bundle);
                break;
            case R.id.confirm_goods_ll:
                Map<String,String> body=new HashMap<String, String>();
                body.put("orderno",orderList.getOrderno());
                body.put("uid",String.valueOf(AppConstant.UID));
                mPresenter.sureGoods(body);
                break;
            case R.id.againbuy_ll:
                Bundle commentbundle = new Bundle();
                commentbundle.putInt("TYPE",1);
                commentbundle.putInt("ORDERID",orderList.getId());
                startActivity(AddCommentActivity.class,commentbundle);
                break;
            case R.id.immediate_payment_ll:
                WeChatPayService weChatPay = new WeChatPayService(this, 0, orderList.getOrderno(), dataList.get(0).getTitle(), orderList.getTotalprice()+"");
                weChatPay.pay();
                break;
        }
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
