package com.yizhisha.maoyi.ui.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.GoodsScreesBean;
import com.yizhisha.maoyi.bean.json.GoodsScreesContentBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.common.popupwindow.GoodsScressPopuwindow;
import com.yizhisha.maoyi.ui.home.contract.SpecialDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.SpecialDetailPresenter;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SpecialDetailActivity extends BaseActivity<SpecialDetailPresenter> implements SpecialDetailContract.View {

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

    @Bind(R.id.banner)
    Banner banner;
    private List<String> imageUrl;
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
    private String cid="";//商品类型
    private String price="";//价格
    private String spcId="";//专题ID
    private String mKey="";

    private List<SortedListBean.SortedsBean> sortedsBeanList=new ArrayList<>();

    private GoodsScressPopuwindow popuwindow;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_detail;
    }

    @Override
    protected void initToolBar() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(SpecialDetailActivity.this);
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
        spcId=bundle.getString("spc_id");
        load(true);
        mPresenter.getSortedList();
    }
    private void load(boolean isShowLoad){

        Map<String,String> map=new HashMap<>();
        map.put("spc_id",spcId);
        if(!cid.equals("")){
            map.put("cid", cid);
        }
        if(!price.equals("")){
            map.put("price", price);
        }
        if(sort!=0){
            map.put("order", sort+"");
        }
        if(!mKey.equals("")){
            map.put("key", mKey);
        }
        mPresenter.getSpecialDetail(map,isShowLoad);
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
                            String search="";
                            if(buffer.length()>0) {
                             search = buffer.substring(0, buffer.length() - 1).toString();
                            }
                            cid=search;
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
    public void getSpecialDetailSuccess(SpecialDetailBean model) {

        dataLists=model.getGoods();
        if(dataLists.size()!=0){
            ll_head.setVisibility(View.VISIBLE);
        }
        mAdapter.setNewData(dataLists);
        imageUrl = new ArrayList<>();
        for(int i=0;i<model.getSpecial().size();i++){
            imageUrl.add(AppConstant.BANNER_IMG_URL+model.getSpecial().get(i).getSpc_litpic());
        }
//        imageUrl.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        if(model.getSpecial().size()<2){
            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        }else{
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        }

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrl);
//        banner.setBannerTitles(bannerTitle);
        banner.setDelayTime(3000);
        banner.start();

    }

    @Override
    public void getSortedListSuccess(List<SortedListBean.SortedsBean> model) {

        sortedsBeanList=model;
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
    public void loadFail(int code,String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
            return;
        }
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(true);
            }
        });
        dataLists.clear();
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError(msg);
    }
    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}
