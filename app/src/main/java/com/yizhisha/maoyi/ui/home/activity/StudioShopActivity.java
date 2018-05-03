package com.yizhisha.maoyi.ui.home.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.common.popupwindow.GoodsScressPopuwindow;
import com.yizhisha.maoyi.ui.home.contract.StudioShopContract;
import com.yizhisha.maoyi.ui.home.presenter.StudioShopPresenter;
import com.yizhisha.maoyi.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.maoyi.ui.login.activity.RegisterActivity;
import com.yizhisha.maoyi.ui.me.activity.AddAddressActivity;
import com.yizhisha.maoyi.ui.me.activity.AddCommentActivity;
import com.yizhisha.maoyi.ui.me.activity.MyAddressActivity;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import org.w3c.dom.Text;

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

    @Bind(R.id.head_iv)
    ImageView headIv;


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @Bind(R.id.loadingView)
    CommonLoadingView loadingView;
    @Bind(R.id.back_iv)
    ImageView back_iv;
    @Bind(R.id.tv_focus)
    TextView tv_focus;
    @Bind(R.id.search_selectyarn_et)
    EditText searchEt;
    @Bind(R.id.search_iv)
    ImageView searchIv;


    private StudioShopAdapter mAdapter;

    private List<StudioShopBean.Goods> dataLists=new ArrayList<>();
    private StudioShopBean studioShopBean;

    private int mWid;//工作室ID；
    private boolean isFocus=false;


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
    private String price="";
    private int sort=0;
    private String search="";//商品类型
    private String mKey="";//搜索词
    private List<SortedListBean.SortedsBean> sortedsBeanList=new ArrayList<>();

    private GoodsScressPopuwindow popuwindow;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_studio_shop;
    }
    @Override
    protected void initToolBar() {
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(StudioShopActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mWid=bundle.getInt("wid");
        }
        initAdapter();
        load(true);
        mPresenter.getSortedList();
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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("gid", Integer.parseInt(dataLists.get(position).getId()));
                startActivity(ProductDetailActivity.class,bundle);
            }
        });
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    searchIv.setVisibility(View.VISIBLE);
                }else{
                    mKey="";
                    load(false);
                    searchIv.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("wid",String.valueOf(mWid));
        if(AppConstant.isLogin) {
            map.put("uid", String.valueOf(AppConstant.UID));
        }
        if(!price.equals("")){
            map.put("price", price);
        }
        if(!search.equals("")){
            map.put("search", search);
        }
        if(sort!=0){
            map.put("order", sort+"");
        }

        if(!mKey.equals("")){
            map.put("key", mKey);
        }
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
            if(favorite.equals("y")){
                isFocus=true;
                tv_focus.setText("已关注");
                tv_focus.setTextColor(getResources().getColor(R.color.common_color));
                tv_focus.setBackgroundResource(R.drawable.shape_gray_selector);

            }else{
                isFocus=false;
                tv_focus.setBackgroundResource(R.drawable.shape_pink);
                tv_focus.setTextColor(getResources().getColor(R.color.white));
                tv_focus.setText("关注");
            }
        }
        if(model.getWorkshop()!=null){
            StudioShopBean.Workshop workshop=model.getWorkshop();
            GlideUtil.getInstance().LoadContextBitmap(this,workshop.getBackground(),workImgIv,GlideUtil.LOAD_BITMAP);
            GlideUtil.getInstance().LoadContextBitmap(this,workshop.getAvatar(),headIv,GlideUtil.LOAD_BITMAP);
            nameTv.setText(workshop.getWorkshop());
        }
        if(model.getGoods().size()>0){
            dataLists.clear();
            dataLists.addAll(model.getGoods());
            mAdapter.setNewData(dataLists);
        }else{
            showEmpty();
        }


    }
    private void addHeadView() {
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
    public void focusWorkSuccess(String msg) {
        if(isFocus){
            tv_focus.setText("已关注");
            tv_focus.setBackgroundResource(R.drawable.shape_gray_selector);
            tv_focus.setTextColor(getResources().getColor(R.color.common_color));

        }else{
            tv_focus.setText("关注");
            tv_focus.setBackgroundResource(R.drawable.shape_pink);
            tv_focus.setTextColor(getResources().getColor(R.color.white));
        }
        new NormalAlertDialog.Builder(this)
                .setBoolTitle(true)
                .setTitleText("温馨提示")
                .setContentText(msg)
                .setSingleModel(true)
                .setSingleText("确认")
                .setSingleTextColor(R.color.blue)
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                }).build().show();
    }

    @Override
    public void getSortedListSuccess(List<SortedListBean.SortedsBean> model) {
        sortedsBeanList=model;
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

                load(true);
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
        load(false);
    }

    @OnClick({R.id.tv_focus,R.id.search_iv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_focus:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
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
            case R.id.search_iv:
                String text=searchEt.getText().toString().trim();
                if(text.length()==0){
                    ToastUtil.showShortToast("请输入关键字");
                }
                String key=searchEt.getText().toString().trim();
                mKey=key.replaceAll(" +"," ");
                load(false);
                break;

        }
    }
    //判断是否登陆
    private NormalSelectionDialog dialog;
    private void showLoginDialog(){
        final List<String> mDatas1=new ArrayList<>();
        mDatas1.add("登录");
        mDatas1.add("注册");
        dialog=new NormalSelectionDialog.Builder(this)
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
    }

}
