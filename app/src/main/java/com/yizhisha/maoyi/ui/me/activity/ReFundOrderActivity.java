package com.yizhisha.maoyi.ui.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyRefundOrderAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ReFundOrderActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
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
        dataList.addAll(getDataAfterHandle());
        mLoadingView.loadSuccess();
        initAdapter();
        mAdapter.setNewData(dataList);
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
        mAdapter.setOnItemTypeClickListener(new MyRefundOrderAdapter.OnItemTypeClickListener() {
            @Override
            public void onItemClick(View view, int type, int position) {
                if(type==2) {
                    startActivity(ReFundOrderDetailsActivity.class);
                }
            }
        });

    }

    @Override
    public void onRefresh() {

    }
    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @return
     */
    public static ArrayList<Object> getDataAfterHandle() {
        ArrayList<Object> dataList = new ArrayList<Object>();
        for(int i=0;i<4;i++) {
            //遍历每一张大订单
            //大订单支付的金额核定单状态
            MyOrderListBean.Goods orderHeadBean = new MyOrderListBean().new Goods();
            dataList.add(orderHeadBean);


            List<MyOrderListBean> goodses = new ArrayList<>();
            //遍历每个大订单里面的小订单
            MyOrderListBean.Goods goodsBean = new MyOrderListBean().new Goods();
            dataList.add(goodsBean);
            OrderFootBean orderFootBean = new OrderFootBean();
            dataList.add(orderFootBean);
        }
            return dataList;
    }
}
