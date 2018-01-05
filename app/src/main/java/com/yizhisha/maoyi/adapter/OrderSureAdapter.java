package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/8/15.
 */

public class OrderSureAdapter extends BaseQuickAdapter<OrderSureBean.Goods,BaseViewHolder> {
    public OrderSureAdapter(@Nullable List<OrderSureBean.Goods> data) {
        super(R.layout.item_myorder_details,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderSureBean.Goods goods) {
        String detail=goods.getDetail();
        helper.setText(R.id.tradename_myorder_tv,goods.getTitle());
        helper.setText(R.id.tradeprice_myorder_tv,"￥"+goods.getTotalprice());
        ImageView imageView=helper.getView(R.id.tradehead_myorder_iv);
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.PRUDUCT_IMG_URL+goods.getLitpic(),imageView,GlideUtil.LOAD_BITMAP);
        helper.setText(R.id.tradecolor_myorder_tv,detail.substring(0,detail.indexOf("#")));
        helper.setText(R.id.tradesize_myorder_tv,detail.substring(detail.indexOf("#")+1, detail.length()));
        helper.setText(R.id.tradeamount_myorder_tv,"x"+goods.getAmount());
    }
}
