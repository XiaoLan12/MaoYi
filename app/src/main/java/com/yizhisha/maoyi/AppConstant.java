package com.yizhisha.maoyi;

import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/9/22.
 */

public class AppConstant {
    public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";
    public static  int UID=3;
    public static boolean isLogin=false;
    public static MeInfoBean meInfoBean=null;

    //头像前缀
    public static final String HEAD_IMG_URL="http://dyc.maozhiwang.com/data/attached/avatar/";
    //评论图片前缀
    public static final String COMENT_IMG_URL="http://dyc.maozhiwang.com/data/attached/comment/";
    //轮播前缀
    public static final String BANNER_IMG_URL="http://dyc.maozhiwang.com/data/attached/special/";
    //商品图片
    public static final String PRUDUCT_IMG_URL="http://dyc.maozhiwang.com/data/attached/goods/";
    public static final String PRODUCT_VIDEO="http://dyc.maozhiwang.com/data/attached/video/";
    public static List<SortedListBean.SortedsBean> sortedBeanList=new ArrayList<>();


    public static final String WEIXIN_APP_ID="wxcf3dcbc781d32889";
    public static final String  APP_SECRET="7eef53125b9e6fd5be1dd7294c35f5eb";
//    微信Prepayurl：https://api.mch.weixin.qq.com/pay/unifiedorder

}
