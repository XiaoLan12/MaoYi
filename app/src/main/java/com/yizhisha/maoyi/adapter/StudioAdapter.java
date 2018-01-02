package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class StudioAdapter extends BaseQuickAdapter<StudioBean.StudioListBean,BaseViewHolder> {
    public StudioAdapter(@Nullable List<StudioBean.StudioListBean> data) {
        super(R.layout.item_studio,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudioBean.StudioListBean item) {
            helper.setText(R.id.name_tv,item.getWorkshop());
            helper.setText(R.id.address_tv,item.getAddress());
        ImageView imageView=helper.getView(R.id.img);
        GlideUtil.getInstance().LoadContextBitmap(mContext,item.getAvatar(),imageView,GlideUtil.LOAD_BITMAP);
    }
}
