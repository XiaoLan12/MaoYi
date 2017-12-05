package com.yizhisha.maoyi.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
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
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.GoodsAttrsBean;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.GoodsProductBean;
import com.yizhisha.maoyi.bean.json.GoodsStyleBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.common.dialog.CustomDialog;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.ui.home.contract.ProductDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.ProductDetailPresenter;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.SKUInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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


    private SDayExplosionAdapter mAdapter1;
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

        mAdapter1 = new SDayExplosionAdapter(dataLists);
        rlTuijian.setAdapter(mAdapter);
        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
                    dataBean.setAttributes(attributesBeanList);
                    dataBean.setStockGoods(stockGoodsBeanList);
                    Dialog(goodsProductBean);
                }
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
    private String goodColor,goodSize;
    //自定义dialog弹窗
    public void Dialog(GoodsProductBean goodBean){
        goods_nmb=1;
        dialog = new CustomDialog(ProductDetailActivity.this,R.style.Dialog);//设置dialog的样式
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottomDialogAnim); // 添加动画
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        WindowManager m = ProductDetailActivity.this.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;     //dialog屏幕占比
        window.setAttributes(lp);

        RecyclerView dialog_listView = (RecyclerView) dialog.findViewById(R.id.dialog_listView);
        LinearLayout dialog_confirm_ll = (LinearLayout) dialog.findViewById(R.id.dialog_confirm_ll);
        RelativeLayout custom_dialog_close = (RelativeLayout) dialog.findViewById(R.id.custom_dialog_close);
        dialog_selected_goods = (TextView) dialog.findViewById(R.id.dialog_selected_goods);
        ImageView dialog_img = (ImageView) dialog.findViewById(R.id.dialog_img);
        TextView dialog_goods_price = (TextView) dialog.findViewById(R.id.dialog_goods_price);
        tv_item_minus_comm_detail = (TextView) dialog.findViewById(R.id.tv_item_minus_comm_detail);
        tv_item_number_comm_detail = (TextView) dialog.findViewById(R.id.tv_item_number_comm_detail);
        TextView tv_item_add_comm_detail = (TextView) dialog.findViewById(R.id.tv_item_add_comm_detail);
       /* String detail=goodBean.getShopcart().getDetail();
        if(detail!=null&&!detail.equals("")){
            goodColor=detail.substring(0,detail.indexOf("#"));
            goodSize=detail.substring(detail.indexOf("#")+1, detail.lastIndexOf("#"));
            dialog_selected_goods.setText("已选:"+'"'+goodColor+'"'+" "+'"'+goodSize+'"');
        }else{
            dialog_selected_goods.setText("已选:");
        }*/

        dialog_goods_price.setText("￥:"+goodBean.getPrice());
        dialog_listView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new EditShoppcartAdapter(dataBean.getAttributes(), dataBean.getStockGoods());
        dialog_listView.setAdapter(mAdapter);
        mAdapter.setSKUInterface(new SKUInterface() {
            @Override
            public void selectedAttribute(String[] attr) {
                goodColor=attr[0];
                goodSize=attr[1];
                String str1 = attr[0];
                String str2 = attr[1];
                if (!str1.equals("") && !str2.equals("")) {
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"' + " " + '"' + str2 + '"');
                } else if (str1.equals("")&&!str2.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str2 + '"');
                }else if (str2.equals("")&&!str1.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"');
                }else{
                    dialog_selected_goods.setText("已选:");
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
                } else if (str1.equals("")&&!str2.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str2 + '"');
                }else if (str2.equals("")&&!str1.equals("")){
                    dialog_selected_goods.setText("已选:" + '"' + str1 + '"');
                }else{
                    dialog_selected_goods.setText("已选:");
                }
            }
        });
        custom_dialog_close.setOnClickListener(new ProductDetailActivity.DialogClick());
        tv_item_minus_comm_detail.setOnClickListener(new ProductDetailActivity.DialogClick());
        tv_item_add_comm_detail.setOnClickListener(new ProductDetailActivity.DialogClick());

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

                dialog.dismiss();
            }
        });
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
    public void getSimilarRecommenSuccess(SimilarRecommenBean model) {
        dataLists = model.getGoods();
        mAdapter1.setNewData(dataLists);
    }

    @Override
    public void loadFail(String msg) {

    }
}
