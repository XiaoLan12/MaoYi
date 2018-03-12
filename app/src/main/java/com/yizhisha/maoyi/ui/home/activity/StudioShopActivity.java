package com.yizhisha.maoyi.ui.home.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyAddressAdapter;
import com.yizhisha.maoyi.adapter.StudioShopAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.GoodsScreesBean;
import com.yizhisha.maoyi.bean.json.GoodsScreesContentBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.common.popupwindow.GoodsScressPopuwindow;
import com.yizhisha.maoyi.ui.home.contract.StudioShopContract;
import com.yizhisha.maoyi.ui.home.presenter.StudioShopPresenter;
import com.yizhisha.maoyi.ui.me.activity.AddAddressActivity;
import com.yizhisha.maoyi.ui.me.activity.MyAddressActivity;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class StudioShopActivity extends BaseActivity<StudioShopPresenter> implements StudioShopContract.View
,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.work_img_iv)
    ImageView workImgIv;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.focus_iv)
    ImageView  focusIv;
    @Bind(R.id.head_iv)
    ImageView headIv;


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @Bind(R.id.loadingView)
    CommonLoadingView loadingView;

    private StudioShopAdapter mAdapter;

    private List<StudioShopBean.Goods> dataLists=new ArrayList<>();
    private StudioShopBean studioShopBean;

    private int mWid;//工作室ID；
    private boolean isFocus=false;


    private ImageView img_banner;
    private ImageView img_select_price;
    private TextView tv_select_select;
    private TextView tv_select_price;
    private TextView tv_select_xiaoliang;
    private LinearLayout ll_select_price;
    private int price=0;
    private int xiaoliang=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_studio_shop;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mWid=bundle.getInt("wid");
        }
        initAdapter();
        Map<String,String> body=new HashMap<>();
        body.put("wid",String.valueOf(mWid));
        body.put("uid",String.valueOf(AppConstant.UID));
        load(body,true);
        addHeadView();
    }
    private void initAdapter(){
        swiperefreshlayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        swiperefreshlayout.setProgressViewEndTarget(false, 100);
        swiperefreshlayout.setOnRefreshListener(this);

        mAdapter=new StudioShopAdapter(dataLists);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(mAdapter);
    }

    private void load(Map<String,String> map,boolean isShowLoad){
        mPresenter.getStudioShop(map,isShowLoad);
    }
    @Override
    public void getStudioShopSuccess(StudioShopBean model) {
        swiperefreshlayout.setRefreshing(false);
        if(model==null){
            return;
        }
        studioShopBean=model;
        String favorite=model.getFavorite();
        if(favorite!=null){
            focusIv.setVisibility(View.VISIBLE);
            if(favorite.equals("y")){
                isFocus=true;
                focusIv.setImageResource(R.drawable.icon_favorit);
            }else{
                isFocus=false;
                focusIv.setImageResource(R.drawable.icon_favorit_normale);
            }
        }else{
            focusIv.setVisibility(View.GONE);
        }
        if(model.getWorkshop()!=null){
            StudioShopBean.Workshop workshop=model.getWorkshop();
            GlideUtil.getInstance().LoadContextBitmap(this,workshop.getBackground(),workImgIv,GlideUtil.LOAD_BITMAP);
            GlideUtil.getInstance().LoadContextBitmap(this,workshop.getAvatar(),headIv,GlideUtil.LOAD_BITMAP);
            nameTv.setText(workshop.getLinkman());
        }

        dataLists.clear();
        dataLists.addAll(model.getGoods());
        mAdapter.setNewData(dataLists);

    }
    private void addHeadView() {
        View view=this.getLayoutInflater().inflate(R.layout.headview_studio, (ViewGroup) recyclerview.getParent(), false);
        img_banner= (ImageView) view.findViewById(R.id.img_banner);
        img_select_price=(ImageView)view.findViewById(R.id.img_select_price);
        tv_select_select=(TextView)view.findViewById(R.id.tv_select_select);
        tv_select_price=(TextView)view.findViewById(R.id.tv_select_price);
        tv_select_xiaoliang=(TextView)view.findViewById(R.id.tv_select_xiaoliang);
        ll_select_price=(LinearLayout) view.findViewById(R.id.ll_select_price);
        tv_select_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsScressPopuwindow popuwindow=new GoodsScressPopuwindow(mContext);
                List<Object> objects=new ArrayList<>();
                GoodsScreesBean goodsScreesBean=new GoodsScreesBean();
                goodsScreesBean.setItem("裙子");
                objects.add(goodsScreesBean);
                for(int i=0;i<3;i++){
                    GoodsScreesContentBean goodsScreesContentBean=new GoodsScreesContentBean();
                    goodsScreesContentBean.setTitle("item1");
                    goodsScreesContentBean.setTitle("item2");
                    goodsScreesContentBean.setTitle("item3");
                    objects.add(goodsScreesContentBean);
                }

                GoodsScreesBean goodsScreesBean1=new GoodsScreesBean();
                goodsScreesBean1.setItem("库子");
                objects.add(goodsScreesBean1);
                for(int i=0;i<3;i++){
                    GoodsScreesContentBean goodsScreesContentBean=new GoodsScreesContentBean();
                    goodsScreesContentBean.setTitle("item1");
                    goodsScreesContentBean.setTitle("item2");
                    goodsScreesContentBean.setTitle("item3");
                    objects.add(goodsScreesContentBean);
                }

                popuwindow.serData(objects);
                popuwindow.showAtLocation(view, Gravity.CENTER, 0, 0);

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

                Map<String,String> body=new HashMap<>();
                body.put("wid",String.valueOf(mWid));
                body.put("uid",String.valueOf(AppConstant.UID));
                if(xiaoliang==0){
                    xiaoliang=1;
                    body.put("order","1");
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.red1));
                }else{
                    xiaoliang=0;
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
                }
                load(body,false);
            }
        });
        ll_select_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(xiaoliang!=0) {
                    xiaoliang = 0;
                    tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
                }
                Map<String,String> body=new HashMap<>();
                body.put("wid",String.valueOf(mWid));
                body.put("uid",String.valueOf(AppConstant.UID));
                if(price==0){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    price=4;
                    body.put("order","4");
                }else if(price==4){
                    img_select_price.setImageResource(R.drawable.price_select_down);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    price=3;
                    body.put("order","3");
                }else {
                    img_select_price.setImageResource(R.drawable.price_select_not);
                    price=0;
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.black));
                }
                load(body,false);


            }
        });
        mAdapter.addHeaderView(view);
    }
    @Override
    public void focusWorkSuccess(String msg) {
        if(isFocus){
            focusIv.setImageResource(R.drawable.icon_favorit);
        }else{
            focusIv.setImageResource(R.drawable.icon_favorit_normale);
        }
        ToastUtil.showShortToast(msg);
    }
    @Override
    public void showLoading() {
        loadingView.load();
    }
    @Override
    public void hideLoading() {
        loadingView.loadSuccess();
    }
    @Override
    public void showEmpty() {
        dataLists.clear();
        swiperefreshlayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        loadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(int code,String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
            return;
        }
        loadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                Map<String,String> body=new HashMap<>();
                body.put("wid",String.valueOf(mWid));
                load(body,true);
            }
        });
        dataLists.clear();
        swiperefreshlayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        loadingView.loadError();
    }

    @Override
    public void onRefresh() {
        Map<String,String> body=new HashMap<>();
        body.put("wid",String.valueOf(mWid));
        load(body,false);
    }

    @OnClick({R.id.focus_iv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.focus_iv:
                if(isFocus){
                    isFocus=false;
                }else {
                    isFocus=true;
                }
                Map<String,String> body=new HashMap<>();
                body.put("wid",String.valueOf(mWid));
                body.put("uid",String.valueOf(AppConstant.UID));
                mPresenter.focusWork(body);
                break;
        }
    }

}
