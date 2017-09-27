package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderGoodsBean;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class RefundOrderDetailsAdapter extends BaseQuickAdapter<MyOrderGoodsBean,BaseViewHolder> {
    public RefundOrderDetailsAdapter( @Nullable List<MyOrderGoodsBean> data) {
        super(R.layout.item_myorder_details,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyOrderGoodsBean goods) {

    }
}
