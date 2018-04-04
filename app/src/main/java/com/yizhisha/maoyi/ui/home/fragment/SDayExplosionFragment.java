package com.yizhisha.maoyi.ui.home.fragment;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.GoodsScreesBean;
import com.yizhisha.maoyi.bean.json.GoodsScreesContentBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;
import com.yizhisha.maoyi.common.popupwindow.GoodsScressPopuwindow;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.ui.home.contract.SDayExplosionContract;
import com.yizhisha.maoyi.ui.home.presenter.SDayExplosionPresenter;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class SDayExplosionFragment extends BaseFragment<SDayExplosionPresenter> implements SDayExplosionContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.img_banner)
    ImageView img_banner;
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
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.ll_head)
     LinearLayout ll_head;

    //查询条件
    private int sort=0;//排序
    private String cid="";//商品类型
    private String price="";//价格
    private List<SortedListBean.SortedsBean> sortedsBeanList=new ArrayList<>();

    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean.WeekBean> dataLists = new ArrayList<>();
    private GoodsScressPopuwindow popuwindow;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sday_explosion;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new SDayExplosionAdapter(dataLists);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
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

        load(true);
        mPresenter.getWeekTop();
        mPresenter.getSortedList();
    }
    private void load(boolean isShowLoad){

        Map<String,String> map=new HashMap<>();
        if(!cid.equals("")){
            map.put("cid", cid);
        }
        if(!price.equals("")){
            map.put("price", price);
        }
        if(sort!=0){
            map.put("order", sort+"");
        }
        mPresenter.getWeekList(map,isShowLoad);
    }
    private void initHeadView() {
        tv_select_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(popuwindow==null){
                    popuwindow=new GoodsScressPopuwindow(mContext,true);
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
                if(sort==1){
                    sort=0;
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
                if(sort==0||sort==1){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    sort=4;
                }else if(sort==4){
                    img_select_price.setImageResource(R.drawable.price_select_down);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    sort=3;
                }else if(sort==3){
                    img_select_price.setImageResource(R.drawable.price_select_up);
                    tv_select_price.setTextColor(RescourseUtil.getColor(R.color.red1));
                    sort=4;
                }
                load(false);

            }
        });
    }
//         所有帅选按钮变灰色
    private void changeColor(){
        tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
        tv_select_price.setTextColor(RescourseUtil.getColor(R.color.black));
        tv_select_select.setTextColor(RescourseUtil.getColor(R.color.black));
    }

    @Override
    public void getWeekToprSuccess(List<WeekTopListBean.WeekTopBean> model) {
        if(model!=null)
        GlideUtil.getInstance().LoadSupportv4FragmentBitmap(SDayExplosionFragment.this,AppConstant.BANNER_IMG_URL+model.get(0).getSpc_litpic(),img_banner,0,0,null);

    }

    @Override
    public void getWeekListtSuccess(List<WeekListBean.WeekBean> model) {
        dataLists=model;
        mAdapter.setNewData(dataLists);
        ll_head.setVisibility(View.VISIBLE);
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
    public void loadFail(int code, String msg) {
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
}
