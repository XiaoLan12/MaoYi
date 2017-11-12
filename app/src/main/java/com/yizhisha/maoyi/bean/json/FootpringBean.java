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
        private String price;


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



        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice() {
            return price;
        }

    }
}
