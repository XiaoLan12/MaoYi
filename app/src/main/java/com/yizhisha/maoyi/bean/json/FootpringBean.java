package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/14.
 */

public class FootpringBean {
    private List<History> history ;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public class History {
        private String id;

        private String title;

        private String pid;

        private String tid;

        private String pname;

        private String tname;

        private String litpic;

        private String brand_name;

        private String price;

        private String price_real;

        private String devi_length;

        private String devi_size;

        private String devi_thickness;

        private String devi_elastic;

        private String favs;

        private String sales;

        private String description;

        private String content;

        private String material;

        private String video;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_real() {
            return price_real;
        }

        public void setPrice_real(String price_real) {
            this.price_real = price_real;
        }

        public String getDevi_length() {
            return devi_length;
        }

        public void setDevi_length(String devi_length) {
            this.devi_length = devi_length;
        }

        public String getDevi_size() {
            return devi_size;
        }

        public void setDevi_size(String devi_size) {
            this.devi_size = devi_size;
        }

        public String getDevi_thickness() {
            return devi_thickness;
        }

        public void setDevi_thickness(String devi_thickness) {
            this.devi_thickness = devi_thickness;
        }

        public String getDevi_elastic() {
            return devi_elastic;
        }

        public void setDevi_elastic(String devi_elastic) {
            this.devi_elastic = devi_elastic;
        }

        public String getFavs() {
            return favs;
        }

        public void setFavs(String favs) {
            this.favs = favs;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }
    }
}
