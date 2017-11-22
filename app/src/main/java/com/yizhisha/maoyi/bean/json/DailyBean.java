package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/11/21.
 */

public class DailyBean {
    private String spc_id;
    private String spc_name;
    private String spc_litpic;
    private String spc_starttime;
    private String spc_endtime;

    public String getSpc_id() {
        return spc_id;
    }

    public void setSpc_id(String spc_id) {
        this.spc_id = spc_id;
    }

    public String getSpc_name() {
        return spc_name;
    }

    public void setSpc_name(String spc_name) {
        this.spc_name = spc_name;
    }

    public String getSpc_litpic() {
        return spc_litpic;
    }

    public void setSpc_litpic(String spc_litpic) {
        this.spc_litpic = spc_litpic;
    }

    public String getSpc_starttime() {
        return spc_starttime;
    }

    public void setSpc_starttime(String spc_starttime) {
        this.spc_starttime = spc_starttime;
    }

    public String getSpc_endtime() {
        return spc_endtime;
    }

    public void setSpc_endtime(String spc_endtime) {
        this.spc_endtime = spc_endtime;
    }

    @Override
    public String toString() {
        return "DailyBean{" +
                "spc_id='" + spc_id + '\'' +
                ", spc_name='" + spc_name + '\'' +
                ", spc_litpic='" + spc_litpic + '\'' +
                ", spc_starttime='" + spc_starttime + '\'' +
                ", spc_endtime='" + spc_endtime + '\'' +
                '}';
    }
}
