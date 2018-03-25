package com.yizhisha.maoyi.bean;

import java.util.List;

/**
 * Created by 小蓝 on 2018/3/20.
 */

public class TypeContentBean {
    private String title;
    private int type;
    private String id;

    private List<ContentBena> contentBenaList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ContentBena> getContentBenaList() {
        return contentBenaList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContentBenaList(List<ContentBena> contentBenaList) {
        this.contentBenaList = contentBenaList;
    }

    public class ContentBena{
        private String title;
        private String imgsrc;
        private int url;
        private int type;
        private int cid;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUrl() {
            return url;
        }

        public void setUrl(int url) {
            this.url = url;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }
    }
}
