package com.yizhisha.maoyi.ui.type.avtivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
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
import com.yizhisha.maoyi.widget.ClearEditText;
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
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.ll_search)
    LinearLayout searchLl;
    @Bind(R.id.search_selectyarn_et)
    ClearEditText searchEt;
    @Bind(R.id.search_iv)
    ImageView searchIv;
    @Bind(R.id.search_tv)
    TextView searchTv;
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
    @Bind(R.id.ll_head)
    LinearLayout ll_head;

    //查询条件
    private int sort=0;//排序
    private String search="";//商品类型
    private String cid="";
    private String price="";//价格
    private String mKey="";//搜索词


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
        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleTv.setVisibility(View.GONE);
                searchLl.setVisibility(View.VISIBLE);
                searchIv.setVisibility(View.GONE);
                searchTv.setVisibility(View.VISIBLE);
            }
        });
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    searchTv.setText("搜索");
                }else{
                    mKey="";
                    load(false);
                    searchTv.setText("取消");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=searchTv.getText().toString().trim();
                if(text.equals("搜索")){
                    String key=searchEt.getText().toString().trim();
                    mKey=key.replaceAll(" +"," ");
                    load(false);
                }else{
                    searchLl.setVisibility(View.GONE);
                    titleTv.setVisibility(View.VISIBLE);
                    searchIv.setVisibility(View.VISIBLE);
                    searchTv.setVisibility(View.GONE);
                }
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
        if(bundle.getString("cid")!=null){
            cid=bundle.getString("cid");
        }else if(bundle.getString("KEY")!=null){
            mKey=bundle.getString("KEY");
        }
        load(true);
        mPresenter.getSortedList();
    }
    private void load(boolean isShowLoad){

        Map<String,String> map=new HashMap<>();
        map.put("cid", cid);
        if(!price.equals("")){
            map.put("price", price);
        }
        if(sort!=0){
            map.put("order", sort+"");
        }
        if(!search.equals("")){
            map.put("search", search);
        }
        if(!mKey.equals("")){
            map.put("key", mKey);
        }
        mPresenter.getGoodsSorted(map,isShowLoad);
    }

    private void initHeadView() {
        tv_select_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popuwindow==null){
                    popuwindow=new GoodsScressPopuwindow(mContext,false);
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
                            String searchs="";
                            if(buffer.length()>0) {
                                searchs = buffer.substring(0, buffer.length() - 1).toString();
                            }
                            search=searchs;
                            price=valuePrice;

                            load(false);
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
                img_select_price.setImageResource(R.drawable.price_select_not);
                tv_select_price.setTextColor(RescourseUtil.getColor(R.color.black));
                if(sort==1||sort==0){
                    sort=4;
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
                }else{
                    sort=1;
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.red1));
                }

                load(false);
            }
        });
        ll_select_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
                if(sort==0||sort==1||sort==4){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    sort=5;
                }else if(sort==5){
                    img_select_price.setImageResource(R.drawable.price_select_down);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    sort=2;
                }else if(sort==2){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    sort=5;
                }
                load(false);

            }
        });
    }
    @Override
    public void getGoodsSortedSuccess(SpecialDetailBean model) {
        dataLists.clear();
        dataLists=model.getGoods();
        if(dataLists.size()!=0){
            ll_head.setVisibility(View.VISIBLE);
        }
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
               load(true);
            }
        });
        dataLists.clear();
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError(msg);
    }
}
