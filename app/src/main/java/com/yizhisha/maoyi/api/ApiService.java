package com.yizhisha.maoyi.api;

import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.MeInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lan on 2017/9/22.
 */

public interface ApiService {
    //用户中心首页头像名称显示
    @GET("app/ucenter/profile/")
    Observable<MeInfoBean> loadHeadInfo(@Query("uid") int uid);
    @GET("app/user/dologin/mobile/15626036029/password/123456")
    Observable<LoginBean> login();
}
