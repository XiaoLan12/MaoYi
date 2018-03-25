package com.yizhisha.maoyi.wxapi;

/**
 * 创建人 : skyCracks<br>
 * 创建时间 : 2016-7-18上午10:12:59<br>
 * 版本 :	[v1.0]<br>
 * 类描述 : 微信支付所需参数及配置<br>
 */
public class WeChatConstans {
	
	/** 应用从官方网站申请到的合法appId */
    public static final String APP_ID = "wxcf3dcbc781d32889";
	/** 微信开放平台和商户约定的密钥 */
    public static final String APP_SECRET = "7eef53125b9e6fd5be1dd7294c35f5eb";
    /** 商家向财付通申请的商家id */
    public static final String PARTNER_ID = "1490133942";
    /** 微信公众平台商户模块和商户约定的密钥 */
    public static final String PARTNER_KEY = "zhouhongfushiyouxiangongshi78922";
    /** 微信统一下单接口 */
	public static final String WECHAT_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /** 回调接口 */
    public static final String NOTIFY_URL = "http://www.weixin.qq.com/wxpay/pay.php";

}
