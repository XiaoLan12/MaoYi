package com.yizhisha.maoyi.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderFootBean;
import com.yizhisha.maoyi.bean.json.MyOrderGoodsBean;
import com.yizhisha.maoyi.bean.json.MyOrderHeadBean;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class MyRefundOrderAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    public static final int ITEM_HEADER = 1;
    public static final int ITEM_CONTENT= 2;
    public static final int ITEM_FOOTER= 3;
    public MyRefundOrderAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<Object>() {
            @Override
            protected int getItemType(Object order) {
                if(order instanceof MyOrderHeadBean) {
                    return ITEM_HEADER;
                }else if(order instanceof MyOrderGoodsBean){
                    return ITEM_CONTENT;
                }else if(order instanceof MyOrderFootBean){
                    return ITEM_FOOTER;
                }
                return ITEM_CONTENT;
            }
        });
        getMultiTypeDelegate().registerItemType(ITEM_HEADER, R.layout.item_head_refund_order).
        registerItemType(ITEM_CONTENT, R.layout.item_middle_refund_order).
        registerItemType(ITEM_FOOTER, R.layout.item_bottom_refund_order);
    }


    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
        switch (helper.getItemViewType()){
            case ITEM_HEADER:
                final MyOrderHeadBean order= (MyOrderHeadBean) item;
                helper.setText(R.id.orderNumber_tv,"213412443445545");
                helper.setText(R.id.refundNumber_tv,"21341244344");
                break;
            case ITEM_CONTENT:
                helper.setText(R.id.tradename_tv,"商品详情");
                helper.setText(R.id.tradecolor_tv,"米白色；尺码:5");
                helper.setText(R.id.tradeprice_tv,"￥169.90");
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v,ITEM_CONTENT,helper.getLayoutPosition());
                    }
                });
                break;
            case ITEM_FOOTER:
                helper.setText(R.id.refundType_tv,"仅退款,退款成功");
                break;
        }

    }
    private OnItemTypeClickListener mOnItemClickListener;
    public interface OnItemTypeClickListener{
        void onItemClick(View view, int type, int position);
    }
    public void setOnItemTypeClickListener(OnItemTypeClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
