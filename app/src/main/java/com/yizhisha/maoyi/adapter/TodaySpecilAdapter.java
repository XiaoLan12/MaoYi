package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.widget.TimeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */

public class TodaySpecilAdapter extends BaseQuickAdapter<DailyBean,BaseViewHolder> {
    SimpleDateFormat formatter;

    public TodaySpecilAdapter(@Nullable List<DailyBean> data) {
        super(R.layout.item_today_special, data);
        formatter = new SimpleDateFormat("MM/dd HH:mm:ss");
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyBean item) {
        helper.setText(R.id.tv_title,item.getSpc_name());
        ImageView imageView=helper.getView(R.id.imageView);
        Glide.with(mContext).load(AppConstant.BANNER_IMG_URL+item.getSpc_litpic()).into(imageView);
        helper.setText(R.id.tv_time,"活动时间:"+ formatter.format(new Date(Long.parseLong(item.getSpc_starttime())*1000))+"-"+formatter.format(new Date(Long.parseLong(item.getSpc_endtime())*1000)));
        TimeView mTvTimer=helper.getView(R.id.tv_count_down);
       try {
            mTvTimer.setData(Long.parseLong(item.getSpc_starttime()+"000"),Long.parseLong(item.getSpc_endtime()+"000"),System.currentTimeMillis()/1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}