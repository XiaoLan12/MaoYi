package com.yizhisha.maoyi.ui.type.avtivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.common.popupwindow.GoodsScressPopuwindow;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;
import com.yizhisha.maoyi.ui.type.contract.TypeShopListContract;
import com.yizhisha.maoyi.ui.type.presenter.TypeShopListPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class TypeShopListActivity extends BaseActivity<TypeShopListPresenter> implements TypeShopListContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.new_iv)
    ImageView newIv;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean.WeekBean> dataLists = new ArrayList<>();


    @Bind(R.id.img_select_price)
    ImageView img_select_price;
    @Bind(R.id.tv_select_select)
    TextView tv_select_select;
    @Bind(R.id.tv_select_price)
    TextView tv_select_price;
    @Bind(R.id.tv_select_xiaoliang)
    TextView tv_select_xiaoliang;
    @Bind(R.id.ll_select_price)
    LinearLayout ll_select_price;
    private int price=0;
    private int xiaoliang=0;

    private String spc_id="2";
    private String cid;

    private int mWid;//工作室ID；

    private List<SortedListBean.SortedsBean> sortedsBeanList=new ArrayList<>();

    private GoodsScressPopuwindow popuwindow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_type_shop_list;
    }
    @Override
    protected void initToolBar() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(TypeShopListActivity.this);
            }
        });
        newIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NewActivity.class);
            }
        });
    }

    @Override
    protected void initView() {
        newIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NewActivity.class);
            }
        });
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new SDayExplosionAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("gid", dataLists.get(position).getId());
                startActivity(ProductDetailActivity.class,bundle);
            }
        });
        initHeadView();
        Bundle bundle=getIntent().getExtras();
        spc_id=bundle.getString("spc_id");
        cid=bundle.getString("cid");
        Map<String,String> map=new HashMap<>();
        map.put("cid",cid);
        map.put("spc_id",spc_id);
        mPresenter.getGoodsSorted(map,true);
        mPresenter.getSortedList();
    }

    private void initHeadView() {
        tv_select_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popuwindow==null){
                    popuwindow=new GoodsScressPopuwindow(mContext);
                    popuwindow.serData1(sortedsBeanList);
                    popuwindow.setOnSearchOnClick(new GoodsScressPopuwindow.OnSearchOnClick() {
                        @Override
                        public void onSearchLisenter() {
                            List<Integer> data=popuwindow.getSelectData();
                            String valuePrice=popuwindow.getPrice();
                            StringBuffer buffer=new StringBuffer();
                            for(Integer str:data){
                                buffer.append(str).append(",");
                            }
                            String search="";
                            if(buffer.length()>0) {
                                search = buffer.substring(0, buffer.length() - 1).toString();
                            }else{
                                search=cid;
                            }
                            Map<String,String> map=new HashMap<>();
                            map.put("spc_id",spc_id);
                            if(price==0){
                                if(xiaoliang!=0) {
                                    map.put("order", xiaoliang + "");
                                }
                            }else{
                                if(price!=0) {
                                    map.put("order", price + "");
                                }
                            }
                            if(!search.equals("")) {
                                map.put("cid", search);
                            }
                            if(!valuePrice.equals("")){
                                map.put("price", valuePrice);
                            }

                            mPresenter.getGoodsSorted(map,false);
                            popuwindow.dismiss();
                        }
                    });
                    popuwindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
                }else{
                    popuwindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
                }


            }
        });
        tv_select_xiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(price!=0){
                    price=0;
                    img_select_price.setImageResource(R.drawable.price_select_not);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.black));
                }

                Map<String,String> map=new HashMap<>();
                if(xiaoliang==0){
                    xiaoliang=1;
                    map.put("order","1");
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.red1));
                }else{
                    xiaoliang=0;
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
                }
                map.put("spc_id",spc_id);
                map.put("cid",cid);
                mPresenter.getGoodsSorted(map,false);
            }
        });
        ll_select_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(xiaoliang!=0) {
                    xiaoliang = 0;
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
                }
                Map<String,String> map=new HashMap<>();
                if(price==0){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    price=4;
                    map.put("order","4");
                }else if(price==4){
                    img_select_price.setImageResource(R.drawable.price_select_down);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    price=3;
                    map.put("order","3");
                }else if(price==3){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    price=4;
                    map.put("order","3");
                }
                map.put("spc_id",spc_id);
                map.put("cid",cid);
                mPresenter.getGoodsSorted(map,false);


            }
        });
    }
    @Override
    public void getGoodsSortedSuccess(SpecialDetailBean model) {
        dataLists.clear();
        dataLists=model.getGoods();
        mAdapter.setNewData(dataLists);
    }
    @Override
    public void getSortedListSuccess(List<SortedListBean.SortedsBean> model) {
        sortedsBeanList=model;
    }

    @Override
    public void getStudioShopSuccess(StudioShopBean model) {

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
        dataLists.clear();
        mAdapter.setNewData(dataLists);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(int code, String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
            return;
        }
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                Map<String,String> map=new HashMap<>();
                map.put("spc_id",spc_id);
                map.put("cid",spc_id);
                mPresenter.getGoodsSorted(map,true);
            }
        });
        dataLists.clear();
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError(msg);
    }
}
