package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.time.TimerUtils;

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

        LinearLayout parent=helper.getView(R.id.parent);

        String  s=TimerUtils.TIME_STYLE_ONE;
        if(Long.parseLong(item.getSpc_endtime()+"000")-Long.parseLong(item.getSpc_starttime()+"000")>86400000){
            s=TimerUtils.TIME_STYLE_THREE1;
        }
        TextView tv5= TimerUtils.getTimer(TimerUtils.JD_STYLE,mContext,Long.parseLong(1518428305+"000")-System.currentTimeMillis(),s,R.drawable.timer_shape)
                .setTimerPadding(12,12,12,12)//设置内间距
                .setTimerTextColor(RescourseUtil.getColor(R.color.common_color))//设置字体颜色
                .setTimerTextSize(38)//设置字体大小
                .setTimerGapColor(RescourseUtil.getColor(R.color.common_color))//设置间隔的颜色
                .getmDateTv();//拿到TextView对象
        /*int count=parent.getChildCount();
        for(int i=0;i<count;i++){
            View view =parent.getChildAt(i);
            if(view instanceof TextView){
                parent.removeView(tv5);
            }
        }*/
        if(parent.getChildCount()==0){
            parent.addView(tv5);
            setmLayoutParams(tv5);
        }
//        parent.removeAllViews();

      /* try {
            mTvTimer.setData(Long.parseLong(item.getSpc_starttime()+"000"),Long.parseLong(item.getSpc_endtime()+"000"),System.currentTimeMillis()/1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
    private void setmLayoutParams(TextView tv) {
        tv.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
//        params.setMargins(20,20,20,20);
        tv.setLayoutParams(params);
    }
}