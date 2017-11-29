package com.yizhisha.maoyi.ui.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderAdapter;
import com.yizhisha.maoyi.adapter.ToEvalutionAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.DataHelper;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.ToEvalutionFootBean;
import com.yizhisha.maoyi.ui.me.activity.AddCommentActivity;
import com.yizhisha.maoyi.ui.me.activity.ApplyRefundActivity;
import com.yizhisha.maoyi.ui.me.activity.MyOrderDetailsActivity;
import com.yizhisha.maoyi.ui.me.contract.MyOrderContract;
import com.yizhisha.maoyi.ui.me.presenter.MyOrderPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class ToEvaluationFragment extends BaseFragment<MyOrderPresenter> implements MyOrderContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ToEvalutionAdapter mAdapter;
    private ArrayList<Object> dataList=new ArrayList<>();
    public static ToEvaluationFragment getInstance() {
        ToEvaluationFragment sf = new ToEvaluationFragment();
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myrating;
    }
    @Override
    protected void initView() {
        initAdapter();
        if(mAdapter.getData().size()<=0){
            load(true);
        }
    }
    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("status", String.valueOf(4));

        mPresenter.loadOrder(map,isShowLoad);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new ToEvalutionAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.add_comment_tv:
                        if(dataList.get(position) instanceof ToEvalutionFootBean) {
                            ToEvalutionFootBean footBean= (ToEvalutionFootBean) dataList.get(position);
                            Bundle commentbundle = new Bundle();
                            commentbundle.putInt("TYPE",1);
                            commentbundle.putInt("ORDERID",footBean.getOrderId());
                            startActivity(AddCommentActivity.class,commentbundle);
                        }
                        break;
                }
            }
        });
        mAdapter.setOnItemTypeClickListener(new ToEvalutionAdapter.OnItemTypeClickListener() {
            @Override
            public void onItemClick(View view, int type, int position) {
                if(type==3){

                }
            }
        });


    }
    @Override
    public void onRefresh() {
        load(false);
    }

    @Override
    public void loadOrderSuccess(List<MyOrderListBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList= DataHelper.getDataEvalution(data);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void sureGoodsSuuccess(String msg) {

    }

    @Override
    public void cancleOrder(String msg) {

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
    public void loadFail(int code, String msg) {
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
