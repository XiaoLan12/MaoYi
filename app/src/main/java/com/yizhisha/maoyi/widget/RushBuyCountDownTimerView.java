package com.yizhisha.maoyi.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.R;

import java.util.Date;

import butterknife.Bind;

/**
 * Created by 小蓝 on 2018/2/5.
 */

public class RushBuyCountDownTimerView extends LinearLayout{
    private Context mContext;
       TextView dayTv;
       TextView hourTv;
       TextView minuteTv;
       TextView secondTv;

    private long startTime;
    private long endTime;
    private long nowTime;
    private long subTime;

    boolean stopThread=false;
    public RushBuyCountDownTimerView(Context context){
        this(context,null);

    }
    public void init(Long startdate, Long endDate){
        startTime=startdate;
        endTime=endDate;
        new Thread(mRunnable).start();
    }
    public RushBuyCountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_countdowntimer, this);
        dayTv= (TextView) view.findViewById(R.id.day_tv);
        hourTv=(TextView)view.findViewById(R.id.hour_tv);
        minuteTv=(TextView)view.findViewById(R.id.minute_tv);
        secondTv=(TextView)view.findViewById(R.id.second_tv);

    }
    public RushBuyCountDownTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_countdowntimer, this);
        dayTv=(TextView)view.findViewById(R.id.day_tv);
        hourTv=(TextView)view.findViewById(R.id.hour_tv);
        minuteTv=(TextView)view.findViewById(R.id.minute_tv);
        secondTv=(TextView)view.findViewById(R.id.second_tv);

    }
    public void setDateInfo(Long startdate, Long endDate) {
        try {
            //前的时间
            Date sd = new Date(startdate);
            //后的时间
            Date ed = new Date(endDate);
            long diff = ed.getTime() - sd.getTime(); // 得到的差值是微秒级别，可以忽略
            long day = diff / 86400000;                         //以天数为单位取整
            long hours = diff % 86400000 / 3600000;               //以小时为单位取整
            long minutes = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整
            long seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整

            dayTv.setText(String.format("%02d", day));
            hourTv.setText(String.format("%02d", hours));
            minuteTv.setText( String.format("%02d", minutes));
            secondTv.setText( String.format("%02d", seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler(){

        public void handleMessage(Message msg){

            switch (msg.what){

                case 1:
                    Date curDate = new Date();// 获取当前时间
                    long cur = curDate.getTime();// 获取当前时间

                    if (startTime - cur> 0) {
                        //activitStateTv.setText("活动未开始");
                    } else if (endTime - cur> 0) {
                       // activitStateTv.setText("距离结束还剩");
                        setDateInfo(startTime,endTime);
                        //setDateInfo((cur+subTime),endTime*1000);
                    } else {
                       //activitStateTv.setText("活动已结束");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Runnable mRunnable = new Runnable(){
        @Override
        public void run() {
            while(!stopThread){
                try{
                    Thread.sleep(1000);
                    Message message=handler.obtainMessage();
                    message.what = 1;
                    //发送信息给handler
                    handler.sendMessage(message);
                }catch (Exception e){

                }
            }
        }
    };
}
