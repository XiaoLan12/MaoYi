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
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.bean.json.RefundBean;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopCartOrderSureBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenListBean;
import com.yizhisha.maoyi.bean.json.SingleShoppCartBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.bean.json.TuiguangItem1Bean;
import com.yizhisha.maoyi.bean.json.TuiguangItem2Bean;
import com.yizhisha.maoyi.bean.json.UserHeadBean;
import com.yizhisha.maoyi.bean.json.WechatBean;
import com.yizhisha.maoyi.bean.json.WechatInfoBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by lan on 2017/9/22.
 */

public class Api {
    public final static String API_BASE_URL = "http://dyc.maozhiwang.com/";
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
    //获得收藏列表
    public Observable<CollectListBean> loadCollectList(Map<String, String> param){
        return service.getCollectList(param);
    }
    //收藏商品
    public Observable<RequestStatusBean> collectProduct(Map<String, String> param){
        return service.workshopFav(param);
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
    public Observable<SingleShoppCartBean> loadSingleShpCart(Map<String,String> map){
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
    //购物车订单确认
    public Observable<ShopCartOrderSureBean> shopCartOrderSure(Map<String,String> map){
        return service.shopCartOrderSure(map);
    }
    //订单列表
    public Observable<MyOrderBean> loadOrderList(Map<String,String> map){
        return service.getOrderList(map);
    }
    //订单查询
    public Observable<MyOrderBean> queryOrderList(Map<String,String> map){
        return service.queryOrderList(map);
    }

    //订单详情
    public Observable<MyOrderBean> loadOrderDetail(Map<String,String> map){
        return service.getOrderDetails(map);
    }
    //确认收货
    public Observable<RequestStatusBean> sureGoods(Map<String,String> map){
        return service.sureGoods(map);
    }
    //取消订单
    public Observable<RequestStatusBean> cancelOrder(Map<String,String> map){
        return service.cancelOrder(map);
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

    //退款详情
    public Observable<RefundDetailBean> loadRefundDetail(Map<String,String> map){
        return service.loadRefundDetail(map);
    }

    //撤销退款
    public Observable<RequestStatusBean> refundDel(Map<String,String> map){
        return service.refundDel(map);
    }
    //退款物流
    public Observable<ResponseBody> loadRefundExpress(String  refundno){
        return service.loadRefundExpress(refundno);
    }

    //物流
    public Observable<ResponseBody> loadExpress(String  refundno){
        return service.loadExpress(refundno);
    }

    //退款申请
    public Observable<RequestStatusBean> refundApply(Map<String,String> map){
        return service.refundApply(map);
    }
    //退款图片上传
    public Observable<CommentPicBean> uploadRefundPic(MultipartBody.Part body){
        return service.uploadRefundPic(body);
    }
    //提交退货
    public Observable<RequestStatusBean> refundGood(Map<String,String> map){
        return service.refundGood(map);
    }
    //修改密码
    public Observable<RequestStatusBean> changePwd(Map<String,String> map){
        return service.changePwd(map);
    }

    //绑定手机号码
    public Observable<RequestStatusBean> bindPhone(Map<String, String> map){
        return service.bindPhone(map);
    }
    //获得绑定的手机号码
    public Observable<RequestStatusBean> loadBindPhone(int uid){
        return service.loadBindPhone(uid);
    }

    //登录
    public Observable<RequestStatusBean> Login(Map<String, String> map){
        return service.Login(map);
    }

    //手机快捷登录
    public Observable<LoginBean> phoneLogin(Map<String, String> map){
        return service.phoneLogin(map);
    }


    //首页今日专场轮播图
    public Observable<WeekTopListBean> getDailyTopSlider(){
        return service.getDailyTopSlider();
    }
    //首页今日专场列表
    public Observable<ListBean<DailyBean>> getDailyList(){
        return service.getDailyList();
    }
    //首页7日爆款列表
    public Observable<WeekTopListBean> getWeekTop(){
        return service.getWeekTop();
    }

    //首页7日爆款列表
    public Observable<WeekListBean> getWeekList(Map<String, String> map){
        return service.getWeekList(map);
    }

    //首页往期专场列表
    public Observable<ListBean<DailyBean>> getPastList(){
        return service.getPastList();
    }

    //产品分类
    public Observable<SortedListBean> getSorted(){
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
    public Observable<SimilarRecommenListBean> getSimilarRecommen(int tid){
        return service.getSimilarRecommen(tid);
    }
    //获取验证码
    public Observable<RequestStatusBean> getCode(Map<String, String> map){
        return service.getCode(map);
    }
    //注册
    public Observable<RequestStatusBean> Register(Map<String, String> map){
        return service.Register(map);
    }
    //找回密码
    public Observable<RequestStatusBean> FindPwd(Map<String, String> map){
        return service.FindPwd(map);
    }
    //获得微信登录的数据
    public Observable<WechatBean> getWeChatLoginData(String url){
        return service.getWeChatLoginData(url);
    }
    //微信登录
    public Observable<RequestStatusBean> weChatLogin(Map<String,String> map){
        return service.weChatLogin(map);
    }
    //绑定微信号
    public Observable<RequestStatusBean> bindWeChat(Map<String,String> map){
        return service.bindWeChat(map);
    }
    //微信解除绑定
    public Observable<RequestStatusBean> unBindWeChat(int uid){
        return service.unBindWeChat(uid);
    }
    //获得微信登录的数据
    public Observable<WechatInfoBean> getWeChatInfo(String url){
        return service.getWeChatInfo(url);
    }
    //微信解除绑定
    public Observable<RequestStatusBean> showBindWeChart(int uid){
        return service.showBindWeChart(uid);
    }

    //获得工作室
    public Observable<StudioBean> getStudio(){
        return service.getStudio();
    }
    //获得工作室商品列表
    public Observable<StudioShopBean> getStudioShop(Map<String, String> map){
        return service.getStudioShop(map);
    }

    //获得工作室商品列表
    public Observable<RequestStatusBean> focusWork(Map<String, String> map){
        return service.focusWork(map);
    }
    //订单确认
    public Observable<OrderSureBean> orderSure(Map<String,String> map){
        return service.orderSure(map);
    }

    //普通商品和板毛创建订单
    public Observable<RequestStatusBean> createOrder(Map<String,String> map){
        return service.createOrder(map);
    }

    //购物车创建订单
    public Observable<RequestStatusBean> createShopCartOrder(Map<String,String> map){
        return service.createShopCartOrder(map);
    }
    //查询商品列表
    public Observable<SpecialDetailBean> goodsSorted(Map<String,String> map){
        return service.goodsSorted(map);
    }
    //查询商品列表
    public Observable<TuiguangItem1Bean> tuiguangItem1(Map<String,String> map){
        return service.tuiguangItem1(map);
    }
    //查询商品列表
    public Observable<TuiguangItem2Bean> tuiguangItem2(Map<String,String> map){
        return service.tuiguangItem2(map);
    }

}
