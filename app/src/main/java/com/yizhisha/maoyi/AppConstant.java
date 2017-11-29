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
    public static final int UID=3;
    public static MeInfoBean meInfoBean=null;

    //头像前缀
    public static final String HEAD_IMG_URL="http://dyc.maozhiwang.com/data/attached/avatar/";
    //评论图片前缀

    public static final String COMENT_IMG_URL="http://dyc.maozhiwang.com/data/attached/comment/";
    //轮播前缀
    public static final String BANNER_IMG_URL="http://dyc.maozhiwang.com/data/attached/special/";
    //商品图片
    public static final String PRUDUCT_IMG_URL="http://dyc.maozhiwang.com/data/attached/goods/";
    public static List<SortedListBean> sortedBeanList=new ArrayList<>();
 }
