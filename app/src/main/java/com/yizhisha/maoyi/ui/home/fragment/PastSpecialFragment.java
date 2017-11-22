package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.PastSpecilAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.ui.home.contract.PastSpecialContract;
import com.yizhisha.maoyi.ui.home.presenter.PastSpecialPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class PastSpecialFragment extends BaseFragment<PastSpecialPresenter> implements PastSpecialContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private PastSpecilAdapter mAdapter;
    private List<DailyBean> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_past_special;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter = new PastSpecilAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getPastList();
    }

    @Override
    public void getPastListSuccess(ListBean<DailyBean> model) {
        dataLists=model.getList();
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void loadFail(String msg) {

    }
}
