package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.DailyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */

public class PastSpecilAdapter extends BaseQuickAdapter<DailyBean,BaseViewHolder> {
    public PastSpecilAdapter(@Nullable List<DailyBean> data) {
        super(R.layout.item_past_special, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyBean item) {
            helper.setText(R.id.tv_title,item.getSpc_name());
        ImageView imageView=helper.getView(R.id.imageView);
        Glide.with(mContext).load(AppConstant.BANNER_IMG_URL+item.getSpc_litpic()).into(imageView);
    }
}