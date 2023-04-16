package com.xovex_chatsheet;

public class Information {

    private  String msg;
    private String phoneNo;

    public Information() {
    }

    public Information(String msg, String phoneNo) {
        this.msg = msg;
        this.phoneNo = phoneNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
