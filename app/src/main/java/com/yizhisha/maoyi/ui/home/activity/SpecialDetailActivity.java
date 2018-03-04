package com.yizhisha.maoyi.ui.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.common.popupwindow.GoodsScressPopuwindow;
import com.yizhisha.maoyi.ui.home.contract.SpecialDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.SpecialDetailPresenter;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

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

    private Banner banner;
    private List<String> imageUrl;
    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean.WeekBean> dataLists = new ArrayList<>();



    private ImageView img_select_price;
    private TextView tv_select_select;
    private TextView tv_select_price;
    private TextView tv_select_xiaoliang;
    private LinearLayout ll_select_price;
    private int price=0;
    private int xiaoliang=0;

    private String spc_id="2";
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
//        mAdapter.setNewData(dataLists);
        addHeadView();
        Bundle bundle=getIntent().getExtras();
        spc_id=bundle.getString("spc_id");

        Map<String,String> map=new HashMap<>();
        map.put("spc_id",spc_id);
        mPresenter.getSpecialDetail(map);
    }
    private void addHeadView() {
        View view=getLayoutInflater().inflate(R.layout.headview_special_detail, (ViewGroup) mRecyclerView.getParent(), false);
        banner=view.findViewById(R.id.banner);
        img_select_price=view.findViewById(R.id.img_select_price);
        tv_select_select=view.findViewById(R.id.tv_select_select);
        tv_select_price=view.findViewById(R.id.tv_select_price);
        tv_select_xiaoliang=view.findViewById(R.id.tv_select_xiaoliang);
        ll_select_price=view.findViewById(R.id.ll_select_price);
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
                mPresenter.getSpecialDetail(map);
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
                }else {
                    img_select_price.setImageResource(R.drawable.price_select_not);
                    price=0;
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.black));
                }
                map.put("spc_id",spc_id);
                mPresenter.getSpecialDetail(map);


            }
        });




        mAdapter.addHeaderView(view);
    }
    @Override
    public void getSpecialDetailSuccess(SpecialDetailBean model) {
        dataLists=model.getGoods();
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
    public void loadFail(String msg) {

    }
    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}
