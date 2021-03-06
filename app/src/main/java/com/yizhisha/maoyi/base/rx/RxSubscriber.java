package com.yizhisha.maoyi.base.rx;
import android.content.Context;

import com.yizhisha.maoyi.App;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.utils.NetWorkUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;

import rx.Subscriber;

/**
 * Created by lan on 2016/7/4.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private String msg;
    private boolean showDialog=true;
    private LoadingDialog mLoadingDialog;
    public RxSubscriber(Context context, String msg, boolean showDialog){
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscriber(Context context) {
        this(context, App.getAppContext().getString(R.string.loading),true);
    }
    public RxSubscriber(Context context, boolean showDialog) {
        this(context, App.getAppContext().getString(R.string.loading),showDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                mLoadingDialog=new LoadingDialog(mContext,msg,true);
                mLoadingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCompleted(){
        if (showDialog)
            if(mLoadingDialog!=null){
                mLoadingDialog.cancelDialog();
                mLoadingDialog=null;
            }
    }
    @Override
    public void onError(Throwable e) {
        if (showDialog){
            if(mLoadingDialog!=null){
                mLoadingDialog.cancelDialog();
                mLoadingDialog=null;
            }
        }
        e.printStackTrace();
        //网络
        if (!NetWorkUtil.isNetConnected(App.getAppContext())) {
            onFailure(RescourseUtil.getString(R.string.no_net));
        }
        //服务器
        else{
            onFailure(e.getMessage());
        }

    }
    @Override
    public void onNext(T t) {
        onSuccess(t);

    }
    protected abstract void onSuccess(T t);

    protected abstract void onFailure(String message);
}
