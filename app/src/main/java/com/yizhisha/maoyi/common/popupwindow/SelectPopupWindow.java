package com.yizhisha.maoyi.common.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yizhisha.maoyi.R;

/**
 * Created by 小蓝 on 2018/4/17.
 */

public class SelectPopupWindow extends PopupWindow {
    //一个LinearLayout 表示一个可选操作
    private LinearLayout fp_linear_sharetoWeixin,fp_linear_sharetoquan;
    //popupWindow 取消文本按钮
    private TextView fp_cancel;

    private View mMenuView;
    public SelectPopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setAnimationStyle(R.style.bottomDialogAnim);
        mMenuView = inflater.inflate(R.layout.feed_popuwindow, null);

        fp_linear_sharetoWeixin = (LinearLayout) mMenuView.findViewById(R.id.fp_linear_sharetoWeixin);
        fp_linear_sharetoquan = (LinearLayout) mMenuView.findViewById(R.id.fp_linear_sharetoquan);
        fp_cancel = (TextView) mMenuView.findViewById(R.id.fp_cancel);
        //点击取消按钮，关闭popupWindow
        fp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        fp_linear_sharetoWeixin.setOnClickListener(itemsOnClick);
        fp_linear_sharetoquan.setOnClickListener(itemsOnClick);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        //点击popupWindow之外的部分  关闭popupWindow
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.fp_linear_share).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
