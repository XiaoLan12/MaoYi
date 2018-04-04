package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class MeInfoBean {
    private String avatar;
    private String nickname;
    private int sex;
    private long regtime;
    private String invitecode;
    private String parentcode;
    private String status;
    private String info;
    private int[] orderCount;

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public int getSex() {
        return sex;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int[] getOrderCount() {
        return orderCount;
    }

    public long getRegtime() {
        return regtime;
    }

    public void setRegtime(long regtime) {
        this.regtime = regtime;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public void setOrderCount(int[] orderCount) {
        this.orderCount = orderCount;
    }
}
