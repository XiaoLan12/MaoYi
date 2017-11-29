package com.yizhisha.maoyi.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.bean.json.OrderHeadBean;
import com.yizhisha.maoyi.bean.json.ToEvalutionFootBean;
import com.yizhisha.maoyi.utils.DateUtil;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class ToEvalutionAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    public static final int ITEM_CONTENT= 2;
    public static final int ITEM_FOOTER= 3;
    public ToEvalutionAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<Object>() {
            @Override
            protected int getItemType(Object order) {
                if(order instanceof MyOrderListBean.Goods){
                    return ITEM_CONTENT;
                }else if(order instanceof ToEvalutionFootBean){
                    return ITEM_FOOTER;
                }
                return ITEM_CONTENT;
            }
        });
        getMultiTypeDelegate().registerItemType(ITEM_CONTENT, R.layout.item_middle_tovualution).
        registerItemType(ITEM_FOOTER, R.layout.item_bottom_toevalution);
    }


    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
        switch (helper.getItemViewType()){
            case ITEM_CONTENT:
                MyOrderListBean.Goods goods= (MyOrderListBean.Goods) item;
                helper.setText(R.id.titel_tv,goods.getTitle());
                helper.setText(R.id.price_tv,"￥"+goods.getTotalprice());
               /* GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),
                        (ImageView) helper.getView(R.id.tradehead_myorder_iv),GlideUtil.LOAD_BITMAP);*/
                break;
            case ITEM_FOOTER:
                final ToEvalutionFootBean orderFootBean= (ToEvalutionFootBean) item;
                helper.setText(R.id.date_tv, DateUtil.getDateToString2(orderFootBean.getAddtime()));
                helper.setText(R.id.color_tv, orderFootBean.getDetail());
                helper.addOnClickListener(R.id.add_comment_tv);
                /*helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v,ITEM_FOOTER,helper.getLayoutPosition());
                    }
                });*/
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
