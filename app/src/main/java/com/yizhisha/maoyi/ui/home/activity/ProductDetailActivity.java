package com.yizhisha.maoyi.ui.home.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.EditShoppcartAdapter;
import com.yizhisha.maoyi.adapter.ProductDetailImgAdapter;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.GoodsAttrsBean;
import com.yizhisha.maoyi.bean.json.BannerResult;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.GoodsProductBean;
import com.yizhisha.maoyi.bean.json.GoodsStyleBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenListBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.common.dialog.CustomDialog;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.common.dialog.PicShowDialog;
import com.yizhisha.maoyi.ui.home.contract.ProductDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.ProductDetailPresenter;
import com.yizhisha.maoyi.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.maoyi.ui.login.activity.RegisterActivity;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ImageSlideshow;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;
import com.yizhisha.maoyi.widget.SKUInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.is_gallery)
    ImageSlideshow imageSlideshow;

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
    @Bind(R.id.collection_iv)
    ImageView collectIv;
    @Bind(R.id.ll_collection)
    LinearLayout llCollection;
    @Bind(R.id.ll_shopping_cart)
    LinearLayout llShoppingCart;
    @Bind(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @Bind(R.id.tv_shopping)
    TextView tvShopping;
    @Bind(R.id.shop_attri_tv)
    TextView shopAttriTv;
    @Bind(R.id.bottom_ll)
    LinearLayout bottomLl;
    @Bind(R.id.ll_select_color_size)
    LinearLayout llSelectColorSize;
    //评论
    @Bind(R.id.comment_amount_tv)
    TextView commentAmountTv;
    @Bind(R.id.comment_img_iv)
    ImageView commentImgIv;
    @Bind(R.id.comment_name_tv)
    TextView commentNameTv;
    @Bind(R.id.comment_content_tv)
    TextView commentContentTv;
    @Bind(R.id.look_allcomment_tv)
    TextView lookAllcommentTv;
    @Bind(R.id.ll_img)
    LinearLayout llImg;

    private SDayExplosionAdapter mAdapter1;
    private List<WeekListBean.WeekBean> dataLists = new ArrayList<>();

    private PopupWindow popupWindow;
    GoodsProductBean goodsProductBean;
    GoodsDetailBean goodsDetailBean;
    private int mGid;
    //详情
    private List<String> contentList = new ArrayList<>();




    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initToolBar() {
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NewActivity.class);
            }
        });
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(ProductDetailActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mGid=bundle.getInt("gid");
        }
        rlTuijian.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter1 = new SDayExplosionAdapter(dataLists);
        rlTuijian.setAdapter(mAdapter1);
        Map<String, String> map = new HashMap<>();
        map.put("gid", String.valueOf(mGid));
        map.put("uid", String.valueOf(3));
        mPresenter.getGoodsDetail(map);

        // 为ImageSlideshow设置数据
        imageSlideshow.setDotSpace(12);
        imageSlideshow.setDotSize(12);
        imageSlideshow.setDelay(3000);
        imageSlideshow.commit();

    }

    @Override
    public void getGoodsDetailSuccess(GoodsDetailBean model) {
        goodsDetailBean=model;
        goodsProductBean = model.getGoods();

        List<BannerResult> bannerResultsList=new ArrayList<>();
        BannerResult bannerResult=new BannerResult();
        bannerResult.setUrl(goodsProductBean.getLitpic());
        imageSlideshow.setImageTitleBeanList(bannerResultsList);
        mPresenter.getSimilarRecommen(goodsProductBean.getTid());
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
        Log.d("TTT","aa"+goodsProductBean.toString());
        if(model.getFavorite().equals("y")){
            collectIv.setImageResource(R.drawable.icon_favorit);
        }else{
            collectIv.setImageResource(R.drawable.icon_favorit_normale);
        }

        shopAttri();
        initDetails(goodsProductBean.getContent());
        initComment(model);
    }
    //加载评论
    private void initComment(final GoodsDetailBean goodsDetailBean) {
        GoodsDetailBean.Comment comment = goodsDetailBean.getComment();
        if (comment != null && comment.getCount() > 0) {
            commentAmountTv.setText("全部评价(" + comment.getCount() + ")");
            GlideUtil.getInstance().LoadContextCircleBitmap(this, AppConstant.HEAD_IMG_URL + comment.getAvatar(), commentImgIv,
                    R.drawable.icon_head_normal, R.drawable.icon_head_normal);
            if (comment.getMobile() != null && !comment.getMobile().equals("")) {
                commentNameTv.setText(comment.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            }
            commentContentTv.setText(comment.getComment_detail());
            lookAllcommentTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Bundle bundle = new Bundle();
                    bundle.putInt("ID", goodsDetailBean.getGoods().getId());
                    startActivity(CommentYarnActivity.class, bundle);*/
                }
            });
            commentImgIv.setVisibility(View.VISIBLE);

        } else {
            commentAmountTv.setText("产品评价(0)");
            commentImgIv.setVisibility(View.GONE);
            commentContentTv.setText("暂无评论");
            lookAllcommentTv.setVisibility(View.GONE);
        }
    }
    //加载商品属性
    private void shopAttri(){
        dataBean=new GoodsAttrsBean();
        List<GoodsAttrsBean.AttributesBean> attributesBeanList=new ArrayList<>();
        List<GoodsAttrsBean.StockGoodsBean> stockGoodsBeanList=new ArrayList<>();
        if(goodsProductBean.getStyle()!=null&&goodsProductBean.getStyle().size()>0){
            List<GoodsStyleBean> styles=goodsProductBean.getStyle();
            GoodsAttrsBean.AttributesBean attributesBean=new GoodsAttrsBean().new AttributesBean();
            attributesBean.setTabID(0);
            attributesBean.setTabName("颜色分类:");
            List<String> colors=new ArrayList<>();
            List<String> sizes=new ArrayList<>();
            List<String> newSizes=new ArrayList<>();
            for(int i=0;i<styles.size();i++){
                colors.add(styles.get(i).getColor());
                String[] strArray = null;
                strArray = styles.get(i).getSize().split(",");
                for(String size:strArray){
                    sizes.add(size);
                }
            }
            attributesBean.setAttributesItem(colors);
            GoodsAttrsBean.AttributesBean attributesBean1=new GoodsAttrsBean().new AttributesBean();
            attributesBean1.setTabID(1);
            attributesBean1.setTabName("尺码:");
            newSizes.addAll(removeDuplicateWithOrder(sizes));
            attributesBean1.setAttributesItem(newSizes);
            attributesBeanList.add(attributesBean);
            attributesBeanList.add(attributesBean1);


            for(int i=0;i<styles.size();i++){

                GoodsAttrsBean.StockGoodsBean.GoodsInfoBean goodsInfoBean=new GoodsAttrsBean().new StockGoodsBean().new GoodsInfoBean();
                goodsInfoBean.setTabID(0);
                goodsInfoBean.setTabName("颜色分类:");
                goodsInfoBean.setTabValue(styles.get(i).getColor());
                String[] strArray = null;
                strArray = styles.get(i).getSize().split(",");
                for(String size:strArray){
                    List<GoodsAttrsBean.StockGoodsBean.GoodsInfoBean> goodsInfoBeans=new ArrayList<>();
                    GoodsAttrsBean.StockGoodsBean stockGoodsBean=new GoodsAttrsBean().new StockGoodsBean();
                    GoodsAttrsBean.StockGoodsBean.GoodsInfoBean goodsInfoBean1=new GoodsAttrsBean().new StockGoodsBean().new GoodsInfoBean();
                    goodsInfoBean1.setTabID(1);
                    goodsInfoBean1.setTabName("尺码:");
                    goodsInfoBean1.setTabValue(size);
                    goodsInfoBeans.add(goodsInfoBean);
                    goodsInfoBeans.add(goodsInfoBean1);
                    stockGoodsBean.setGoodsID(i);
                    stockGoodsBean.setGoodsInfo(goodsInfoBeans);
                    stockGoodsBeanList.add(stockGoodsBean);
                }

            }


        }
        dataBean.setAttributes(attributesBeanList);
        dataBean.setStockGoods(stockGoodsBeanList);
    }

    private void initDetails(String[] arr){
        if(arr==null){
            return;
        }
        for (int i = 0; i <arr.length; i++) {
            contentList.add(arr[i]);
        }
        rlProductDetail.setLayoutManager(new LinearLayoutManager(mContext));
        rlProductDetail.addItemDecoration(new RecyclerViewDriverLine(this,RecyclerViewDriverLine.VERTICAL_LIST));
        rlProductDetail.setHasFixedSize(true);
        rlProductDetail.setNestedScrollingEnabled(false);
        ProductDetailImgAdapter adapter = new ProductDetailImgAdapter(contentList);
        rlProductDetail.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicShowDialog dialog = new PicShowDialog(ProductDetailActivity.this, contentList, position);
                dialog.show();
            }
        });
    }
    @OnClick({R.id.ll_select_color_size,R.id.tv_shopping_cart,R.id.tv_shopping,R.id.ll_collection})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_select_color_size:
                    Dialog(goodsProductBean,0);
                    dialog_confirm_ll.setVisibility(View.GONE);
                    dialog_confirm2_ll.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_shopping:
                Dialog(goodsProductBean,1);
                dialog_confirm_ll.setVisibility(View.VISIBLE);
                dialog_confirm2_ll.setVisibility(View.GONE);
                break;
            case R.id.tv_shopping_cart:
                Dialog(goodsProductBean,2);
                dialog_confirm_ll.setVisibility(View.VISIBLE);
                dialog_confirm2_ll.setVisibility(View.GONE);
                break;
            case R.id.ll_collection:
                if(AppConstant.isLogin==false){
                    final List<String> mDatas1=new ArrayList<>();
                    mDatas1.add("登录");
                    mDatas1.add("注册");
                    NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(this)
                            .setBoolTitle(true)
                            .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                            .setTitleHeight(55)
                            .setItemHeight(45)
                            .setItemTextColor(R.color.blue)
                            .setItemTextSize(14)
                            .setItemWidth(0.95f)
                            .setCancleButtonText("取消")
                            .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                                @Override
                                public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                    switch (position){
                                        case 0:
                                            startActivity(LoginFragmentActivity.class);
                                            break;
                                        case 1:
                                            startActivity(RegisterActivity.class);
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            }).setTouchOutside(true)
                            .build();
                    dialog.setData(mDatas1);
                    dialog.show();
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(goodsProductBean.getId()));
                map.put("uid", String.valueOf(AppConstant.UID));
                mPresenter.collectProduct(map);
                break;
        }

    }
    //编辑购物车
    private GoodsAttrsBean dataBean;
    private EditShoppcartAdapter mAdapter;
    private LoadingDialog mLoadingDialog;
    private CustomDialog dialog;
    private int goods_nmb = 1;
    private TextView tv_item_minus_comm_detail,tv_item_number_comm_detail,dialog_selected_goods;
    private TextView shopCartTv,shopTv;
    private LinearLayout dialog_confirm_ll,dialog_confirm2_ll;
    private String goodColor,goodSize;
    //自定义dialog弹窗
    public void Dialog(GoodsProductBean goodBean, final int type){
        goods_nmb=1;
        if(dialog==null) {
            dialog = new CustomDialog(ProductDetailActivity.this, R.style.Dialog);//设置dialog的样式
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottomDialogAnim); // 添加动画
            dialog.setCanceledOnTouchOutside(true);

            WindowManager m = ProductDetailActivity.this.getWindowManager();
            Display d = m.getDefaultDisplay();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;     //dialog屏幕占比
            window.setAttributes(lp);

            RecyclerView dialog_listView = (RecyclerView) dialog.findViewById(R.id.dialog_listView);
            dialog_confirm_ll = (LinearLayout) dialog.findViewById(R.id.dialog_confirm_ll);
            dialog_confirm2_ll = (LinearLayout) dialog.findViewById(R.id.dialog_confirm2_ll);
            shopCartTv = (TextView) dialog.findViewById(R.id.tv_shopping_cart);
            shopTv = (TextView) dialog.findViewById(R.id.tv_shopping);
            RelativeLayout custom_dialog_close = (RelativeLayout) dialog.findViewById(R.id.custom_dialog_close);
            dialog_selected_goods = (TextView) dialog.findViewById(R.id.dialog_selected_goods);
            ImageView dialog_img = (ImageView) dialog.findViewById(R.id.dialog_img);
            TextView dialog_goods_price = (TextView) dialog.findViewById(R.id.dialog_goods_price);
            tv_item_minus_comm_detail = (TextView) dialog.findViewById(R.id.tv_item_minus_comm_detail);
            tv_item_number_comm_detail = (TextView) dialog.findViewById(R.id.tv_item_number_comm_detail);
            TextView tv_item_add_comm_detail = (TextView) dialog.findViewById(R.id.tv_item_add_comm_detail);
            custom_dialog_close.setOnClickListener(new ProductDetailActivity.DialogClick());
            tv_item_minus_comm_detail.setOnClickListener(new ProductDetailActivity.DialogClick());
            tv_item_add_comm_detail.setOnClickListener(new ProductDetailActivity.DialogClick());
            dialog_goods_price.setText("￥:" + goodBean.getPrice());
            dialog_listView.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter = new EditShoppcartAdapter(dataBean.getAttributes(), dataBean.getStockGoods());
            dialog_listView.setAdapter(mAdapter);
        }

        mAdapter.setSKUInterface(new SKUInterface() {
            @Override
            public void selectedAttribute(String[] attr) {
                goodColor=attr[0];
                goodSize=attr[1];
                String str1 = attr[0];
                String str2 = attr[1];
                if (!str1.equals("") && !str2.equals("")) {
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"' + " " + '"' + str2 + '"');
                    shopAttriTv.setText("已选:" + '"' + str1 + '"' + " " + '"' + str2 + '"');
                } else if (str1.equals("")&&!str2.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str2 + '"');
                    shopAttriTv.setText("已选:" + '"' + str2 + '"');
                }else if (str2.equals("")&&!str1.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"');
                    shopAttriTv.setText("已选:" + '"' + str1 + '"');
                }else{
                    dialog_selected_goods.setText("已选:");
                    shopAttriTv.setText("请选择尺码颜色");
                }

            }
            @Override
            public void uncheckAttribute(String[] attr) {
                goodColor=attr[0];
                goodSize=attr[1];
                String str1 = attr[0];
                String str2 = attr[1];
                if (!str1.equals("") && !str2.equals("")) {
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"' + " " + '"' + str2 + '"');
                    shopAttriTv.setText("已选:" + '"' + str1 + '"' + " " + '"' + str2 + '"');
                } else if (str1.equals("")&&!str2.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str2 + '"');
                    shopAttriTv.setText("已选:" + '"' + str2 + '"');
                }else if (str2.equals("")&&!str1.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"');
                    shopAttriTv.setText("已选:" + '"' + str1 + '"');
                }else{
                    dialog_selected_goods.setText("已选:");
                    shopAttriTv.setText("请选择尺码颜色");
                }
            }
        });


        dialog_confirm_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goodColor==null||goodColor.equals("")){
                    ToastUtil.showShortToast("请选择商品颜色");
                    return;
                }
                if(goodColor==null||goodSize.equals("")){
                    ToastUtil.showShortToast("请选择商品尺码");
                    return;
                }
                if(type==1){
                    String detail=goodColor+"#"+goodSize;
                    Bundle bundle = new Bundle();
                    bundle.putInt("ORDERTYPE",0);
                    bundle.putInt("gid",Integer.valueOf(goodsProductBean.getId()));
                    bundle.putString("detail", detail);
                    bundle.putInt("amount", goods_nmb);
                    startActivity(SureOrderActivity.class, bundle);
                }else{
                    mLoadingDialog=new LoadingDialog(ProductDetailActivity.this,"",false);
                    mLoadingDialog.show();
                    StringBuilder str=new StringBuilder();
                    str.append(goodColor).append("#");
                    str.append(goodSize).append("#").append(tv_item_number_comm_detail.getText().toString().trim());
                    Map<String,String> map=new HashMap<>();
                    map.put("gid",String.valueOf(goodsProductBean.getId()));
                    map.put("uid",String.valueOf(AppConstant.UID));
                    map.put("savetype","add");
                    map.put("amount",tv_item_number_comm_detail.getText().toString().trim());
                    map.put("detail",str.toString());
                    mPresenter.addShoppCart(map);
                }
                dialog.dismiss();
            }
        });
        shopCartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goodColor==null||goodColor.equals("")){
                    ToastUtil.showShortToast("请选择商品颜色");
                    return;
                }
                if(goodColor==null||goodSize.equals("")){
                    ToastUtil.showShortToast("请选择商品尺码");
                    return;
                }
                mLoadingDialog=new LoadingDialog(ProductDetailActivity.this,"",false);
                mLoadingDialog.show();
                StringBuilder str=new StringBuilder();
                str.append(goodColor).append("#");
                str.append(goodSize).append("#").append(tv_item_number_comm_detail.getText().toString().trim());
                Map<String,String> map=new HashMap<>();
                map.put("gid",String.valueOf(goodsProductBean.getId()));
                map.put("uid",String.valueOf(AppConstant.UID));
                map.put("savetype","add");
                map.put("amount",tv_item_number_comm_detail.getText().toString().trim());
                map.put("detail",str.toString());
                mPresenter.addShoppCart(map);
                dialog.dismiss();
            }
        });
        shopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goodColor==null||goodColor.equals("")){
                    ToastUtil.showShortToast("请选择商品颜色");
                    return;
                }
                if(goodColor==null||goodSize.equals("")){
                    ToastUtil.showShortToast("请选择商品尺码");
                    return;
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }
    //去掉List集合的重复值
    public  List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        return list;

    }
    public class DialogClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.custom_dialog_close:
                    dialog.dismiss();
                    break;
                case R.id.tv_item_minus_comm_detail:
                    //减少购买数量
                    goods_nmb--;
                    if (goods_nmb<1){
                        //Toast.makeText(getApplication(),"已是最低购买量",Toast.LENGTH_SHORT).show();
                    }else {
                        tv_item_number_comm_detail.setText(String.valueOf(goods_nmb));
                    }
                    break;
                case R.id.tv_item_add_comm_detail:
                    //增加购买数量
                    goods_nmb++;
                    tv_item_number_comm_detail.setText(String.valueOf(goods_nmb));
                    break;
            }
        }
    }
    @Override
    public void getSimilarRecommenSuccess(List<WeekListBean.WeekBean> model) {
        dataLists.clear();
        dataLists.addAll(model);
        mAdapter1.setNewData(dataLists);
    }

    @Override
    public void addShoppCartSuccess(String result) {
        if(mLoadingDialog!=null){
            mLoadingDialog.cancelDialog();
        }
        ToastUtil.showShortToast(result);
    }

    @Override
    public void collectProductSuccess(String msg) {
        collectIv.setImageResource(R.drawable.icon_favorit);
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void loadFail(String msg) {

    }
}
