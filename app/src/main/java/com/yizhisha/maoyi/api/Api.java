package com.yizhisha.maoyi.api;

import com.yizhisha.maoyi.bean.json.CollectListBean;
import com.yizhisha.maoyi.bean.json.CommentPicBean;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.FootpringBean;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.bean.json.MyCommentBean;
import com.yizhisha.maoyi.bean.json.MyOrderBean;
import com.yizhisha.maoyi.bean.json.RefundBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenBean;
import com.yizhisha.maoyi.bean.json.SingleShopCartBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.UserHeadBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopBean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by lan on 2017/9/22.
 */

public class Api {
    public final static String API_BASE_URL = "http://dyc.maozhiwang.com/index.php/";
    public static Api instance;
    private ApiService service;

    //配置网络参数
    public Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    //单例
    public static Api getInstance() {
        if (instance == null)
            instance = new Api();
        return instance;
    }
    //用户中心首页头像名称显示
    public Observable<MeInfoBean> loadHeadInfo(int uid){
        return service.loadHeadInfo(uid);
    }
    //修改用户信息
    public Observable<RequestStatusBean> changeUserInfo(Map<String, String> map){
        return service.changeUserInfo(map);
    }
    //修改用户头像
    public Observable<UserHeadBean> changeUserHead(RequestBody uid, MultipartBody.Part body){
        return service.changeUserHead(uid,body);
    }
    //收货地址列表
    public Observable<GoodsListBean> loadGoodsAddress(int uid){
        return service.loadGoodsAddress(uid);
    }
    //修改默认地址
    public Observable<RequestStatusBean> setDefaulsAddress(Map<String,String> map){
        return service.setDefaulsAddress(map);
    }
    //保存收货地址
    public Observable<RequestStatusBean> savaGoodsAddress(Map<String,String> map){
        return service.savaGoodsAddress(map);
    }
    //获得收藏k列表
    public Observable<CollectListBean> loadCollectList(Map<String, String> param){
        return service.getCollectList(param);
    }
    //取消收藏
    public Observable<RequestStatusBean> cacheCollect(Map<String, String> param){
        return service.cacheCollect(param);
    }
    //我的足迹
    public Observable<FootpringBean> loadFootprint(Map<String,String> map){
        return service.loadFootprint(map);
    }
    //删除我的足迹
    public Observable<RequestStatusBean> clearFootPrint(Map<String,String> map){
        return service.clearFootPrint(map);
    }
    // 购物车
    public Observable<ShopcartListBean> getShoppCart(int uid){
        return service.getShoppCartList(uid);
    }
    //加载单个购物车
    public Observable<SingleShopCartBean> loadSingleShpCart(Map<String,String> map){
        return service.loadSingleShpCart(map);
    }
    //修改购物车
    public Observable<RequestStatusBean> changeShopCart(Map<String,String> map){
        return service.changeShopCart(map);
    }
    //删除购物车
    public Observable<RequestStatusBean> deleteShoppCart(Map<String,String> map){
        return service.deleteShoppCart(map);
    }
    //订单列表
    public Observable<MyOrderBean> loadOrderList(Map<String,String> map){
        return service.getOrderList(map);
    }
    //订单详情
    public Observable<MyOrderBean> loadOrderDetail(Map<String,String> map){
        return service.getOrderDetails(map);
    }
    //我的评论
    public Observable<MyCommentBean> loadMyComment(int uid){
        return service.loadMyComment(uid);
    }
    //发布评论
    public Observable<RequestStatusBean> addComment(Map<String,String> map){
        return service.addComment(map);
    }
    //发布追评
    public Observable<RequestStatusBean> addAddComment(Map<String,String> map){
        return service.addAddComment(map);
    }
    //添加评论图片
    public Observable<CommentPicBean> addCommentPic(MultipartBody.Part body){
        return service.addCommentPic(body);
    }
    //退款列表
    public Observable<RefundBean> loadRefundList(int uid){
        return service.loadRefundList(uid);
    }
    public Observable<LoginBean> login(){
        return service.login();
    }

    //首页今日专场轮播图
    public Observable<List<WeekTopBean>> getDailyTopSlider(){
        return service.getDailyTopSlider();
    }
    //首页今日专场列表
    public Observable<ListBean<DailyBean>> getDailyList(){
        return service.getDailyList();
    }
    //首页7日爆款列表
    public Observable<List<WeekTopBean>> getWeekTop(){
        return service.getWeekTop();
    }

    //首页7日爆款列表
    public Observable<List<WeekListBean>> getWeekList(){
        return service.getWeekList();
    }

    //首页往期专场列表
    public Observable<ListBean<DailyBean>> getPastList(){
        return service.getPastList();
    }

    //产品分类
    public Observable<List<SortedListBean>> getSorted(){
        return service.getSorted();
    }
    //专题详情列表
    public Observable<SpecialDetailBean> getSpecialGoodsList(Map<String, String> map){
        return service.getSpecialGoodsList(map);
    }
    //商品详情
    public Observable<GoodsDetailBean> getGoodsDetail(Map<String, String> map){
        return service.getGoodsDetail(map);
    }
    //商品详情的同类推荐
    public Observable<SimilarRecommenBean> getSimilarRecommen(){
        return service.getSimilarRecommen();
    }

}
