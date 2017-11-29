package com.yizhisha.maoyi.ui.home.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.yizhisha.maoyi.bean.json.GoodsStyleBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.ui.home.contract.ProductDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.ProductDetailPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.ll_select_color_size)
    LinearLayout llSelectColorSize;


    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean> dataLists = new ArrayList<>();

    private PopupWindow popupWindow;
    GoodsProductBean goodsProductBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initToolBar() {


    }

    @Override
    protected void initView() {
        rlTuijian.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new SDayExplosionAdapter(dataLists);
        rlTuijian.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ProductDetailActivity.class);
            }
        });

        Map<String, String> map = new HashMap<>();
        map.put("gid", "3");
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
         goodsProductBean = model.getGoods();
        String devi_lenth = goodsProductBean.getDevi_length();
        if (devi_lenth.equals("1")) {
            tvDeviLength1.setBackgroundResource(R.color.common_color);
        } else if (devi_lenth.equals("2")) {
            tvDeviLength2.setBackgroundResource(R.color.common_color);
        } else if (devi_lenth.equals("3")) {
            tvDeviLength3.setBackgroundResource(R.color.common_color);
        }
        String devi_size = goodsProductBean.getDevi_size();
        if (devi_size.equals("1")) {
            tvDeviSize1.setBackgroundResource(R.color.common_color);
        } else if (devi_size.equals("2")) {
            tvDeviSize2.setBackgroundResource(R.color.common_color);
        } else if (devi_size.equals("3")) {
            tvDeviSize3.setBackgroundResource(R.color.common_color);
        }
        String devi_elastic = goodsProductBean.getDevi_elastic();
        if (devi_elastic.equals("1")) {
            tvDeviElastic1.setBackgroundResource(R.color.common_color);
        } else if (devi_elastic.equals("2")) {
            tvDeviElastic2.setBackgroundResource(R.color.common_color);
        } else if (devi_elastic.equals("3")) {
            tvDeviElastic3.setBackgroundResource(R.color.common_color);
        }

        tvTitle.setText(goodsProductBean.getTitle());
        tvPname.setText("商品品类：" + goodsProductBean.getPname());
        tvSales.setText("月销量：" + goodsProductBean.getSales() + "笔");
        tvMoney.setText("￥" + goodsProductBean.getPrice());
        Glide.with(ProductDetailActivity.this).load(AppConstant.PRUDUCT_IMG_URL + goodsProductBean.getLitpic()).into(imgLitpic);
    }
    @OnClick({R.id.ll_select_color_size})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_select_color_size:
               final  String[] mVals = new String[]
                        {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                                };
                if(goodsProductBean==null){
                    return;
                }
                if(goodsProductBean.getStyle()==null){
                    return;
                }
                List<GoodsStyleBean> style=new ArrayList<>();
                style=goodsProductBean.getStyle();
                List<String> color=new ArrayList<>();
                final List<String> size=new ArrayList<>();
                for(int i=0;i<goodsProductBean.getStyle().size();i++){
                    color.add(goodsProductBean.getStyle().get(i).getColor());
                }
                View contentView= LayoutInflater.from(this).inflate(R.layout.popuwindow_product_detail_select, null);
                final LayoutInflater mInflater = LayoutInflater.from(this);
              final  TagFlowLayout tf_size=(TagFlowLayout)contentView.findViewById(R.id.tf_size);
                final  TagFlowLayout tf_color=(TagFlowLayout)contentView.findViewById(R.id.tf_color);

                //尺码
            final    TagAdapter tagAdapter_size=new TagAdapter<String>(size){
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.item_tagflow_tv,tf_size, false);
                        tv.setText(s);
                        return tv;
                    }
                };
                tf_size.setAdapter(tagAdapter_size);

                tf_size.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
                {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent)
                    {
//                        Toast.makeText(ProductDetailActivity.this, mVals[position]+"", Toast.LENGTH_SHORT).show();
                        //view.setVisibility(View.GONE);
                        return true;
                    }
                });


                tf_size.setOnSelectListener(new TagFlowLayout.OnSelectListener()
                {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet)
                    {
//                        getActivity().setTitle("choose:" + selectPosSet.toString());
                    }
                });
                //颜色

                TagAdapter tagAdapter_color=new TagAdapter<String>(color){
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.item_tagflow_tv,tf_color, false);
                        tv.setText(s);
                        return tv;
                    }
                };
                tf_color.setAdapter(tagAdapter_color);

                tf_color.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
                {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent)
                    {
                        String a=goodsProductBean.getStyle().get(position).getSize();
                        String[] b=a.split(",");
                        size.clear();
                        for(int j=0;j<b.length;j++){
                        size.add(b[j]);
                            tagAdapter_size.notifyDataChanged();
//                            tv_si

                    }
//                        Toast.makeText(ProductDetailActivity.this, mVals[position]+"", Toast.LENGTH_SHORT).show();
                        //view.setVisibility(View.GONE);
                        return true;
                    }
                });


                tf_color.setOnSelectListener(new TagFlowLayout.OnSelectListener()
                {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet)
                    {
//                        getActivity().setTitle("choose:" + selectPosSet.toString());
                    }
                });
                tagAdapter_color.setSelectedList(0);






                 popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(ProductDetailActivity.this.findViewById(R.id.ll_select_color_size), Gravity.BOTTOM,0,0);


                break;
        }

    }

    @Override
    public void getSimilarRecommenSuccess(SimilarRecommenBean model) {
        dataLists = model.getGoods();
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void loadFail(String msg) {

    }
}
