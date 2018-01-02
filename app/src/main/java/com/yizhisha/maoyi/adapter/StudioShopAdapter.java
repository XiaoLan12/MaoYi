package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class StudioShopAdapter extends BaseQuickAdapter<StudioShopBean.Goods,BaseViewHolder> {
    public StudioShopAdapter(@Nullable List<StudioShopBean.Goods> data) {
        super(R.layout.item_studioshop,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudioShopBean.Goods item) {
            helper.setText(R.id.shop_title_tv,item.getTitle());
            helper.setText(R.id.shop_price_tv,item.getPrice());
            helper.setText(R.id.shop_sales_tv,item.getSales());
            ImageView imageView=helper.getView(R.id.img_iv);
        GlideUtil.getInstance().LoadContextBitmap(mContext,AppConstant.PRUDUCT_IMG_URL+item.getLitpic(),imageView,GlideUtil.LOAD_BITMAP);
    }
}
