package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public class TuiguangItem1Bean {
    private List<Info> info ;

    private String status;

    private String url;

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class Info {

        private String record_id;

        private int record_uid;

        private int record_tid;

        private int record_type;

        private String record_money;

        private String record_money_left;

        private String record_detail;

        private long record_addtime;


        public void setRecord_id(String record_id){
            this.record_id = record_id;
        }
        public String getRecord_id(){
            return this.record_id;
        }

        public void setRecord_uid(int record_uid){
            this.record_uid = record_uid;
        }
        public int getRecord_uid(){
            return this.record_uid;
        }

        public void setRecord_tid(int record_tid){
            this.record_tid = record_tid;
        }
        public int getRecord_tid(){
            return this.record_tid;
        }

        public void setRecord_type(int record_type){
            this.record_type = record_type;
        }
        public int getRecord_type(){
            return this.record_type;
        }

        public void setRecord_money(String record_money){
            this.record_money = record_money;
        }
        public String getRecord_money(){
            return this.record_money;
        }

        public void setRecord_money_left(String record_money_left){
            this.record_money_left = record_money_left;
        }
        public String getRecord_money_left(){
            return this.record_money_left;
        }

        public void setRecord_detail(String record_detail){
            this.record_detail = record_detail;
        }
        public String getRecord_detail(){
            return this.record_detail;
        }

        public void setRecord_addtime(long record_addtime){
            this.record_addtime = record_addtime;
        }
        public long getRecord_addtime(){
            return this.record_addtime;
        }

    }
}
