package com.yizhisha.maoyi.ui.classify.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.SortedBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.gangedrecyclerview.CheckListener;
import com.yizhisha.maoyi.gangedrecyclerview.ItemHeaderDecoration;
import com.yizhisha.maoyi.gangedrecyclerview.RvListener;
import com.yizhisha.maoyi.gangedrecyclerview.SortAdapter;
import com.yizhisha.maoyi.gangedrecyclerview.SortBean;
import com.yizhisha.maoyi.gangedrecyclerview.SortDetailFragment;
import com.yizhisha.maoyi.ui.classify.contract.ClassifyContract;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/9/22.
 */

public class ClassifyFragment extends BaseFragment<com.yizhisha.maoyi.ui.classify.presenter.ClassifyPresenter> implements CheckListener,ClassifyContract.View{
    private RecyclerView rvSort;
    private SortAdapter mSortAdapter;
    private SortDetailFragment mSortDetailFragment;
    private LinearLayoutManager mLinearLayoutManager;
    private int targetPosition;//点击左边某一个具体的item的位置
    private boolean isMoved;
    private SortBean mSortBean;

    private String sort;

    List<SortedListBean.SortedsBean> sortedsBeanList2=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        initView1();
        initData();

        mPresenter.getStudio(true);
    }
    private void initData() {


    }

    //从资源文件中获取分类json
    private String getAssetsData(String path) {
        String result = "";
        try {
            //获取输入流
            InputStream mAssets = getActivity().getAssets().open(path);
            //获取文件的字节数
            int lenght = mAssets.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("fuck", e.getMessage());
            return result;
        }
    }

    public void createFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("right", mSortBean.getCategoryOneArray());
        mSortDetailFragment.setArguments(bundle);
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }
    private void setChecked(int position, boolean isLeft) {
        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            mSortAdapter.setCheckedPosition(position);
            //此处的位置需要根据每个分类的集合来进行计算
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += mSortBean.getCategoryOneArray().get(i).getCategoryTwoArray().size();
            }
            count += position;
            mSortDetailFragment.setData(count);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(targetPosition));//凡是点击左边，将左边点击的位置作为当前的tag
        } else {
            if (isMoved) {
                isMoved = false;
            } else
                mSortAdapter.setCheckedPosition(position);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));//如果是滑动右边联动左边，则按照右边传过来的位置作为tag

        }
        moveToCenter(position);

    }

    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = rvSort.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - rvSort.getHeight() / 2);
            rvSort.smoothScrollBy(0, y);
        }

    }

    private void initView1() {
        rvSort = (RecyclerView) getActivity().findViewById(R.id.rv_sort);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        rvSort.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        rvSort.addItemDecoration(decoration);
    }

    @Override
    public void check(int position, boolean isScroll) {
        setChecked(position, isScroll);

    }

    @Override
    public void getStudioSuccess(List<StudioBean.StudioListBean> model) {
        List<SortedBean>  sortedBeans=new ArrayList<>();
        for(int i=0;i<model.size();i++){
            sortedBeans.add(new SortedBean(model.get(i).getId()+"",model.get(i).getWorkshop(),model.get(i).getAvatar()));
//            SortedListBean.SortedsBean sortedsBean=new SortedListBean.SortedsBean();
//            sortedsBeanList2.add(new SortedListBean());
        }
        SortedListBean.SortedsBean sortedsBean=new SortedListBean().new SortedsBean("999","工作室",sortedBeans);
        sortedsBeanList2.add(sortedsBean);
        mPresenter.getSortedList();
    }

    @Override
    public void getSortedListSuccess(List<SortedListBean.SortedsBean> model) {

        //获取asset目录下的资源文件
        String assetsData = getAssetsData("sort.json");
        Gson gson = new Gson();
        mSortBean = gson.fromJson(assetsData, SortBean.class);
        List<SortBean.CategoryOneArrayBean> categoryOneArray = mSortBean.getCategoryOneArray();
        List<String> list = new ArrayList<>();

        //初始化左侧列表数据
        for (int i = 0; i < model.size(); i++) {
            list.add(model.get(i).getName());
        }
        list.add("工作室");
        model.addAll(sortedsBeanList2);

        AppConstant.sortedBeanList=model;
      /*  for (int i = 0; i < categoryOneArray.size(); i++) {
            list.add(categoryOneArray.get(i).getName());
        }*/
        Log.e("TTT",list.toString()+"   string"+model.toString());
        mSortAdapter = new SortAdapter(mContext, list, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                if (mSortDetailFragment != null) {
                    isMoved = true;
                    targetPosition = position;
                    setChecked(position, true);
                }
            }
        });
        rvSort.setAdapter(mSortAdapter);
        createFragment();
    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    public void showEmpty() {

    }
}
