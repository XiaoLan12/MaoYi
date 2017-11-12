package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class ClassifyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ClassifyAdapter(@Nullable List<String> data) {
        super(R.layout.item_classify, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
    }
