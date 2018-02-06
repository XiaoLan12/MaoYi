package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SortedListBean {

    private List<SortedsBean> goods;

    public List<SortedsBean> getList() {
        return goods;
    }

    @Override
    public String toString() {
        return "SortedListBean{" +
                "list=" + goods +
                '}';
    }

    public class SortedsBean{
        private String id;
        private String name;
        private List<SortedBean> cat;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SortedBean> getCat() {
            return cat;
        }

        public void setCat(List<SortedBean> cat) {
            this.cat = cat;
        }

        @Override
        public String toString() {
            return "SortedListBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", cat=" + cat +
                    '}';
        }
    }

}
