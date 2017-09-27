package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;

import java.util.List;

/**
 * Created by lan on 2017/9/26.
 */

public class MyCollectAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MyCollectAdapter(@Nullable List<String> data) {
        super(R.layout.mycollect_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.titel_tv,"甜美甜美甜美少女风系列连衣裙");
        helper.setText(R.id.price_tv,"168.00");
    }
}
