package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class ClassificationColourAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ClassificationColourAdapter(@Nullable List<String> data) {
        super(R.layout.item_classification_colour, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView img=helper.getView(R.id.img);
        Glide.with(mContext).load(R.drawable.aaa).transform(new GlideRoundTransform(mContext,10)).into(img);

    }
    }
