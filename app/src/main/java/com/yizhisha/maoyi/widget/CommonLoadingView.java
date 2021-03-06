package com.yizhisha.maoyi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.R;

/**
 * 页面加载提示布局
 */

public class CommonLoadingView extends FrameLayout {

    //加载时显示文字
    protected TextView mLoadingTextTv;
    //数据为空时的图片
    protected ImageView mLoadingEmpyIv;
    //数据为空时的文字
    protected TextView mLoadingEmpyTv;
    public Context mContext;
    //加载错误视图
    protected LinearLayout mLoadErrorLl;
    //加载错误点击事件处理
    private LoadingHandler mLoadingHandler;
    //加载view
    private View loadingView;
    //加载失败view
    private View loadingErrorView;
    //数据为空
    private View emptyView;
    //错误的文字提示
    private TextView errorTipTv;


    public CommonLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setLoadingHandler(LoadingHandler loadingHandler) {
        mLoadingHandler = loadingHandler;
    }

    public void setLoadingErrorView(View loadingErrorView) {
        this.removeViewAt(1);
        this.loadingErrorView = loadingErrorView;
        this.loadingErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingHandler != null) {
                    mLoadingHandler.doRequestData();
                    CommonLoadingView.this.load();
                }
            }
        });
        this.addView(loadingErrorView,1);
    }

    public void setLoadingView(View loadingView) {
        this.removeViewAt(0);
        this.loadingView = loadingView;
        this.addView(loadingView,0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadingView = inflate(mContext, R.layout.common_loading_view, null);
        loadingErrorView = inflate(mContext, R.layout.network_layout, null);
        emptyView = inflate(mContext, R.layout.empty_layout, null);
        this.addView(loadingView);
        this.addView(loadingErrorView);
        this.addView(emptyView);
        loadingErrorView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        initView(this);
    }


    public void setMessage(String message) {
        mLoadingTextTv.setText(message);
    }


    private void initView(View rootView) {
        mLoadingTextTv = (TextView) rootView.findViewById(R.id.loading_text_tv);
        mLoadingEmpyIv= (ImageView) rootView.findViewById(R.id.iv_empty);
        mLoadingEmpyTv= (TextView) rootView.findViewById(R.id.tv_empty);
        mLoadErrorLl = (LinearLayout) rootView.findViewById(R.id.load_error_ll);
        errorTipTv=(TextView)rootView.findViewById(R.id.text_erro);
        mLoadErrorLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingHandler != null) {
                    CommonLoadingView.this.load();
                    mLoadingHandler.doRequestData();
                }
            }
        });
    }

    public void load(){
        loadingView.setVisibility(VISIBLE);
        loadingErrorView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }

    public void load(String message){
        mLoadingTextTv.setText(message);
        loadingView.setVisibility(VISIBLE);
        loadingErrorView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }


    public void loadSuccess(){
        this.loadSuccess(false);
    }

    public void loadSuccess(boolean isEmpty){
        loadingView.setVisibility(GONE);
        loadingErrorView.setVisibility(GONE);
        if (isEmpty) {
            emptyView.setVisibility(VISIBLE);
        }else{
            emptyView.setVisibility(GONE);
        }
    }
    public void loadSuccess(boolean isEmpty,int image,String txt){
        loadingView.setVisibility(GONE);
        loadingErrorView.setVisibility(GONE);
        if (isEmpty) {
            mLoadingEmpyIv.setImageResource(image);
            mLoadingEmpyTv.setVisibility(VISIBLE);
            mLoadingEmpyTv.setText(txt);
            emptyView.setVisibility(VISIBLE);
        }else{
            emptyView.setVisibility(GONE);
        }
    }


    public void loadError(){
        loadingView.setVisibility(GONE);
        loadingErrorView.setVisibility(VISIBLE);
    }
    public void loadError(String tip){
        loadingView.setVisibility(GONE);
        loadingErrorView.setVisibility(VISIBLE);
        if(!tip.equals("")) {
            errorTipTv.setText(tip);
        }
    }


    public interface LoadingHandler{
        void doRequestData();
    }
}
