package com.yizhisha.maoyi.ui.shoppcart.fragment;

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

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.ShoppCartAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.GoodsBean;
import com.yizhisha.maoyi.bean.json.StoreBean;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/22.
 */

public class ShoppCartFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
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
    protected void initView() {
        mLoadingView.loadSuccess();
        data();
        initAdapter();
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
            }
        });
        /*adapter.setOnGoodsCheckedChangeListener(new ShoppCartAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice) {
                tvCountMoney.setText(String.format(getString(R.string.total)+totalPrice));
                //id_tv_totalCount_jiesuan.setText(String.format(getString(R.string.jiesuan), totalCount+""));
            }
        });*/

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
                    mRlBottomBar.setVisibility(View.VISIBLE);
                }else{
                    mToobar.hideRightButton();
                    mLoadingView.loadSuccess(true, R.drawable.ic_launcher,"您的购物车中还没有商品，请您先逛逛!");
                    mRlBottomBar.setVisibility(View.GONE);
                }
                //setupViewsShow(isHasGoods);
            }
        });

        adapter.setOnGoodsEditChangeListenr(new ShoppCartAdapter.OnGoodsEditChangeListenr() {
            @Override
            public void onEditChange(GoodsBean goodsBean) {

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
    private void data(){
        for (int i = 0; i < 3; i++) {
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            StoreBean store=new StoreBean();
            store.setChecked(false);
            store.setEditing(0);
            parentMap.put("parentName",store);
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < 2; j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();
                GoodsBean goodsBean=new GoodsBean();
                goodsBean.setChecked(false);
                goodsBean.setEditing(0);
                childMap.put("childName", goodsBean);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }
       /* //需要展开分组，才会刷新childView
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        if (parentMapList != null && parentMapList.size() > 0) {

            mToobar.showRightButton();
            mRlBottomBar.setVisibility(View.VISIBLE);
        } else {
            mToobar.hideRightButton();
            mRlBottomBar.setVisibility(View.GONE);

        }*/
    }
    @Override
    public void onRefresh() {

    }
}
