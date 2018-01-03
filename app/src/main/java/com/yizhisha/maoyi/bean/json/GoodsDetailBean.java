package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/11/24.
 */

public class GoodsDetailBean {
    private String status;
    private String info;
    private GoodsProductBean goods;
    private String favorite;
    private Comment comment;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public GoodsProductBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsProductBean goods) {
        this.goods = goods;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public Comment getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "GoodsDetailBean{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", goods=" + goods +
                ", favorite='" + favorite + '\'' +
                '}';
    }
    public class Comment {

        private int count;
        public void setCount(int count) {
            this.count = count;
        }
        public int getCount() {
            return count;
        }

    }
}
