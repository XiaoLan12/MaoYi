package com.yizhisha.maoyi.bean.json;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */

public class GoodsProductBean {
    private int id;
    private String title;
    private String pid;
    private int tid;
    private String pname;
    private String tname;
    private String litpic;
    private String brand_name;
    private String price;
    private String devi_length;
    private String devi_size;
    private String devi_thickness;
    private String devi_elastic;
    private String favs;
    private String sales;
    private String decription;
    private String[] content;
    private String video;
    private String material;

    private List<GoodsStyleBean> style;
    private List<GoodsStyleBean> style2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<GoodsStyleBean> getStyle() {
        return style;
    }

    public void setStyle(List<GoodsStyleBean> style) {
        this.style = style;
    }

    public List<GoodsStyleBean> getStyle2() {
        return style2;
    }

    public void setStyle2(List<GoodsStyleBean> style2) {
        this.style2 = style2;
    }

    @Override
    public String toString() {
        return "GoodsProductBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pid='" + pid + '\'' +
                ", tid=" + tid +
                ", pname='" + pname + '\'' +
                ", tname='" + tname + '\'' +
                ", litpic='" + litpic + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", price='" + price + '\'' +
                ", devi_length='" + devi_length + '\'' +
                ", devi_size='" + devi_size + '\'' +
                ", devi_thickness='" + devi_thickness + '\'' +
                ", devi_elastic='" + devi_elastic + '\'' +
                ", favs='" + favs + '\'' +
                ", sales='" + sales + '\'' +
                ", decription='" + decription + '\'' +
                ", content=" + Arrays.toString(content) +
                ", video='" + video + '\'' +
                ", material='" + material + '\'' +
                ", style=" + style +
                ", style2=" + style2 +
                '}';
    }
}
