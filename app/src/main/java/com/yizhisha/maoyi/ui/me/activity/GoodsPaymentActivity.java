package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderDetailsAdapter;
import com.yizhisha.maoyi.adapter.OrderSureAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.RadioBean;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.dialog.RadioSelectionDialog;
import com.yizhisha.maoyi.ui.me.contract.GoodsPaymentContract;
import com.yizhisha.maoyi.ui.me.presenter.GoodsPaymentPresenter;
import com.yizhisha.maoyi.utils.DateUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.wxapi.WeChatPayService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;

public class GoodsPaymentActivity extends BaseActivity<GoodsPaymentPresenter> implements GoodsPaymentContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.consigneeName_tv)
    TextView mTvConsignee;
    @Bind(R.id.consigneePhone_tv)
    TextView mTvConsigneePhone;
    @Bind(R.id.shippingaddress_tv)
    TextView mTvShipAddress;

    @Bind(R.id.cost_tv)
    TextView costTv;
    @Bind(R.id.remark_et)
    EditText mRemarkEt;
    @Bind(R.id.distribution_way_tv)
    TextView distributionWayTv;
    @Bind(R.id.zhifubao_cb)
    CheckBox zhifubaoCb;
    @Bind(R.id.weixin_cb)
    CheckBox weixinCb;

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    private MyOrderDetailsAdapter mAdapter;
    private MyOrderListBean orderList;
    private List<MyOrderListBean.Goods> dataList = new ArrayList<>();

    private RadioSelectionDialog radioSelectionDialog;
    private LoadingDialog mLoadingDialog;
    private Subscription subscription;

    private String orderNo = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_payment;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(GoodsPaymentActivity.this);
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
        mTvConsignee.setText(orderList.getLinkman());
        mTvConsigneePhone.setText(orderList.getMobile());
        mTvShipAddress.setText(orderList.getAddress());
        costTv.setText("合计:￥"+orderList.getTotalprice() + "");
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
    public void loadFail(int code,String msg) {
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
    @OnClick({R.id.weixin_rl,R.id.distribution_way_rl ,
            R.id.sureorder_tv
    })
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.sureorder_tv:
                if(!weixinCb.isChecked()){
                    ToastUtil.showShortToast("请选择支付方式");
                    return;
                }
                WeChatPayService weChatPay = new WeChatPayService(this, 0, orderList.getOrderno(), "订单号:"+orderList.getOrderno(), orderList.getTotalprice()+"");
                weChatPay.pay();

                break;
            case R.id.weixin_rl:
                zhifubaoCb.setChecked(false);
                weixinCb.setChecked(true);
                break;
            case R.id.distribution_way_rl:
                if(radioSelectionDialog==null) {
                    List<RadioBean> list = new ArrayList<>();
                    RadioBean radioBean = new RadioBean();
                    radioBean.setItem("快递 免邮");
                    radioBean.setIndex(0);
                    radioBean.setSelected(true);

                    RadioBean radioBean1 = new RadioBean();
                    radioBean1.setItem("物流");
                    radioBean1.setIndex(1);
                    radioBean1.setSelected(false);

                    list.add(radioBean);
                    list.add(radioBean1);
                    radioSelectionDialog = new RadioSelectionDialog(this, "配送方式");
                    radioSelectionDialog.setItem(list);

                    radioSelectionDialog.setOnSelectItemListener(new RadioSelectionDialog.OnSelectItemListener() {
                        @Override
                        public void onSelectItem(RadioBean bean) {
                            distributionWayTv.setText(bean.getItem());
                        }
                    });
                }
                radioSelectionDialog.show();
                break;

        }
    }
}
