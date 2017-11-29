package com.yizhisha.maoyi.ui.shoppcart.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.ShoppCartAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.GoodsBean;
import com.yizhisha.maoyi.bean.json.ShopcartBean;
import com.yizhisha.maoyi.bean.json.StoreBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.ui.shoppcart.contract.ShoppCartContract;
import com.yizhisha.maoyi.ui.shoppcart.presenter.ShoppCartPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/9/22.
 */

public class ShoppCartFragment extends BaseFragment<ShoppCartPresenter> implements ShoppCartContract.View, SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar mToobar;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.expandableListView)
    ExpandableListView expandableListView;
    @Bind(R.id.id_cb_select_all)
    CheckBox ivSelectAll;
    @Bind(R.id.btnSettle)
    TextView btnSettle;
    @Bind(R.id.tvCountMoney)
    TextView tvCountMoney;
    @Bind(R.id.rlBottomBar)
    RelativeLayout mRlBottomBar;
    @Bind(R.id.normal_shopbotton_ll)
    LinearLayout mLlNormalBottom;
    @Bind(R.id.deleteall_ll)
    LinearLayout mLlDeleteAllBottom;

    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    private ShoppCartAdapter adapter;
    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppcart;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);
        }
    }
    @Override
    protected void initView() {
        mToobar.setRightButton1TextColor(R.color.white);
        mToobar.setRightButton1OnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mToobar.getRightButton1Text().equals("完成")){
                    changeFootShowDeleteView(false);

                }else{
                    changeFootShowDeleteView(true);

                }
            }
        });
        mLoadingView.loadSuccess();
        initAdapter();
        mPresenter.loadShoppCart(AppConstant.UID,true);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        adapter= new ShoppCartAdapter(activity, parentMapList, childMapList_list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "click：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        //选择全部
        ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    adapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });
        //结算
        btnSettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str=new StringBuilder();
                String sid = "";
                for (int i = 0; i < parentMapList.size(); i++) {
                    StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
                    List<Map<String, Object>> childMapList = childMapList_list.get(i);
                    for (int j = 0; j < childMapList.size(); j++) {
                        GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                        if(goodsBean.isChecked()){
                            str.append(goodsBean.getGid()).append(",");
                        }
                    }
                }
                if (str.length() > 0) {
                    sid = str.substring(0, str.length() - 1);
                }
                if(str.length()==0){
                    ToastUtil.showShortToast("请选择要购买的商品");
                    return;
                }
              /*  Bundle bundle=new Bundle();
                bundle.putInt("ORDERTYPE",2);
                bundle.putString("gid",sid);
                startActivity(SureOrderActivity.class,bundle);*/
            }
        });
        adapter.setOnGoodsCheckedChangeListener(new ShoppCartAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice) {
                tvCountMoney.setText(String.format(getString(R.string.total)+totalPrice));
                //id_tv_totalCount_jiesuan.setText(String.format(getString(R.string.jiesuan), totalCount+""));
            }
        });

        adapter.setOnAllCheckedBoxNeedChangeListener(new ShoppCartAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                ivSelectAll.setChecked(allParentIsChecked);
            }
        });

        adapter.setOnCheckHasGoodsListener(new ShoppCartAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                if(isHasGoods){
                    mLoadingView.loadSuccess();
                    mToobar.showRightButton();
                    changeFootShowDeleteView(false);
                    mRlBottomBar.setVisibility(View.VISIBLE);
                }else{
                    mToobar.hideRightButton();
                    changeFootShowDeleteView(true);
                    mLoadingView.loadSuccess(true, R.drawable.ic_launcher,"您的购物车中还没有商品，请您先逛逛!");
                    mRlBottomBar.setVisibility(View.GONE);
                }
                //setupViewsShow(isHasGoods);
            }
        });
        //删除单项商品
        adapter.setOnDeleteShopListener(new ShoppCartAdapter.OnDeleteShopListener() {
            @Override
            public void onDeleteShop(final int groupPosition, final int childPosition) {
                new NormalAlertDialog.Builder(activity)
                        .setBoolTitle(false)
                        .setContentText("确定删除该商品吗?")
                        .setContentTextColor(R.color.blue)
                        .setLeftText("取消")
                        .setLeftTextColor(R.color.blue)
                        .setRightText("确认")
                        .setRightTextColor(R.color.blue)
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                final GoodsBean goodsBean = (GoodsBean) childMapList_list.get(groupPosition).get(childPosition).get("childName");
                                Map<String,String> map=new HashMap<>();
                                map.put("uid",String.valueOf(AppConstant.UID));
                                map.put("sid", String.valueOf(goodsBean.getSid()));
                                mPresenter.deleteOneShoppCart(map,groupPosition,childPosition);
                                adapter.removeOneGood(groupPosition,childPosition);
                                dialog.dismiss();

                            }
                        }).build().show();

            }
        });
        adapter.setOnGoodsEditChangeListenr(new ShoppCartAdapter.OnGoodsEditChangeListenr() {
            @Override
            public void onEditChange(GoodsBean goodsBean) {
               /* Bundle bundle=new Bundle();
                bundle.putInt("gid",goodsBean.getGid());
                bundle.putInt("sid",goodsBean.getSid());
                startActivityForResult(SingleShopCartActivity.class,bundle,100);*/
            }
        });
        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(expandableListView != null && expandableListView.getChildCount() > 0){
                    boolean firstItemVisible = expandableListView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = expandableListView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mSwipeRefreshLayout.setEnabled(enable);
            }
        });
    }
    public void changeFootShowDeleteView(boolean showDeleteView) {

        if (showDeleteView) {
            mToobar.setRightButton1Text("完成");
            mToobar.setRightButton1Icon(null);
            mLlNormalBottom.setVisibility(View.GONE);
            mLlDeleteAllBottom.setVisibility(View.VISIBLE);
        } else {
            mToobar.setRightButton1Text("");
            mToobar.setRightButton1Icon(RescourseUtil.getDrawable(R.drawable.icon_ratingbar));
            mLlNormalBottom.setVisibility(View.VISIBLE);
            mLlDeleteAllBottom.setVisibility(View.GONE);
        }
    }
    @Override
    public void onRefresh() {
        mPresenter.loadShoppCart(AppConstant.UID,false);
    }
    @Override
    public void loadSuccess(List<ShopcartBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();
        for (int i = 0; i < data.size(); i++) {
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            StoreBean store=new StoreBean();
            store.setMzw_uid(data.get(i).getMzw_uid());
            store.setCompany(data.get(i).getCompany());
            store.setChecked(false);
            store.setEditing(0);
            parentMap.put("parentName",store);
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            List<ShopcartBean.Goods> goods=data.get(i).getGoods();
            for (int j = 0; j < goods.size(); j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();

                GoodsBean goodsBean=new GoodsBean();
                goodsBean.setGid(goods.get(j).getGid());
                goodsBean.setTitle(goods.get(j).getTitle());
                goodsBean.setPrice(goods.get(j).getPrice());
                goodsBean.setLitpic(goods.get(j).getLitpic());
                goodsBean.setAmount(goods.get(j).getAmount());
                goodsBean.setDetail(goods.get(j).getDetail());
                goodsBean.setChecked(false);
                goodsBean.setEditing(0);
                childMap.put("childName", goodsBean);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }
        //需要展开分组，才会刷新childView
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        if (parentMapList != null && parentMapList.size() > 0) {

            mToobar.showRightButton();
            mRlBottomBar.setVisibility(View.VISIBLE);
        } else {
            mToobar.hideRightButton();
            mRlBottomBar.setVisibility(View.GONE);

        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void deleteShoppCart(String msg) {
        adapter.removeGoods();
        mToobar.setRightButtonText("编辑");
    }
    @Override
    public void deleteOneShoppCart(String msg, int groupPosition, int childPosition) {
        mPresenter.loadShoppCart(AppConstant.UID,false);
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
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();
        mToobar.hideRightButton();
        mRlBottomBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        mLoadingView.loadSuccess(true, R.drawable.ic_launcher,"您的购物车中还没有商品，请您先逛逛!");
    }

    @Override
    public void loadFail(int code, String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
            return;
        }
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();
        mToobar.hideRightButton();
        mRlBottomBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                mPresenter.loadShoppCart(AppConstant.UID,true);
            }
        });
    }
    @OnClick({R.id.deleteall_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deleteall_btn:
                StringBuilder str=new StringBuilder();
                for (int i = 0; i < parentMapList.size(); i++) {
                    List<Map<String, Object>> childMapList = childMapList_list.get(i);
                    for (int j = 0; j < childMapList.size(); j++) {
                        GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                        if(goodsBean.isChecked()){
                            str.append(goodsBean.getSid());
                            str.append(",");
                        }
                    }
                }
                if(str.length()==0){
                    ToastUtil.showShortToast("请选择要购买的商品");
                    return;
                }
                final String sid = str.substring(0, str.length() - 1);
                new NormalAlertDialog.Builder(activity)
                        .setBoolTitle(false)
                        .setContentText("确定删除选中的商品吗?")
                        .setContentTextColor(R.color.blue)
                        .setLeftText("取消")
                        .setLeftTextColor(R.color.blue)
                        .setRightText("确认")
                        .setRightTextColor(R.color.blue)
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {

                                Map<String,String> map=new HashMap<>();
                                map.put("uid",String.valueOf(AppConstant.UID));
                                map.put("sid", sid);
                                mPresenter.deleteShoppCart(map);
                                dialog.dismiss();

                            }
                        }).build().show();

                break;

        }
    }
}
