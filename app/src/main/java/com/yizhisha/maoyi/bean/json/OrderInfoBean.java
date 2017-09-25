package com.yizhisha.maoyi.bean.json;

/**
 * Created by lan on 2017/9/25.
 */

public class OrderInfoBean {
    private String content;
    private String time;

    public OrderInfoBean(String time, String content) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
