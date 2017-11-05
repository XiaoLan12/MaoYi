package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */

public class TodaySpecilAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public TodaySpecilAdapter(@Nullable List<String> data) {
        super(R.layout.item_today_special, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}