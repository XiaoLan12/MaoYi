package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.WeekListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class SDayExplosionAdapter extends BaseQuickAdapter<WeekListBean.WeekBean,BaseViewHolder> {
    public SDayExplosionAdapter(@Nullable List<WeekListBean.WeekBean> data) {
        super(R.layout.item_sday_explosion, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeekListBean.WeekBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_price,"￥"+item.getPrice());
        helper.setText(R.id.tv_sales,item.getSales()+"人付款");
        ImageView imageView=helper.getView(R.id.img);
        Glide.with(mContext).load(AppConstant.PRUDUCT_IMG_URL+item.getLitpic()).into(imageView);

    }
}