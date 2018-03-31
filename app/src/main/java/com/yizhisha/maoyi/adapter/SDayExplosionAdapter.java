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
import com.yizhisha.maoyi.utils.GlideUtil;

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
        String url=item.getLitpic();
        String newUrl;
        if(url.length()>0&&url.startsWith("http://")){
            newUrl=url;
        }else{
            newUrl=AppConstant.PRUDUCT_IMG_URL+url;
        }
        GlideUtil.getInstance().LoadContextBitmap(mContext, newUrl,
                (ImageView) helper.getView(R.id.img),GlideUtil.LOAD_BITMAP);

    }
}