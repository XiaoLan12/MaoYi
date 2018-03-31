package com.yizhisha.maoyi.ui.shoppcart.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.EditShoppcartAdapter;
import com.yizhisha.maoyi.adapter.ShoppCartAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.GoodsAttrsBean;
import com.yizhisha.maoyi.bean.json.GoodsBean;
import com.yizhisha.maoyi.bean.json.ShopcartBean;
import com.yizhisha.maoyi.bean.json.SingleShoppGoodBean;
import com.yizhisha.maoyi.bean.json.StoreBean;
import com.yizhisha.maoyi.common.dialog.CustomDialog;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.ui.home.activity.SureOrderActivity;
import com.yizhisha.maoyi.ui.shoppcart.contract.ShoppCartContract;
import com.yizhisha.maoyi.ui.shoppcart.presenter.ShoppCartPresenter;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
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
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/9/22.
 */

public class ShoppCartFragment extends BaseFragment<ShoppCartPresenter> implements ShoppCartContract.View, SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar mToobar;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.expandableListView)
    ExpandableListView expandableListView;
    @Bind(R.id.id_cb_select_all)
    CheckBox ivSelectAll;
    @Bind(R.id.btnSettle)
    TextView btnSettle;
    @Bind(R.id.tvCountMoney)
    TextView tvCountMoney;
    @Bind(R.id.rlBottomBar)
    RelativeLayout mRlBottomBar;
    @Bind(R.id.normal_shopbotton_ll)
    LinearLayout mLlNormalBottom;
    @Bind(R.id.deleteall_ll)
    LinearLayout mLlDeleteAllBottom;

    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;


    private ShoppCartAdapter adapter;
    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();

    //编辑购物车
    private GoodsAttrsBean dataBean;
    private EditShoppcartAdapter mAdapter;
    private LoadingDialog mLoadingDialog;
    private CustomDialog dialog;
    private int goods_nmb = 1;
    private TextView tv_item_minus_comm_detail,tv_item_number_comm_detail,dialog_selected_goods;
    private String goodColor,goodSize;

    //编辑的商品
    private int editGid;
    private int editSid;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppcart;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);
        }
    }
    @Override
    protected void initView() {
        mToobar.setRightButton1TextColor(R.color.white);
        mToobar.setRightButton1OnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mToobar.getRightButton1Text().equals("完成")){
                    changeFootShowDeleteView(false);
                }else{
                    changeFootShowDeleteView(true);
                }
            }
        });
        mLoadingView.loadSuccess();
        initAdapter();
        mPresenter.loadShoppCart(AppConstant.UID,true);
    }
    private void loadSingleShoopCart(Map<String,String> map){
        mPresenter.loadSingleShoppCart(map);

    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        adapter= new ShoppCartAdapter(activity, parentMapList, childMapList_list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "click：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        //选择全部
        ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    adapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });
        //结算
        btnSettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder str=new StringBuilder();
                String sid = "";
                for (int i = 0; i < parentMapList.size(); i++) {
                    StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
                    List<Map<String, Object>> childMapList = childMapList_list.get(i);
                    for (int j = 0; j < childMapList.size(); j++) {
                        GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                        if(goodsBean.isChecked()){
                            str.append(goodsBean.getSid()).append(",");
                        }
                    }
                }
                if (str.length() > 0) {
                    sid = str.substring(0, str.length() - 1);
                }
                if(str.length()==0){
                    ToastUtil.showShortToast("请选择要购买的商品");
                    return;
                }
                Log.d("TTT","他妈妈"+sid);
                Bundle bundle = new Bundle();
                bundle.putString("sid", sid);
                bundle.putInt("ORDERTYPE",1);
                startActivity(SureOrderActivity.class, bundle);
            }
        });
        adapter.setOnGoodsCheckedChangeListener(new ShoppCartAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice) {
                tvCountMoney.setText(String.format(getString(R.string.total)+totalPrice));
                //id_tv_totalCount_jiesuan.setText(String.format(getString(R.string.jiesuan), totalCount+""));
            }
        });

        adapter.setOnAllCheckedBoxNeedChangeListener(new ShoppCartAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                ivSelectAll.setChecked(allParentIsChecked);
            }
        });

        adapter.setOnCheckHasGoodsListener(new ShoppCartAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                if(isHasGoods){
                    mLoadingView.loadSuccess();
                    mToobar.showRightButton();
                    changeFootShowDeleteView(false);
                    mRlBottomBar.setVisibility(View.VISIBLE);
                }else{
                    mToobar.hideRightButton();
                    changeFootShowDeleteView(true);
                    mLoadingView.loadSuccess(true, R.drawable.ic_launcher,"您的购物车中还没有商品，请您先逛逛!");
                    mRlBottomBar.setVisibility(View.GONE);
                }
                //setupViewsShow(isHasGoods);
            }
        });

        adapter.setOnGoodsEditChangeListenr(new ShoppCartAdapter.OnGoodsEditChangeListenr() {
            @Override
            public void onEditChange(GoodsBean goodsBean) {
                editGid=goodsBean.getGid();
                editSid=goodsBean.getSid();
                Map<String,String> map=new HashMap<String, String>();
                map.put("gid",String.valueOf(goodsBean.getGid()));
                map.put("sid",String.valueOf(goodsBean.getSid()));
                map.put("uid",String.valueOf(AppConstant.UID));
                loadSingleShoopCart(map);
            }
        });
        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(expandableListView != null && expandableListView.getChildCount() > 0){
                    boolean firstItemVisible = expandableListView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = expandableListView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mSwipeRefreshLayout.setEnabled(enable);
            }
        });
    }
    public void changeFootShowDeleteView(boolean showDeleteView) {

        if (showDeleteView) {
            mToobar.setRightButton1Text("完成");
            mToobar.setRightButton1Icon(null);
            mLlNormalBottom.setVisibility(View.GONE);
            mLlDeleteAllBottom.setVisibility(View.VISIBLE);
        } else {
            mToobar.setRightButton1Text("");
            mToobar.setRightButton1Icon(RescourseUtil.getDrawable(R.drawable.icon_ratingbar));
            mLlNormalBottom.setVisibility(View.VISIBLE);
            mLlDeleteAllBottom.setVisibility(View.GONE);
        }
    }
    @Override
    public void onRefresh() {
        mPresenter.loadShoppCart(AppConstant.UID,false);
    }
    @Override
    public void loadSuccess(List<ShopcartBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();
        for (int i = 0; i < data.size(); i++) {
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            StoreBean store=new StoreBean();
            store.setMzw_uid(data.get(i).getMzw_uid());
            store.setCompany(data.get(i).getCompany());
            store.setChecked(false);
            store.setEditing(0);
            parentMap.put("parentName",store);
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            List<ShopcartBean.Goods> goods=data.get(i).getGoods();
            for (int j = 0; j < goods.size(); j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();

                GoodsBean goodsBean=new GoodsBean();
                goodsBean.setGid(goods.get(j).getGid());
                goodsBean.setSid(goods.get(j).getSid());
                goodsBean.setTitle(goods.get(j).getTitle());
                goodsBean.setPrice(goods.get(j).getPrice());
                goodsBean.setLitpic(goods.get(j).getLitpic());
                goodsBean.setAmount(goods.get(j).getAmount());
                goodsBean.setDetail(goods.get(j).getDetail());
                goodsBean.setChecked(false);
                goodsBean.setEditing(0);
                childMap.put("childName", goodsBean);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }
        //需要展开分组，才会刷新childView
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        if (parentMapList != null && parentMapList.size() > 0) {

            mToobar.showRightButton();
            mRlBottomBar.setVisibility(View.VISIBLE);
        } else {
            mToobar.hideRightButton();
            mRlBottomBar.setVisibility(View.GONE);

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadSingleSuccess(SingleShoppGoodBean data) {
        dataBean=new GoodsAttrsBean();
        List<GoodsAttrsBean.AttributesBean> attributesBeanList=new ArrayList<>();
        List<GoodsAttrsBean.StockGoodsBean> stockGoodsBeanList=new ArrayList<>();
        if(data.getStyle()!=null&&data.getStyle().size()>0){
            List<SingleShoppGoodBean.Style> styles=data.getStyle();
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
            Dialog(data);
        }
    }

    @Override
    public void editShoppCartSuccess(String result) {
        if(mLoadingDialog!=null){
            mLoadingDialog.cancelDialog();
        }
        onRefresh();
        ToastUtil.showShortToast(result);
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
    @Override
    public void deleteShoppCart(String msg) {
        adapter.removeGoods();
        ToastUtil.showShortToast(msg);
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
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();
        mToobar.hideRightButton();
        mRlBottomBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        mLoadingView.loadSuccess(true, R.drawable.ic_launcher,"您的购物车中还没有商品，请您先逛逛!");
    }

    @Override
    public void loadFail(int code, String msg) {
        if(code==0){
            if(mLoadingDialog!=null){
                mLoadingDialog.cancelDialog();
            }
            ToastUtil.showShortToast(msg);
            return;
        }
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();
        mToobar.hideRightButton();
        mRlBottomBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                mPresenter.loadShoppCart(AppConstant.UID,true);
            }
        });
    }
    @OnClick({R.id.deleteall_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deleteall_btn:
                StringBuilder str=new StringBuilder();
                for (int i = 0; i < parentMapList.size(); i++) {
                    List<Map<String, Object>> childMapList = childMapList_list.get(i);
                    for (int j = 0; j < childMapList.size(); j++) {
                        GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                        if(goodsBean.isChecked()){
                            str.append(goodsBean.getSid());
                            str.append(",");
                        }
                    }
                }
                if(str.length()==0){
                    ToastUtil.showShortToast("请选择要删除的商品");
                    return;
                }
                final String sid = str.substring(0, str.length() - 1);
                new NormalAlertDialog.Builder(activity)
                        .setBoolTitle(false)
                        .setContentText("确定删除选中的商品吗?")
                        .setContentTextColor(R.color.blue)
                        .setLeftText("取消")
                        .setLeftTextColor(R.color.blue)
                        .setRightText("确认")
                        .setRightTextColor(R.color.blue)
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {

                                Map<String,String> map=new HashMap<>();
                                map.put("uid",String.valueOf(AppConstant.UID));
                                map.put("sid", sid);
                                mPresenter.deleteShoppCart(map);
                                dialog.dismiss();

                            }
                        }).build().show();

                break;

        }
    }


    //自定义dialog弹窗
    public void Dialog(SingleShoppGoodBean goodBean){
        goods_nmb=1;
        dialog = new CustomDialog(activity,R.style.Dialog);//设置dialog的样式
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottomDialogAnim); // 添加动画
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        WindowManager m = activity.getWindowManager();
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
        String detail=goodBean.getShopcart().getDetail();
        if(detail!=null&&!detail.equals("")){
            goodColor=detail.substring(0,detail.indexOf("#"));
            goodSize=detail.substring(detail.indexOf("#")+1, detail.lastIndexOf("#"));
            dialog_selected_goods.setText("已选:"+'"'+goodColor+'"'+" "+'"'+goodSize+'"');
        }else{
            dialog_selected_goods.setText("已选:");
        }

        dialog_goods_price.setText("￥:"+goodBean.getPrice());
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
        custom_dialog_close.setOnClickListener(new DialogClick());
        tv_item_minus_comm_detail.setOnClickListener(new DialogClick());
        tv_item_add_comm_detail.setOnClickListener(new DialogClick());

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
                mLoadingDialog=new LoadingDialog(activity,"正在修改购物车...",false);
                mLoadingDialog.show();
                StringBuilder str=new StringBuilder();
                str.append(goodColor).append("#");
                str.append(goodSize).append("#").append(tv_item_number_comm_detail.getText().toString().trim());
                Map<String,String> map=new HashMap<>();
                map.put("gid",String.valueOf(editGid));
                map.put("sid",String.valueOf(editSid));
                map.put("uid",String.valueOf(AppConstant.UID));
                map.put("savetype","edit");
                map.put("amount",tv_item_number_comm_detail.getText().toString().trim());
                map.put("detail",str.toString());
                mPresenter.editShoppCart(map);
                dialog.dismiss();
            }
        });
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
}
