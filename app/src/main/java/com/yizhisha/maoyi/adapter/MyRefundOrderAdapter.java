package com.yizhisha.maoyi.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.bean.json.OrderHeadBean;
import com.yizhisha.maoyi.bean.json.RefundFootBean;
import com.yizhisha.maoyi.bean.json.RefundHeadBean;
import com.yizhisha.maoyi.bean.json.RefundListBean;
import com.yizhisha.maoyi.utils.GlideUtil;

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
                if(order instanceof RefundHeadBean) {
                    return ITEM_HEADER;
                }else if(order instanceof RefundListBean.Goods){
                    return ITEM_CONTENT;
                }else if(order instanceof RefundFootBean){
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
                final RefundHeadBean refundHeadBean= (RefundHeadBean)item;
                helper.setText(R.id.orderNumber_tv,refundHeadBean.getOrderno());
                helper.setText(R.id.refundNumber_tv,refundHeadBean.getRefundno());
                break;
            case ITEM_CONTENT:
                final RefundListBean.Goods goods= (RefundListBean.Goods) item;
                String detail=goods.getDetail();
                helper.setText(R.id.tradename_tv,goods.getTitle());
                helper.setText(R.id.tradecolor_tv,goods.getDetail());
                helper.setText(R.id.tradeprice_tv,"￥:"+goods.getPrice());
                helper.setText(R.id.tradecolor_tv,detail.substring(0,detail.indexOf("#")));
                helper.setText(R.id.tradesize_tv,detail.substring(detail.indexOf("#")+1, detail.lastIndexOf("#")));
                GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.PRUDUCT_IMG_URL+goods.getLitpic(),
                        (ImageView) helper.getView(R.id.tradehead_iv),GlideUtil.LOAD_BITMAP);
                helper.setText(R.id.tradeamount_tv,"x"+goods.getAmount());
                break;
            case ITEM_FOOTER:
                final RefundFootBean footBean= (RefundFootBean) item;
                if(footBean.getType()==1){
                    switch (footBean.getRefundstatus()){
                        case 1:
                            helper.setText(R.id.refundType_tv,"待处理");
                            helper.setVisible(R.id.delete_refundorder_tv,false);
                            break;
                        case 2:
                            helper.setText(R.id.refundType_tv,"退款成功");
                            helper.setVisible(R.id.delete_refundorder_tv,true);
                            break;
                    }
                }else if(footBean.getType()==2){
                    switch (footBean.getRefundstatus()){
                        case 1:
                            helper.setText(R.id.refundType_tv,"待处理");
                            helper.setVisible(R.id.delete_refundorder_tv,false);
                            break;
                        case 2:
                            helper.setText(R.id.refundType_tv,"待卖家退款");
                            helper.setVisible(R.id.delete_refundorder_tv,false);
                            break;
                        case 3:
                            helper.setText(R.id.refundType_tv,"待卖家收货");
                            helper.setVisible(R.id.delete_refundorder_tv,false);
                            break;
                        case 4:
                            helper.setText(R.id.refundType_tv,"退款成功");
                            helper.setVisible(R.id.delete_refundorder_tv,true);
                            break;
                    }
                }
                helper.addOnClickListener(R.id.contact_the_merchant_tv);
                break;
        }

    }
}
