package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.OrderInfoAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.OrderInfoBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.ui.me.contract.OrderTrackContract;
import com.yizhisha.maoyi.ui.me.presenter.OrderTrackPresenter;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderTrackingActivity extends BaseActivity<OrderTrackPresenter> implements OrderTrackContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.courier_company_tv)
    TextView courierCompanyTv;
    @Bind(R.id.express_number_tv)
    TextView expressNumberTv;
    private OrderInfoAdapter mAdapter;
    private List<OrderInfoBean> dataList = new ArrayList<>();
    private String refundNo = "";
    private int mType;

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
            mType = bundle.getInt("TYPE", 1);
            refundNo = bundle.getString("ORDERNO", "");
        }
        initAdapter();
        if (mType == 1) {
            mPresenter.loadExpressDetail(refundNo);

        } else {
            mPresenter.loadRefundExpressDetail(refundNo);
        }

    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new OrderInfoAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void loadRefundExpressSuccess(RefundExpressBean data) {
        courierCompanyTv.setText(data.getExpress().getExpName());
        expressNumberTv.setText(data.getExpress_no());
        dataList.clear();
        List<RefundExpressBean.Result> result = data.getResult();
        if (result.size() == 0) {
            return;
        }
        for (RefundExpressBean.Result result1 : result) {
            OrderInfoBean orderInfoBean = new OrderInfoBean(result1.getStatus(), result1.getTime());
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
        ToastUtil.showShortToast(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
