package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class MyOrderDetailsAdapter extends BaseQuickAdapter<MyOrderListBean.Goods,BaseViewHolder> {
    public MyOrderDetailsAdapter(Context context, @Nullable List<MyOrderListBean.Goods> data) {
        super(R.layout.item_myorder_details,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyOrderListBean.Goods goods) {
        helper.setText(R.id.tradename_myorder_tv,goods.getTitle());
        helper.setText(R.id.tradecolor_myorder_tv,goods.getRemark());
        helper.setText(R.id.tradeprice_myorder_tv,"￥"+goods.getTotalprice());
        ImageView imageView=helper.getView(R.id.tradehead_myorder_iv);
        //GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),imageView,GlideUtil.LOAD_BITMAP);
        helper.setText(R.id.tradecolor_myorder_tv,goods.getDetail());
    }
}
