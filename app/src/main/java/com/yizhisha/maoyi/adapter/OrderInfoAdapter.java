package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.OrderInfoBean;
import com.yizhisha.maoyi.utils.RescourseUtil;

import java.util.List;

/**
 * Created by lan on 2017/9/25.
 */

public class OrderInfoAdapter extends BaseQuickAdapter<OrderInfoBean,BaseViewHolder> {
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL= 0x0001;
    public OrderInfoAdapter(@Nullable List data) {
        super(R.layout.time_item,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderInfoBean item) {
        if (helper.getAdapterPosition()==0) {
            // 字体颜色加深
            TextView topLineTv=helper.getView(R.id.tvTopLine);
            topLineTv.setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tvAcceptStation,0xff555555);
            helper.setTextColor(R.id.tvAcceptTime,0xff555555);
            helper.setBackgroundRes(R.id.tvDot,R.drawable.timelline_dot_first);
        } else{
            helper.setVisible(R.id.tvTopLine,true);
            helper.setTextColor(R.id.tvAcceptStation,0xff999999);
            helper.setTextColor(R.id.tvAcceptTime,0xff999999);
            helper.setBackgroundRes(R.id.tvDot,R.drawable.timelline_dot_normal);
        }
        helper.setText(R.id.tvAcceptStation,item.getContent());
        helper.setText(R.id.tvAcceptTime,item.getTime());
    }
}
