package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.OrderInfoAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.OrderInfoBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.ui.me.contract.OrderTrackContract;
import com.yizhisha.maoyi.ui.me.presenter.OrderTrackPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderTrackingActivity extends BaseActivity<OrderTrackPresenter> implements OrderTrackContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
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
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(OrderTrackingActivity.this);
            }
        });
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
    public void loadRefundExpressSuccess(String data) {
        try {
            JSONObject jsonObject=new JSONObject(data);
            String info=jsonObject.getString("info");
            String status=jsonObject.getString("status");
            if(status.equals("y")){
                JSONObject expressObject=jsonObject.getJSONObject("express");
                courierCompanyTv.setText(expressObject.getString("exp_name"));
                expressNumberTv.setText(jsonObject.getString("express_no"));
                Object resultObject=jsonObject.get("result");
                if(resultObject.equals("")){
                    showEmpty("未查询到物流信息");
                }else{
                    dataList.clear();
                    JSONArray  resultArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<resultArray.length();i++){
                        JSONObject object=resultArray.getJSONObject(i);
                        OrderInfoBean orderInfoBean = new OrderInfoBean(object.getString("status"), object.getString("time"));
                        dataList.add(orderInfoBean);
                    }
                    mAdapter.setNewData(dataList);
                }
            }else{
                loadFail(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
    public void showEmpty(String msg) {
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true, R.drawable.icon_error,msg);
    }

    @Override
    public void loadFail(String msg) {
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadError(msg);
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                if (mType == 1) {
                    mPresenter.loadExpressDetail(refundNo);

                } else {
                    mPresenter.loadRefundExpressDetail(refundNo);
                }
            }
        });
    }


}
