package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/11/24.
 */

public class GoodsStyleBean {
    private String size;
    private String color;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "GoodsStyleBean{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
