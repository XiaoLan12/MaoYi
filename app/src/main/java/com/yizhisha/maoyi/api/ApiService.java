package com.yizhisha.maoyi.api;

import com.yizhisha.maoyi.bean.json.CollectListBean;
import com.yizhisha.maoyi.bean.json.FootpringBean;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.bean.json.OrderListBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;
import com.yizhisha.maoyi.bean.json.SingleShopCartBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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

    @GET("app/user/dologin/mobile/15626036029/password/123456")
    Observable<LoginBean> login();
}
