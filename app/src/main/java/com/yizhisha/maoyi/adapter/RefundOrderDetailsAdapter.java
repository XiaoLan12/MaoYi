package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class RefundOrderDetailsAdapter extends BaseQuickAdapter<RefundDetailBean.Goods,BaseViewHolder> {
    public RefundOrderDetailsAdapter( @Nullable List<RefundDetailBean.Goods> data) {
        super(R.layout.item_myorder_details,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, RefundDetailBean.Goods goods) {
        helper.setText(R.id.tradename_myorder_tv,goods.getTitle());
        helper.setText(R.id.tradecolor_myorder_tv,goods.getDetail());
        helper.setText(R.id.tradeprice_myorder_tv,"￥:"+goods.getPrice());
    }
}
