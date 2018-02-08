package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ProductDetailImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ProductDetailImgAdapter(@Nullable List<String> data) {
        super(R.layout.item_fragment_product_img,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String goods) {
        GlideUtil.getInstance().LoadContextBitmap(mContext,goods,
                (ImageView) helper.getView(R.id.img), GlideUtil.LOAD_BITMAP);
    }
}