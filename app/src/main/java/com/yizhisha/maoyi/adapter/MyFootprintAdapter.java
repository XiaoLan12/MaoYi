package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.FootpringBean;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/9/26.
 */

public class MyFootprintAdapter extends BaseQuickAdapter<FootpringBean.History,BaseViewHolder> {
    public MyFootprintAdapter(@Nullable List<FootpringBean.History> data) {
        super(R.layout.mycollect_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootpringBean.History item) {
        helper.setText(R.id.titel_tv,item.getTitle());
        helper.setText(R.id.price_tv,item.getPrice()+"");
        helper.addOnClickListener(R.id.delete_tv);
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.PRUDUCT_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.photo_iv),GlideUtil.LOAD_BITMAP);
    }
}
