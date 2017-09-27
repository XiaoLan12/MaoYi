package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.utils.RescourseUtil;

import java.util.List;

/**
 * Created by lan on 2017/9/26.
 */

public class CouponsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public CouponsAdapter(@Nullable List<String> data) {
        super(R.layout.coupons_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RelativeLayout relativeLayout=helper.getView(R.id.coupons_rl);
        if(item.equals("未使用")){
            relativeLayout.setBackgroundColor(RescourseUtil.getColor(R.color.blue));
        }else{
            relativeLayout.setBackgroundColor(RescourseUtil.getColor(R.color.common_h3));
        }
    }
}
