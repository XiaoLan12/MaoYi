package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class RefundOrderDetailsAdapter extends BaseQuickAdapter<MyOrderListBean.Goods,BaseViewHolder> {
    public RefundOrderDetailsAdapter( @Nullable List<MyOrderListBean.Goods> data) {
        super(R.layout.item_myorder_details,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyOrderListBean.Goods goods) {

    }
}
