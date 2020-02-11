package com.workpath.bookstreamapp.moudles.user;

import com.workpath.bookstreamapp.moudles.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayChen on 2020/2/5.
 */

public class AllUserBean extends BaseBean {

    private List<UserMoudle> data; //数据

    public AllUserBean(){

    }

    public AllUserBean(int ecode, String emsg, List<UserMoudle> data) {
        this.ecode=ecode;
        this.emsg = emsg;
        this.data = data;
    }

    public List<UserMoudle> getData() {
        return data;
    }

    public void setData(List<UserMoudle> data) {
        this.data = data;
    }

    public int getEcode() {
        return ecode;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    @Override
    public String toString() {
        return "AllUserBean{" +
                "ecode=" + ecode +
                ", emsg='" + emsg + '\'' +
                ", data=" + data +
                '}';
    }
}
