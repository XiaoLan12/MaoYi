package com.yizhisha.maoyi.ui.home.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.EditShoppcartAdapter;
import com.yizhisha.maoyi.adapter.ProductDetailImgAdapter;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.base.rx.RxBus;
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
import com.yizhisha.maoyi.common.popupwindow.SelectPopupWindow;
import com.yizhisha.maoyi.event.FinishEvent;
import com.yizhisha.maoyi.event.UpdateShopCartEvent;
import com.yizhisha.maoyi.ui.home.contract.ProductDetailContract;
import com.yizhisha.maoyi.ui.home.jc.DemoFragment;
import com.yizhisha.maoyi.ui.home.jc.DemoFragment1;
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
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;

/**
 * Created by Administrator on 2017/11/16.
 */
public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View, ViewPager.OnPageChangeListener {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

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
    @Bind(R.id.ll_dot)
    LinearLayout ll_dot;
    @Bind(R.id.ll_dot1)
    LinearLayout ll_dot1;
    @Bind(R.id.ll_dot2)
    LinearLayout ll_dot2;
    @Bind(R.id.linearlayout)
    LinearLayout parentLl;


    private SDayExplosionAdapter mAdapter1;
    private List<WeekListBean.WeekBean> dataLists = new ArrayList<>();

    GoodsProductBean goodsProductBean;
    GoodsDetailBean goodsDetailBean;
    private int mGid;
    //详情
    private List<String> contentList = new ArrayList<>();


    List<DemoFragment1> fragmentList = new ArrayList<>();

    private Subscription subscription;

    private IWXAPI api;
    // 自定义PopupWindow
    private SelectPopupWindow feedSelectPopupWindow;

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
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        api = WXAPIFactory.createWXAPI(this, AppConstant.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        api.registerApp(AppConstant.WEIXIN_APP_ID);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mGid = bundle.getInt("gid");
        }
        rlTuijian.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter1 = new SDayExplosionAdapter(dataLists);
        rlTuijian.setAdapter(mAdapter1);

        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("gid", dataLists.get(position).getId());
                startActivity(ProductDetailActivity.class,bundle);
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("gid", String.valueOf(mGid));
        map.put("uid", String.valueOf(3));
        mPresenter.getGoodsDetail(map);
        event();

        // 为ImageSlideshow设置数据
//        imageSlideshow.setDotSpace(12);
//        imageSlideshow.setDotSize(12);
//        imageSlideshow.setDelay(3000);
//        imageSlideshow.commit();

    }

    private void event() {
        subscription = RxBus.$().toObservable(FinishEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FinishEvent>() {
                    @Override
                    public void call(FinishEvent event) {
                        finish_Activity(ProductDetailActivity.this);
                    }
                });
    }

    @Override
    public void getGoodsDetailSuccess(GoodsDetailBean model) {
        if (model.getStatus().equals("y")) {
            goodsDetailBean = model;
            goodsProductBean = model.getGoods();

            List<BannerResult> bannerResultsList = new ArrayList<>();
            BannerResult bannerResult = new BannerResult();
            bannerResult.setUrl(AppConstant.PRUDUCT_IMG_URL + goodsProductBean.getLitpic());
            bannerResultsList.add(bannerResult);

            if (!goodsProductBean.getVideo().equals("")) {
                String path = goodsProductBean.getVideo();
                if (!path.contains("http:")) {
                    path = AppConstant.PRODUCT_VIDEO + path;
                }
                BannerResult bannerResult1 = new BannerResult();
                bannerResult1.setUrl(path);
                bannerResultsList.add(bannerResult1);
                fragmentList.add(new DemoFragment1().setIndex(path, 0));

                ll_dot.setVisibility(View.VISIBLE);
                ll_dot1.setBackgroundResource(R.drawable.dot_selected);
            }


//        viewPager.setAdapter(new Myvpadapter( ProductDetailActivity.this,bannerResultsList));

            fragmentList.add(new DemoFragment1().setIndex(AppConstant.PRUDUCT_IMG_URL + goodsProductBean.getLitpic(), 1));

            MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            viewPager.setAdapter(myAdapter);
            viewPager.setOnPageChangeListener(this);


//        imageSlideshow.setImageTitleBeanList(bannerResultsList);
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
            String devi_elastic = goodsProductBean.getDevi_thickness();
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
            Log.d("TTT", "aa" + goodsProductBean.toString());
            if (model.getFavorite().equals("y")) {
                collectIv.setImageResource(R.drawable.icon_favorit);
            } else {
                collectIv.setImageResource(R.drawable.icon_favorit_normale);
            }

            shopAttri();
            initDetails(goodsProductBean.getContent());
            initComment(model);
        } else {
            ToastUtil.showShortToast(model.getInfo());
        }

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
    private void shopAttri() {
        dataBean = new GoodsAttrsBean();
        List<GoodsAttrsBean.AttributesBean> attributesBeanList = new ArrayList<>();
        List<GoodsAttrsBean.StockGoodsBean> stockGoodsBeanList = new ArrayList<>();
        if (goodsProductBean.getStyle() != null && goodsProductBean.getStyle().size() > 0) {
            List<GoodsStyleBean> styles = goodsProductBean.getStyle();
            GoodsAttrsBean.AttributesBean attributesBean = new GoodsAttrsBean().new AttributesBean();
            attributesBean.setTabID(0);
            attributesBean.setTabName("颜色分类:");
            List<String> colors = new ArrayList<>();
            List<String> sizes = new ArrayList<>();
            List<String> newSizes = new ArrayList<>();
            for (int i = 0; i < styles.size(); i++) {
                colors.add(styles.get(i).getColor());
                String[] strArray = null;
                strArray = styles.get(i).getSize().split(",");
                for (String size : strArray) {
                    sizes.add(size);
                }
            }
            attributesBean.setAttributesItem(colors);
            GoodsAttrsBean.AttributesBean attributesBean1 = new GoodsAttrsBean().new AttributesBean();
            attributesBean1.setTabID(1);
            attributesBean1.setTabName("尺码:");
            newSizes.addAll(removeDuplicateWithOrder(sizes));
            attributesBean1.setAttributesItem(newSizes);
            attributesBeanList.add(attributesBean);
            attributesBeanList.add(attributesBean1);


            for (int i = 0; i < styles.size(); i++) {

                GoodsAttrsBean.StockGoodsBean.GoodsInfoBean goodsInfoBean = new GoodsAttrsBean().new StockGoodsBean().new GoodsInfoBean();
                goodsInfoBean.setTabID(0);
                goodsInfoBean.setTabName("颜色分类:");
                goodsInfoBean.setTabValue(styles.get(i).getColor());
                String[] strArray = null;
                strArray = styles.get(i).getSize().split(",");
                for (String size : strArray) {
                    List<GoodsAttrsBean.StockGoodsBean.GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
                    GoodsAttrsBean.StockGoodsBean stockGoodsBean = new GoodsAttrsBean().new StockGoodsBean();
                    GoodsAttrsBean.StockGoodsBean.GoodsInfoBean goodsInfoBean1 = new GoodsAttrsBean().new StockGoodsBean().new GoodsInfoBean();
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

    private void initDetails(String[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            contentList.add(arr[i]);
        }
        rlProductDetail.setLayoutManager(new LinearLayoutManager(mContext));
        rlProductDetail.addItemDecoration(new RecyclerViewDriverLine(this, RecyclerViewDriverLine.VERTICAL_LIST));
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

    /*  if(AppConstant.isLogin==false){
         if(dialog==null){
             showLoginDialog();
         }
         dialog.show();
         return;
     }*/
    //判断是否登陆
    private NormalSelectionDialog dialog1;

    private void showLoginDialog() {
        final List<String> mDatas1 = new ArrayList<>();
        mDatas1.add("登录");
        mDatas1.add("注册");
        dialog1 = new NormalSelectionDialog.Builder(ProductDetailActivity.this)
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
                        switch (position) {
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
        dialog1.setData(mDatas1);
    }


    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    @OnClick({R.id.ll_select_color_size,R.id.tv_shopping_cart,R.id.tv_shopping,R.id.ll_collection,R.id.ll_shopping_cart,
    R.id.share_iv})
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
                if(AppConstant.isLogin==false) {
                    if (dialog1 == null) {
                        showLoginDialog();
                    }
                    dialog1.show();
                    return;
                }

                Dialog(goodsProductBean,1);
                dialog_confirm_ll.setVisibility(View.VISIBLE);
                dialog_confirm2_ll.setVisibility(View.GONE);
                break;
            case R.id.tv_shopping_cart:

                if(AppConstant.isLogin==false) {
                    if (dialog1 == null) {
                        showLoginDialog();
                    }
                    dialog1.show();
                    return;
                }

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
            case R.id.ll_shopping_cart:
                if(AppConstant.isLogin==false) {
                    if (dialog1 == null) {
                        showLoginDialog();
                    }
                    dialog1.show();
                    return;
                }

                startActivity(ShopCartActivity.class);
                break;
            case R.id.share_iv:
                if(feedSelectPopupWindow==null){
                    feedSelectPopupWindow = new SelectPopupWindow(this, selectItemsOnClick);
                    feedSelectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            backgroundAlpha(1f);
                        }
                    });
                }
               ;
                // 设置popupWindow显示的位置
                // 此时设在界面底部并且水平居中
                feedSelectPopupWindow.showAtLocation(parentLl,
                        Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                // 当popupWindow 出现的时候 屏幕的透明度  ，设为0.5 即半透明 灰色效果
                backgroundAlpha(0.5f);
                // 设置popupWindow取消的点击事件，即popupWindow消失后，屏幕的透明度，全透明，就回复原状态

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
            String url=goodBean.getLitpic();
            String newUrl;
            if(url.length()>0&&url.startsWith("http://")){
                newUrl=url;
            }else{
                newUrl=AppConstant.PRUDUCT_IMG_URL+url;
            }
            GlideUtil.getInstance().LoadContextBitmap(mContext, newUrl,
                    dialog_img,GlideUtil.LOAD_BITMAP);
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
    //viewpager标记

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            ll_dot1.setBackgroundResource(R.drawable.dot_selected);
            ll_dot2.setBackgroundResource(R.drawable.dot_unselected);
        }else {
            ll_dot2.setBackgroundResource(R.drawable.dot_selected);
            ll_dot1.setBackgroundResource(R.drawable.dot_unselected);
        }
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
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
        RxBus.$().postEvent(new UpdateShopCartEvent());
        ToastUtil.showShortToast(result);
    }

    @Override
    public void collectProductSuccess(String msg) {
        collectIv.setImageResource(R.drawable.icon_favorit);
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    // popupWindow 点击事件监听
    private View.OnClickListener selectItemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                //根据popupWindow 布局文件中的id 来执行相应的点击事件
                case R.id.fp_linear_sharetoWeixin:


                   shareUrl(0,ProductDetailActivity.this,"com.yizhisha.maoyi",goodsProductBean.getTitle(),"");
                    break;
                case R.id.fp_linear_sharetoquan:
                    shareUrl(1,ProductDetailActivity.this,"https://open.weixin.qq.com",goodsProductBean.getTitle(),"");
                    break;

            }
            //每次点击popupWindow中的任意按钮，记得关闭此popupWindow，
            feedSelectPopupWindow.dismiss();
        }
    };

   /* private boolean share(String title, int scene) {

        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = title;
        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;   // 发送文本类型的消息时，title字段不起作用
        // msg.title = "Will be ignored";
        msg.description = title;   // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis()); // transaction字段用于唯一标识一个请求
        req.message = msg;   // 分享或收藏的目标场景，通过修改scene场景值实现。
        // 发送到聊天界面 —— WXSceneSession
        // 发送到朋友圈 —— WXSceneTimeline
        // 添加到微信收藏 —— WXSceneFavorite
        req.scene = scene==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        // 调用api接口发送数据到微信
        return api.sendReq(req);
    }*/

    //flag用来判断是分享到微信好友还是分享到微信朋友圈，
    //0代表分享到微信好友，1代表分享到朋友圈
    private boolean shareUrl(int flag,Context context,String url,String title,String descroption){
        //初始化一个WXWebpageObject填写url
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = url;
        //用WXWebpageObject对象初始化一个WXMediaMessage，天下标题，描述
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = descroption;
        //这块需要注意，图片的像素千万不要太大，不然的话会调不起来微信分享，
        //我在做的时候和我们这的UIMM说随便给我一张图，她给了我一张1024*1024的图片
        //当时也不知道什么原因，后来在我的机智之下换了一张像素小一点的图片好了！
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        return api.sendReq(req);
    }

}
