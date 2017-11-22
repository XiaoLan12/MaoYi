package com.yizhisha.maoyi.api;

import com.yizhisha.maoyi.bean.json.CollectListBean;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.FootpringBean;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.bean.json.OrderListBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;
import com.yizhisha.maoyi.bean.json.SingleShopCartBean;
import com.yizhisha.maoyi.bean.json.WechatBean;
import com.yizhisha.maoyi.bean.json.WechatInfoBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by lan on 2017/9/22.
 */

public interface ApiService {
    //用户中心首页头像名称显示
    @GET("app/ucenter/profile/")
    Observable<MeInfoBean> loadHeadInfo(@Query("uid") int uid);

    //个人资料修改
    @FormUrlEncoded
    @POST("ios/ucenter/profile_save/")
    Observable<RequestStatusBean> changeUserInfo(@FieldMap Map<String, String> map);

    //收货地址列表
    @GET("/app/ucenter/address")
    Observable<GoodsListBean> loadGoodsAddress(@Query("uid") int uid);

    //收货地址保存
    @FormUrlEncoded
    @POST("app/ucenter/addressSave")
    Observable<RequestStatusBean> savaGoodsAddress(@FieldMap Map<String,String> map);

    //修改默认地址
    @GET("app/ucenter/addressDefault")
    Observable<RequestStatusBean> setDefaulsAddress(@QueryMap Map<String, String> param);

    //获得收藏列表
    @GET("ios/ucenter/favorite/")
    Observable<CollectListBean> getCollectList(@QueryMap Map<String, String> param);

    //添加收藏
    @GET("app/ucenter/favoriteAdd")
    Observable<RequestStatusBean> addCollect(@QueryMap Map<String, String> param);

    //取消收藏
    @GET("app/ucenter/favoriteDelete")
    Observable<RequestStatusBean> cacheCollect(@QueryMap Map<String, String> param);

    //我的足迹
    @GET("app/ucenter/historyList")
    Observable<FootpringBean> loadFootprint(@QueryMap Map<String,String> map);

    //删除足迹
    @FormUrlEncoded
    @POST("app/ucenter/historyDelete")
    Observable<RequestStatusBean> clearFootPrint(@FieldMap Map<String,String> map);

    //购物车
    @GET("ios/ucenter/shopcart/")
    Observable<ShopcartListBean> getShoppCartList(@Query("uid") int id);

    //加载单个购物车
    @GET("app/ucenter/shopcartShow")
    Observable<SingleShopCartBean> loadSingleShpCart(@QueryMap Map<String, String> param);

    //修改购物车
    @FormUrlEncoded
    @POST("app/ucenter/shopcartEdit")
    Observable<RequestStatusBean> changeShopCart(@FieldMap Map<String, String> param);

    //删除购物车商品
    @GET("app/ucenter/shopcartDelete")
    Observable<RequestStatusBean> deleteShoppCart(@QueryMap  Map<String, String> param);

    //获得订单
    @FormUrlEncoded
    @POST("app/order/orderSelect")
    Observable<OrderListBean> getOrderList(@FieldMap Map<String,String> map);

    //获得订单详情
    @GET("ios/order/detail")
    Observable<OrderListBean> getOrderDetails(@QueryMap Map<String,String> map);

    //取消订单
    @FormUrlEncoded
    @POST("app/order/orderDelete")
    Observable<RequestStatusBean> cancelOrder(@FieldMap Map<String,String> map);

    //确认收货
    @FormUrlEncoded
    @POST("app/order/order_receive/")
    Observable<RequestStatusBean> sureGoods(@FieldMap Map<String,String> map);

    //登录
    @FormUrlEncoded
    @POST("app/user/dologin/")
    Observable<RequestStatusBean> Login(@FieldMap Map<String,String> map);

    //注册
    @FormUrlEncoded
    @POST("app/user/doreg/")
    Observable<RequestStatusBean> Register(@FieldMap Map<String,String> map);

    // 找回密码
    @POST("ios/user/getpassword/")
    Observable<RequestStatusBean> FindPwd(@QueryMap Map<String,String> map);

    // 获取验证码
    @GET("app/ajax/checkpost/")
    Observable<RequestStatusBean> getCode(@QueryMap Map<String,String> map);

    // 手机快捷登录获取验证码
    @GET("app/ajax/logincheck/")
    Observable<RequestStatusBean> getPhoneLoginCode(@QueryMap Map<String,String> map);

    //手机快捷登录
    @FormUrlEncoded
    @POST("app/user/quicklogin/")
    Observable<RequestStatusBean> phoneLogin(@FieldMap Map<String,String> map);

    //修改密码
    @FormUrlEncoded
    @POST("app/ucenter/password_save/")
    Observable<RequestStatusBean> changePwd(@FieldMap Map<String,String> map);

    //获得微信登录的数据
    @GET()
    Observable<WechatBean> getWeChatLoginData(@Url String url);

    //微信登录
    @FormUrlEncoded
    @POST("app/user/wxlogin/")
    Observable<RequestStatusBean> weChatLogin(@FieldMap Map<String,String> map);

    //绑定微信号
    @GET("app/user/wxreg/")
    Observable<RequestStatusBean> bindWeChat(@QueryMap Map<String, String> param);

    //微信解除绑定
    @GET("app/user/wxunbind/")
    Observable<RequestStatusBean> unBindWeChat(@Query("uid") int uid);

    //微信绑定显示
    @GET("app/user/wxshow/")
    Observable<RequestStatusBean> showBindWeChart(@Query("uid") int uid);

    //加载微信用户信息
    @GET()
    Observable<WechatInfoBean> getWeChatInfo(@Url String url);



    @GET("app/user/dologin/mobile/15626036029/password/123456")
    Observable<LoginBean> login();


    //首页今日专场轮播图
    @GET("app/dailyTopSlider/")
    Observable<List<WeekTopBean>> getDailyTopSlider();


    //首页今日专场列表
    @GET("app/dailyList/")
    Observable<ListBean<DailyBean>> getDailyList();

    //首页7日轮播图
    @GET("app/weekTop/")
    Observable<List<WeekTopBean>> getWeekTop();
    //首页7日爆款列表
    @GET("app/weekList/")
    Observable<List<WeekListBean>> getWeekList();

    //首页往期专场
    @GET("app/pastList/")
    Observable<ListBean<DailyBean>> getPastList();



}
