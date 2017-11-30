package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;

import java.util.List;

/**
 * Created by lan on 2017/9/25.
 */

public class RefundInfoAdapter extends BaseQuickAdapter<RefundDetailBean.Log,BaseViewHolder> {
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL= 0x0001;
    public RefundInfoAdapter(@Nullable List data) {
        super(R.layout.refund_info_item,data);
    }
    @Override
    protected void convert(BaseViewHolder helper,RefundDetailBean.Log item) {
        if (helper.getAdapterPosition()==0) {
            // 字体颜色加深
            TextView topLineTv=helper.getView(R.id.tvTopLine);
            topLineTv.setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.content_tv,0xff555555);
            helper.setTextColor(R.id.time_tv,0xff555555);
            helper.setBackgroundRes(R.id.tvDot,R.drawable.timelline_dot_first);
        } else{
            helper.setVisible(R.id.tvTopLine,true);
            helper.setTextColor(R.id.content_tv,0xff999999);
            helper.setTextColor(R.id.time_tv,0xff999999);
            helper.setBackgroundRes(R.id.tvDot,R.drawable.timelline_dot_normal);
        }
        helper.setText(R.id.title_tv,item.getLog_refund());
        helper.setText(R.id.content_tv,item.getLog_detail());
        helper.setText(R.id.time_tv,item.getLog_addtime());
    }
}
