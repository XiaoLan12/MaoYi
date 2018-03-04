package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SortedBean {
    private String cat_id;
    private String cat_name;
    private String cat_litpic;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_litpic() {
        return cat_litpic;
    }

    public void setCat_litpic(String cat_litpic) {
        this.cat_litpic = cat_litpic;
    }

    @Override
    public String toString() {
        return "SortedBean{" +
                "cat_id='" + cat_id + '\'' +
                ", cat_name='" + cat_name + '\'' +
                ", cat_litpic='" + cat_litpic + '\'' +
                '}';
    }

    public SortedBean(String cat_id, String cat_name, String cat_litpic) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_litpic = cat_litpic;
    }
}
