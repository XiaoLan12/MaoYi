package com.yizhisha.maoyi.ui.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyOrderAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.DataHelper;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.ui.me.activity.ApplyRefundActivity;
import com.yizhisha.maoyi.ui.me.activity.MyOrderDetailsActivity;
import com.yizhisha.maoyi.ui.me.activity.OrderTrackingActivity;
import com.yizhisha.maoyi.ui.me.contract.MyOrderContract;
import com.yizhisha.maoyi.ui.me.presenter.MyOrderPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/25.
 */

public class MyOrderFragment extends BaseFragment<MyOrderPresenter> implements MyOrderContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyOrderAdapter mAdapter;
    private int mType=0;
    private ArrayList<Object> dataList=new ArrayList<>();
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private int currStatus;//当前点击的位置
    private final static int RESULT_CODE=102;
    public static MyOrderFragment getInstance(int type) {
        MyOrderFragment sf = new MyOrderFragment();
        sf.mType = type;
        return sf;

    }
    public int getStatus(){
        return mType;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(isPrepared){
                onRefresh();
            }

        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myorder;
    }
    @Override
    protected void initView() {
        isPrepared=true;
        initAdapter();
        if(mAdapter.getData().size()<=0){
            load(mType,true);
        }

    }
    private void load(int type,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        if(type!=-1){
            map.put("status", String.valueOf(type));
        }

        mPresenter.loadOrder(map,isShowLoad);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new MyOrderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemTypeClickListener(new MyOrderAdapter.OnItemTypeClickListener() {
            @Override
            public void onItemClick(View view, int type, int position) {
                if(type==2){
                    String  orderno;
                    if(dataList.get(position) instanceof MyOrderListBean.Goods) {
                        MyOrderListBean.Goods goods= (MyOrderListBean.Goods) dataList.get(position);
                        orderno=goods.getOrderno();
                        Bundle bundle=new Bundle();
                        bundle.putString("ORDERNO",orderno);
                        startActivityForResult(MyOrderDetailsActivity.class,bundle,RESULT_CODE);
                    }
                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.refunds_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean goods= (OrderFootBean) dataList.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putString("ORDERNO", goods.getOrderno());
                            bundle.putInt("TYPE", goods.getType());
                            startActivity(ApplyRefundActivity.class,bundle);
                        }

                        break;
                    case R.id.order_track_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean goods= (OrderFootBean) dataList.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putString("ORDERNO", goods.getOrderno());
                            bundle.putInt("TYPE",1);
                            startActivity(OrderTrackingActivity.class,bundle);
                        }
                        break;
                    case R.id.cancel_the_order_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            final OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            new NormalAlertDialog.Builder(activity)
                                    .setBoolTitle(false)
                                    .setContentText("确认取消订单？")
                                    .setContentTextSize(18)
                                    .setLeftText("取消")
                                    .setRightText("确认")
                                    .setWidth(0.75f)
                                    .setHeight(0.33f)
                                    .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                                        @Override
                                        public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                            dialog.dismiss();
                                        }
                                        @Override
                                        public void clickRightButton(NormalAlertDialog dialog, View view) {
                                            currStatus=orderFootBean.getStatus();
                                            Map<String, String> bodyMap = new HashMap<>();
                                            bodyMap.put("uid", String.valueOf(AppConstant.UID));
                                            bodyMap.put("orderno", String.valueOf(orderFootBean.getOrderno()));
                                            mPresenter.cancleOrder(bodyMap);
                                            dialog.dismiss();
                                        }
                                    }).build().show();

                        }
                        break;
                    case R.id.confirm_goods_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            Map<String,String> body=new HashMap<String, String>();
                            body.put("orderno",orderFootBean.getOrderno());
                            body.put("uid",String.valueOf(AppConstant.UID));
                            body.put("type","order");
                            mPresenter.sureGoods(body);
                        }
                        break;
                    case R.id.againbuy_tv:
                        break;
                    case R.id.immediate_payment_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            final OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            Bundle bundle=new Bundle();
                            bundle.putString("ORDERNO",orderFootBean.getOrderno());
                            startActivityForResult(MyOrderDetailsActivity.class,bundle,RESULT_CODE);
                        }
                    case R.id.delete_order_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            final OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            new NormalAlertDialog.Builder(activity)
                                    .setBoolTitle(false)
                                    .setContentText("确认删除订单？")
                                    .setContentTextSize(18)
                                    .setLeftText("取消")
                                    .setRightText("确认")
                                    .setWidth(0.75f)
                                    .setHeight(0.33f)
                                    .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                                        @Override
                                        public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                            dialog.dismiss();
                                        }
                                        @Override
                                        public void clickRightButton(NormalAlertDialog dialog, View view) {
                                            currStatus=orderFootBean.getStatus();
                                            Map<String, String> bodyMap = new HashMap<>();
                                            bodyMap.put("uid", String.valueOf(AppConstant.UID));
                                            bodyMap.put("orderno", String.valueOf(orderFootBean.getOrderno()));
                                            mPresenter.cancleOrder(bodyMap);
                                            dialog.dismiss();
                                        }
                                    }).build().show();

                        }
                        break;
                }
            }
        });
    }
    @Override
    public void onRefresh() {
        load(mType,false);
    }
    @Override
    public void loadOrderSuccess(List<MyOrderListBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList= DataHelper.getDataAfterHandle(data);
        mAdapter.setNewData(dataList);
    }
    @Override
    public void sureGoodsSuuccess(String msg) {
        onRefresh();
        ToastUtil.showShortToast(msg);
    }
    @Override
    public void cancleOrder(String msg) {
        onRefresh();
        ToastUtil.showShortToast(msg);
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
        if(code==0){

        }else{
            dataList.clear();
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(dataList);
            mLoadingView.loadError();
            mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
                @Override
                public void doRequestData() {
                    load(mType,true);
                }
            });
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }
}
