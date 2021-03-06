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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @POST("app/ucenter/profileSave/")
    Observable<RequestStatusBean> changeUserInfo(@FieldMap Map<String, String> map);

    //修改用户头像
    @Multipart
    @POST("app/ajax/uploadAvatarPic/")
    Observable<UserHeadBean> changeUserHead(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);

    //收货地址列表
    @GET("app/ucenter/address")
    Observable<GoodsListBean> loadGoodsAddress(@Query("uid") int uid);

    //收货地址保存
    @FormUrlEncoded
    @POST("app/ucenter/addressSave")
    Observable<RequestStatusBean> savaGoodsAddress(@FieldMap Map<String,String> map);

    //修改默认地址
    @GET("app/ucenter/addressDefault")
    Observable<RequestStatusBean> setDefaulsAddress(@QueryMap Map<String, String> param);

    //获得收藏列表
    @FormUrlEncoded
    @POST("app/ucenter/favoriteList/")
    Observable<CollectListBean> getCollectList(@FieldMap Map<String, String> param);

    //添加收藏

    @GET("app/ucenter/favoriteAdd")
    Observable<RequestStatusBean> addCollect(@QueryMap Map<String, String> param);

    //添加收藏
    @GET("app/goods/workshopFav/")
    Observable<RequestStatusBean> workshopFav(@QueryMap Map<String, String> param);

    //取消收藏
    @GET("app/ucenter/favoriteDelete")
    Observable<RequestStatusBean> cacheCollect(@QueryMap Map<String, String> param);

    //我的足迹
    @FormUrlEncoded
    @POST("app/ucenter/historyList")
    Observable<FootpringBean> loadFootprint(@FieldMap Map<String,String> map);

    //删除足迹
    @FormUrlEncoded
    @POST("app/ucenter/historyDelete")
    Observable<RequestStatusBean> clearFootPrint(@FieldMap Map<String,String> map);

    //购物车
    @GET("app/ucenter/shopcartList/")
    Observable<ShopcartListBean> getShoppCartList(@Query("uid") int id);

    //加载单个购物车
    @FormUrlEncoded
    @POST("app/ucenter/shopcartShow")
    Observable<SingleShoppCartBean> loadSingleShpCart(@FieldMap Map<String, String> param);

    //修改购物车
    @FormUrlEncoded
    @POST("app/ucenter/shopcartEdit")
    Observable<RequestStatusBean> changeShopCart(@FieldMap Map<String, String> param);

    //删除购物车商品
    @GET("app/ucenter/shopcartDelete")
    Observable<RequestStatusBean> deleteShoppCart(@QueryMap  Map<String, String> param);

    //购物车下单确认
    @FormUrlEncoded
    @POST("app/order/confirm_shopcart/")
    Observable<ShopCartOrderSureBean> shopCartOrderSure(@FieldMap Map<String, String> param);

    //获得订单列表
    @FormUrlEncoded
    @POST("app/order/orderList")
    Observable<MyOrderBean> getOrderList(@FieldMap  Map<String, String> param);

    //查询订单
    @FormUrlEncoded
    @POST("app/order/orderSelect")
    Observable<MyOrderBean> queryOrderList(@FieldMap Map<String,String> map);

    //获得订单详情
    @FormUrlEncoded
    @POST("app/order/orderDetail")
    Observable<MyOrderBean> getOrderDetails(@FieldMap Map<String,String> map);

    //取消订单
    @FormUrlEncoded
    @POST("app/order/orderDelete")
    Observable<RequestStatusBean> cancelOrder(@FieldMap Map<String,String> map);

    //确认收货
    @FormUrlEncoded
    @POST("app/order/order_receive/")
    Observable<RequestStatusBean> sureGoods(@FieldMap Map<String,String> map);

    //退款申请

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
    Observable<LoginBean> phoneLogin(@FieldMap Map<String,String> map);

    //修改密码
    @FormUrlEncoded
    @POST("app/user/changepassword/")
    Observable<RequestStatusBean> changePwd(@FieldMap Map<String,String> map);

    //手机号绑定
    @GET("app/ucenter/mobile_save/")
    Observable<RequestStatusBean> bindPhone(@QueryMap Map<String,String> map);

    //获得绑定的手机号码
    @GET("app/ucenter/mobile_binding/")
    Observable<RequestStatusBean> loadBindPhone(@Query("uid") int uid);

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

    //我的评论
    @GET("app/ucenter/commentList/")
    Observable<MyCommentBean> loadMyComment(@Query("uid") int uid);
    //发布评论
    @FormUrlEncoded
    @POST("app/ucenter/commentSave/")
    Observable<RequestStatusBean> addComment(@FieldMap Map<String, String> param);

    //发布追评
    @FormUrlEncoded
    @POST("app/ucenter/commentAddSave/")
    Observable<RequestStatusBean> addAddComment(@FieldMap Map<String, String> param);

    //上传评论图片
    @Multipart
    @POST("app/ajax/uploadCommentPic/")
    Observable<CommentPicBean> addCommentPic(@Part MultipartBody.Part file);

    //退款列表
    @GET("app/order/refundList/")
    Observable<RefundBean> loadRefundList(@Query("uid") int uid);

    //退款详情
    @GET("app/order/refundOrder/")
    Observable<RefundDetailBean> loadRefundDetail(@QueryMap Map<String, String> param);

    //撤销退款
    @GET("app/order/refundDel/")
    Observable<RequestStatusBean> refundDel(@QueryMap Map<String, String> param);

    //退款物流信息
    @GET("app/order/expressviewrefund/")
    Observable<ResponseBody> loadRefundExpress(@Query("refundno") String refundno);

    //物流信息
    @GET("app/order/expressView/")
    Observable<ResponseBody> loadExpress(@Query("orderno") String orderno);


    //退款申请
    @FormUrlEncoded
    @POST("app/order/refundApply/")
    Observable<RequestStatusBean> refundApply(@FieldMap Map<String, String> param);

    //退款图片上传
    @Multipart
    @POST("app/ajax/uploadRefundPic/")
    Observable<CommentPicBean> uploadRefundPic(@Part MultipartBody.Part file);

    //提交退货
    @FormUrlEncoded
    @POST("app/order/refundExpress/")
    Observable<RequestStatusBean> refundGood(@FieldMap Map<String, String> param);

    //首页今日专场轮播图
    @GET("app/dailyTopSlider/")
    Observable<WeekTopListBean> getDailyTopSlider();

    //首页今日专场列表
    @GET("app/dailyList/")
    Observable<ListBean<DailyBean>> getDailyList();

    //首页7日轮播图
    @GET("app/weekTop/")
    Observable<WeekTopListBean> getWeekTop();
    //首页7日爆款列表
    @GET("app/weekList/")
    Observable<WeekListBean> getWeekList(@QueryMap Map<String, String> param);

    //首页往期专场
    @GET("app/pastList/")
    Observable<ListBean<DailyBean>> getPastList();

    //产品分类
    @GET("app/sorted/")
    Observable<SortedListBean> getSorted();

    //专题详情列表
    @GET("app/goods/specialGoodsList/")
    Observable<SpecialDetailBean> getSpecialGoodsList(@QueryMap Map<String, String> param);

    //商品详情
    @GET("app/goods/goodsDetail/")
    Observable<GoodsDetailBean> getGoodsDetail(@QueryMap Map<String, String> param);
    //商品详情的同类推荐
    @GET("app/recommend/")
    Observable<SimilarRecommenListBean> getSimilarRecommen(@Query("tid") int tid);

    //工作室
    @GET("app/goods/workshopList/")
    Observable<StudioBean> getStudio();

    //工作室
    @GET("app/goods/workshopGoods/")
    Observable<StudioShopBean> getStudioShop(@QueryMap Map<String, String> param);

    //收藏工作室
    @GET("app/goods/workshopFav/")
    Observable<RequestStatusBean> focusWork(@QueryMap Map<String, String> param);

    @FormUrlEncoded
    @POST("app/order/confirm")
    Observable<OrderSureBean> orderSure(@FieldMap Map<String, String> param);

    //创建订单
    @FormUrlEncoded
    @POST("app/order/create")
    Observable<RequestStatusBean> createOrder(@FieldMap Map<String, String> param);

    //创建购物车订单
    @FormUrlEncoded
    @POST("app/order/create_shopcart")
    Observable<RequestStatusBean> createShopCartOrder(@FieldMap Map<String, String> param);

    //查询商品列表
    @GET("app/goods/goodsSorted")
    Observable<SpecialDetailBean>  goodsSorted(@QueryMap Map<String, String> param);

    //收入明细
    @FormUrlEncoded
    @POST("app/user/getFinance")
    Observable<TuiguangItem1Bean> tuiguangItem1(@FieldMap Map<String, String> param);
    //人脉资源
    @FormUrlEncoded
    @POST("app/user/getUserSub")
    Observable<TuiguangItem2Bean> tuiguangItem2(@FieldMap Map<String, String> param);
}
