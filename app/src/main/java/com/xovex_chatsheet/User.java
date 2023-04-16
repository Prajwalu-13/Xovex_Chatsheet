package com.xovex_chatsheet;

public class User {
    String Msg;
    String PhoneNo;
    public User(String msg, String phoneNo) {
        Msg = msg;
        PhoneNo = phoneNo;
    }

    public String getMsg() {
        return Msg;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }
}
