package com.yizhisha.maoyi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.utils.RescourseUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by lan on 2017/7/24.
 */

public class TimeView extends View {
    private String mTitle;
    private int mTitleColor;
    private int mTitleSize;

    private Rect mBound;
    private Paint mPaint;

    long startTime;//开始时间
    long endTime;//结束时间
    long subTime;//服务器时间差

    private boolean isSecKill=false;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            invalidate();
        }
    };

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         /*
        获得自定义属性
         */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TimeView, defStyleAttr, 0);
        int a = typedArray.getIndexCount();
        for (int i = 0; i < a; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.TimeView_tvtitles:
                    mTitle = typedArray.getString(attr);
                    break;
                case R.styleable.TimeView_tvtitleColors:
                    mTitleColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TimeView_tvtitleSizes:
                    mTitleSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        /*
        获得绘制的区域
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);

    }
    /**
     * 重写onMeasure
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            width = (int) (getPaddingLeft() + mBound.width() + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {

            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            height = (int) (getPaddingTop() + mBound.height() + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Date curDate = new Date();// 获取当前时间
        long cur = curDate.getTime();// 获取当前时间
        mPaint.setColor(mTitleColor); // 这里设置 画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, getMeasuredWidth(), getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(RescourseUtil.getColor(R.color.common_color));

//        mPaint1.setColor(mTitleColor); // 这里设置 画笔颜色
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
//        mPaint1.setColor(RescourseUtil.getColor(R.color.red1));
        if(StartAndEndTimeDiff(cur+subTime).equals("活动未开始")||StartAndEndTimeDiff(cur+subTime).equals("活动已结束")){
            canvas.drawText(StartAndEndTimeDiff(cur+subTime), getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        }else{
            String[] s= StartAndEndTimeDiff(cur+subTime).split(":");
            canvas.drawText(s[0], getWidth() / 2 - mBound.width() / 2+4, getHeight() / 2 + mBound.height() / 2, mPaint);
            canvas.drawText(s[1], getWidth() / 2 - mBound.width() / 2+getWidth()/3+16, getHeight() / 2 + mBound.height() / 2, mPaint);
            canvas.drawText(s[2], getWidth() / 2 - mBound.width() / 2+getWidth()/3*2+24, getHeight() / 2 + mBound.height() / 2, mPaint);

            canvas.drawText(":", getWidth() / 2 - mBound.width() / 2+getWidth()/3-8, getHeight() / 2 + mBound.height() / 2, mPaint);
            canvas.drawText(":", getWidth() / 2 - mBound.width() / 2+getWidth()/3*2, getHeight() / 2 + mBound.height() / 2, mPaint);

            RectF rel4 = new RectF( 1,  1, getMeasuredWidth()/3-16,  getMeasuredHeight());
            canvas.drawRoundRect(rel4, 15, 15, mPaint);

            RectF rel5 = new RectF( getMeasuredWidth()/3+8,1 , getMeasuredWidth()/3*2-8,  getMeasuredHeight());
            canvas.drawRoundRect(rel5, 15, 15, mPaint);

            RectF rel6 = new RectF( getMeasuredWidth()/3*2+16,1, getMeasuredWidth(),  getMeasuredHeight());
            canvas.drawRoundRect(rel6, 15, 15, mPaint);

        }
//        canvas.drawText(StartAndEndTimeDiff(cur+subTime), getWidth() / 2 - mBound1.width() / 2, getHeight() / 2 + mBound1.height() / 2, mPaint1);



        mHandler.sendEmptyMessageDelayed(1, 1000);
    }
    /**
     * 剩余毫秒值 转换成 剩余时间格式
     *
     * @param t
     * @return
     */
    public String time2time(long t) {
        if (t <= 0) {
            return "00时:00分:00秒:000";
        }

        long haomiao = t % 1000;

        long miao = t / 1000 % 60;
        long min = t / 1000 / 60 % 60;
        long h = t / 1000 / 60 / 60;
       /* String miao1=miao+"";
        String min1=min+"";
        String h1=h+"";

        if(h<10){
            h1="0"+0;
        }
        if(min<10){
            min1="0"+0;
        }
       if(miao<10){
            miao1="0"+0;
        }*/
        return h + ":" + String.format("%02d", min) + ":" + String.format("%02d", miao);

      /*  return h + "时:" + min + "分:" + String.format("%02d", miao) + "秒:"
                + String.format("%03d", haomiao);
*/
    }

    /**
     * @param subTime 北京时间
     * @return 计算活动开始时间与当前时间的差值
     */
    public String StartAndEndTimeDiff(long subTime) {
        if (startTime - subTime > 0) {
            isSecKill=false;
            return "活动未开始";
        } else if (endTime - subTime > 0) {
            isSecKill=true;
            return time2time(endTime - subTime);
        } else {
            isSecKill=false;
            return "活动已结束";
        }
    }

    /**
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param curtime 服务器时间
     * @throws ParseException
     */
    public void setData(long starttime,long endtime,long  curtime) throws ParseException {
        startTime=starttime;
        endTime=endtime;
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        long cur1 = curDate.getTime();// 获取当前时间
        subTime = curtime*1000 -cur1;//获得时间差
    }

    public boolean isOverActivity(){
        return isSecKill;
    }
}