package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class MeInfoBean {
    private String avatar;
    private String nickname;
    private String sex;
    private String status;
    private String info;

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "MeInfoBean{" +
                "avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
