package com.yizhisha.maoyi.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.GoodsProductBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.ui.home.contract.ProductDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.ProductDetailPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.img_litpic)
    ImageView imgLitpic;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_sales)
    TextView tvSales;
    @Bind(R.id.tv_district)
    TextView tvDistrict;
    @Bind(R.id.tv_pname)
    TextView tvPname;
    @Bind(R.id.tv_size)
    TextView tvSize;
    @Bind(R.id.tv_color)
    TextView tvColor;
    @Bind(R.id.tv_devi_length1)
    TextView tvDeviLength1;
    @Bind(R.id.tv_devi_length2)
    TextView tvDeviLength2;
    @Bind(R.id.tv_devi_length3)
    TextView tvDeviLength3;
    @Bind(R.id.tv_devi_size1)
    TextView tvDeviSize1;
    @Bind(R.id.tv_devi_size2)
    TextView tvDeviSize2;
    @Bind(R.id.tv_devi_size3)
    TextView tvDeviSize3;
    @Bind(R.id.tv_devi_elastic1)
    TextView tvDeviElastic1;
    @Bind(R.id.tv_devi_elastic2)
    TextView tvDeviElastic2;
    @Bind(R.id.tv_devi_elastic3)
    TextView tvDeviElastic3;
    @Bind(R.id.rl_product_detail)
    RecyclerView rlProductDetail;
    @Bind(R.id.rl_tuijian)
    RecyclerView rlTuijian;
    @Bind(R.id.ll_collection)
    LinearLayout llCollection;
    @Bind(R.id.ll_shopping_cart)
    LinearLayout llShoppingCart;
    @Bind(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @Bind(R.id.tv_shopping)
    TextView tvShopping;
    @Bind(R.id.bottom_ll)
    LinearLayout bottomLl;


    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initToolBar() {


    }

    @Override
    protected void initView() {
        rlTuijian.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new SDayExplosionAdapter(dataLists);
        rlTuijian.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ProductDetailActivity.class);
            }
        });

        Map<String,String> map=new HashMap<>();
        map.put("gid","2");
        mPresenter.getGoodsDetail(map);

        mPresenter.getSimilarRecommen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getGoodsDetailSuccess(GoodsDetailBean model) {
        GoodsProductBean goodsProductBean=model.getGoods();
        String devi_lenth=goodsProductBean.getDevi_length();
        if(devi_lenth.equals("1")){
            tvDeviLength1.setBackgroundResource(R.color.common_color);
        }else if(devi_lenth.equals("2")){
            tvDeviLength2.setBackgroundResource(R.color.common_color);
        }else if(devi_lenth.equals("3")){
            tvDeviLength3.setBackgroundResource(R.color.common_color);
        }
        String devi_size=goodsProductBean.getDevi_size();
        if(devi_size.equals("1")){
            tvDeviSize1.setBackgroundResource(R.color.common_color);
        }else if(devi_size.equals("2")){
            tvDeviSize2.setBackgroundResource(R.color.common_color);
        }else if(devi_size.equals("3")){
            tvDeviSize3.setBackgroundResource(R.color.common_color);
        }
        String devi_elastic=goodsProductBean.getDevi_elastic();
        if(devi_elastic.equals("1")){
            tvDeviElastic1.setBackgroundResource(R.color.common_color);
        }else if(devi_elastic.equals("2")){
            tvDeviElastic2.setBackgroundResource(R.color.common_color);
        }else if(devi_elastic.equals("3")){
            tvDeviElastic3.setBackgroundResource(R.color.common_color);
        }

        tvTitle.setText(goodsProductBean.getTitle());
        tvPname.setText("商品品类："+goodsProductBean.getPname());
        tvSales.setText("月销量："+goodsProductBean.getSales()+"笔");
        tvMoney.setText("￥"+goodsProductBean.getPrice());
        Glide.with(ProductDetailActivity.this).load(AppConstant.PRUDUCT_IMG_URL+goodsProductBean.getLitpic()).into(imgLitpic);
    }

    @Override
    public void getSimilarRecommenSuccess(SimilarRecommenBean model) {
        dataLists=model.getGoods();
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void loadFail(String msg) {

    }
}
