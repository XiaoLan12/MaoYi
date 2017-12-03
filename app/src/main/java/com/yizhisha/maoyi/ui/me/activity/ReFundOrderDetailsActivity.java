package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.RefundInfoAdapter;
import com.yizhisha.maoyi.adapter.RefundOrderDetailsAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;
import com.yizhisha.maoyi.ui.me.contract.ReFundOrderDetailsContract;
import com.yizhisha.maoyi.ui.me.presenter.ReFundOrderDetailsPresenter;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReFundOrderDetailsActivity extends BaseActivity<ReFundOrderDetailsPresenter> implements ReFundOrderDetailsContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.recyclerview1)
    RecyclerView mRecyclerView1;
    @Bind(R.id.orderNumber_tv)
    TextView orderNumberTv;
    @Bind(R.id.refundNumber_tv)
    TextView refundNumberTv;
    @Bind(R.id.linearlayout)
    LinearLayout linearlayout;
    @Bind(R.id.pric_tv)
    TextView pricTv;
    @Bind(R.id.reason_tv)
    TextView reasonTv;
    @Bind(R.id.look_express_tv)
    TextView lookExpressTv;
    @Bind(R.id.refund_tv)
    TextView refundTv;
    @Bind(R.id.cacle_refund_tv)
    TextView cacleRefundTv;


    private RefundOrderDetailsAdapter mAdapter;
    private List<RefundDetailBean.Goods> dataList = new ArrayList<>();
    private RefundDetailBean.Refund order;
    private RefundInfoAdapter mAdapter1;
    private List<RefundDetailBean.Log> dataList1 = new ArrayList<>();
    private String refundNo = "";

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
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            refundNo = bundle.getString("REFUNDNO", "");
        }
        initAdapter();
        loadRefundDetail();
    }

    //加载详情
    private void loadRefundDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(AppConstant.UID));
        map.put("refundno", refundNo);
        mPresenter.loadRefundDetail(map);
    }
    //撤销退款
    private void refundDel(int id) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(AppConstant.UID));
        map.put("id", String.valueOf(id));
        mPresenter.refundDel(map);
    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new RefundOrderDetailsAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView1.setNestedScrollingEnabled(false);
        mAdapter1 = new RefundInfoAdapter(dataList1);
        mRecyclerView1.setAdapter(mAdapter1);

    }

    @Override
    public void loadRefundDetailSuccess(RefundDetailBean data) {
        dataList.clear();
        dataList1.clear();
        dataList = data.getGoods();
        dataList1 = data.getLog();
        order = data.getRefund();
        if (order != null) {
            orderNumberTv.setText(order.getOrderno());
            refundNumberTv.setText(order.getRefundno());
            pricTv.setText("￥:" + order.getPic());
            reasonTv.setText(order.getReason());
            if(order.getType()==1){
                switch (order.getRefundstatus()){
                    case 1:
                        cacleRefundTv.setVisibility(View.VISIBLE);
                        break;
                }
            }else if(order.getType()==2){
                switch (order.getRefundstatus()){
                    case 1:
                        cacleRefundTv.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        refundTv.setVisibility(View.VISIBLE);
                        cacleRefundTv.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        lookExpressTv.setVisibility(View.VISIBLE);
                        break;

                }
            }
        }
        mAdapter1.setNewData(dataList1);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void refundDel(String data) {
        ToastUtil.showShortToast(data);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
    @OnClick({R.id.look_express_tv,R.id.cacle_refund_tv,R.id.refund_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.look_express_tv:
                Bundle bundle = new Bundle();
                bundle.putString("ORDERNO", order.getRefundno());
                bundle.putInt("TYPE",2);
                startActivity(OrderTrackingActivity.class,bundle);
                break;
            case R.id.cacle_refund_tv:
                refundDel(order.getId());
                break;
            case R.id.refund_tv:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("REFUNDID",order.getId());
                startActivity(RefundGoodActivity.class,bundle1);
                break;
        }
    }
}
