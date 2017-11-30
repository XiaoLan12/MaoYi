package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyRefundOrderAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.DataHelper;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.bean.json.RefundFootBean;
import com.yizhisha.maoyi.bean.json.RefundListBean;
import com.yizhisha.maoyi.bean.json.ToEvalutionFootBean;
import com.yizhisha.maoyi.ui.me.contract.ReFundOrderContract;
import com.yizhisha.maoyi.ui.me.presenter.ReFundOrderPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ReFundOrderActivity extends BaseActivity<ReFundOrderPresenter> implements SwipeRefreshLayout.OnRefreshListener,
        ReFundOrderContract.View{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyRefundOrderAdapter mAdapter;
    private ArrayList<Object> dataList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_re_fund_order;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        initAdapter();
        load(true);

    }
    private void load(boolean isShowLoad){
        mPresenter.loadRefund(AppConstant.UID,isShowLoad);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new MyRefundOrderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.contact_the_merchant_tv:
                        if(dataList.get(position) instanceof RefundFootBean) {
                            RefundFootBean footBean = (RefundFootBean) dataList.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putString("REFUNDNO", footBean.getRefundno());
                            startActivity(ReFundOrderDetailsActivity.class,bundle);
                        }
                        break;
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        load(false);
    }

    @Override
    public void loadRefundSuccess(List<RefundListBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList= DataHelper.getDataRefund(data);
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
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }
    @Override
    public void loadFail(String msg) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(true);
            }
        });
    }
}
