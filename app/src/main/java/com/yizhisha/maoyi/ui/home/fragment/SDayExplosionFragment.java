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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class SDayExplosionFragment extends BaseFragment<SDayExplosionPresenter> implements SDayExplosionContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private ImageView img_banner;
    private ImageView img_select_price;
    private TextView tv_select_select;
    private TextView tv_select_price;
    private TextView tv_select_xiaoliang;
    private LinearLayout ll_select_price;
    private int price=0;
    private int xiaoliang=0;
            private List<SortedListBean.SortedsBean> sortedsBeanList=new ArrayList<>();

    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean.WeekBean> dataLists = new ArrayList<>();
    private     GoodsScressPopuwindow popuwindow;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sday_explosion;
    }

    @Override
    protected void initView() {
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
        Map<String,String> map=new HashMap<>();
        mPresenter.getWeekList(map);
        mPresenter.getWeekTop();
        mPresenter.getSortedList();
    }
    private void addHeadView() {
        View view=getActivity().getLayoutInflater().inflate(R.layout.headview_sday_explosion, (ViewGroup) mRecyclerView.getParent(), false);
        img_banner=(ImageView)view.findViewById(R.id.img_banner);
        img_select_price=(ImageView)view.findViewById(R.id.img_select_price);
          tv_select_select=(TextView)view.findViewById(R.id.tv_select_select);
          tv_select_price=(TextView)view.findViewById(R.id.tv_select_price);
          tv_select_xiaoliang=(TextView)view.findViewById(R.id.tv_select_xiaoliang);
        ll_select_price=(LinearLayout)view.findViewById(R.id.ll_select_price);
        tv_select_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(popuwindow!=null){
                popuwindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
                return;
            }else {
                popuwindow = new GoodsScressPopuwindow(mContext);
                if (sortedsBeanList.size() == 0) {
                    ToastUtil.showbottomShortToast("加载失败");
                    mPresenter.getSortedList();
                    return;
                }
             final   String[] mVals1 = new String[sortedsBeanList.get(0).getCat().size()];
             final   String[] mVals2 = new String[sortedsBeanList.get(1).getCat().size()];
              final  String[] mVals3 = new String[sortedsBeanList.get(2).getCat().size()];
                for (int i = 0; i < sortedsBeanList.get(0).getCat().size(); i++) {
                    mVals1[i] = sortedsBeanList.get(0).getCat().get(i).getCat_name();
                }
                for (int i = 0; i < sortedsBeanList.get(1).getCat().size(); i++) {
                    mVals2[i] = sortedsBeanList.get(1).getCat().get(i).getCat_name();
                }
                for (int i = 0; i < sortedsBeanList.get(2).getCat().size(); i++) {
                    mVals3[i] = sortedsBeanList.get(2).getCat().get(i).getCat_name();
                }

                popuwindow.serData1(mVals1, mVals2, mVals3);
                popuwindow.setSearchClickListener(new GoodsScressPopuwindow.SearchClickListener() {
                    @Override
                    public void searchClickLIstener(Set<Integer> selectPosSet1, Set<Integer> selectPosSet2, Set<Integer> selectPosSet3,List<Integer> liIn) {
                        List<String> list=new ArrayList<>();
                        for(Integer i:selectPosSet1){
                            list.add(mVals1[i]);
                        }
                        for(Integer i:selectPosSet2){
                            list.add(mVals2[i]);
                        }
                        for(Integer i:selectPosSet3){
                            list.add(mVals3[i]);
                        }
                        ToastUtil.showbottomShortToast(list.toString()+liIn.toString());
                    }
                });
                popuwindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
            }
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
                mPresenter.getWeekList(map);
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
                mPresenter.getWeekList(map);

            }
        });
        mAdapter.addHeaderView(view);
    }
//         所有帅选按钮变灰色
    private void changeColor(){
        tv_select_xiaoliang.setTextColor(RescourseUtil.getColor(R.color.black));
        tv_select_price.setTextColor(RescourseUtil.getColor(R.color.black));
        tv_select_select.setTextColor(RescourseUtil.getColor(R.color.black));
    }

    @Override
    public void getWeekToprSuccess(List<WeekTopListBean.WeekTopBean> model) {
        Log.e("TTT",model.get(0).getSpc_litpic()+"-----");
        if(model!=null)
        GlideUtil.getInstance().LoadSupportv4FragmentBitmap(SDayExplosionFragment.this,AppConstant.BANNER_IMG_URL+model.get(0).getSpc_litpic(),img_banner,0,0,null);

    }

    @Override
    public void getWeekListtSuccess(List<WeekListBean.WeekBean> model) {
        dataLists=model;
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void getSortedListSuccess(List<SortedListBean.SortedsBean> model) {
        sortedsBeanList=model;
        Log.e("UUU",model.toString());
    }

    @Override
    public void loadFail(String msg) {

    }
}
