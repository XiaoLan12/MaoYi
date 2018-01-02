package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class StudioShopBean {
    private Workshop workshop;
    private String status;
    private String info;
    private List<Goods> goods;
    private String favorite;
    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
    public Workshop getWorkshop() {
        return workshop;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
    public List<Goods> getGoods() {
        return goods;
    }

    public String getFavorite() {
        return favorite;
    }

    public class Workshop {

        private String workshop;
        private String avatar;
        private String linkman;
        private String address;
        private String major;
        private String addtime;
        private String album;
        private String cover;
        private String background;
        public void setWorkshop(String workshop) {
            this.workshop = workshop;
        }
        public String getWorkshop() {
            return workshop;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
        public String getAvatar() {
            return avatar;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }
        public String getLinkman() {
            return linkman;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setMajor(String major) {
            this.major = major;
        }
        public String getMajor() {
            return major;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
        public String getAddtime() {
            return addtime;
        }

        public void setAlbum(String album) {
            this.album = album;
        }
        public String getAlbum() {
            return album;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
        public String getCover() {
            return cover;
        }

        public void setBackground(String background) {
            this.background = background;
        }
        public String getBackground() {
            return background;
        }

    }

    public class Goods {

        private String id;
        private String title;
        private String litpic;
        private String price;
        private String sales;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
        public String getLitpic() {
            return litpic;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getPrice() {
            return price;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }
        public String getSales() {
            return sales;
        }

    }

}
