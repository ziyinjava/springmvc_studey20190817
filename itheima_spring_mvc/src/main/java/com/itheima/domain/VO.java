package com.itheima.domain;

import java.util.List;
import java.util.Map;

public class VO {

    private List<User> userList;

    private Map<String,User> userMap;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }


    @Override
    public String toString() {
        return "VO{" + "userList=" + userList + ", userMap=" + userMap + '}';
    }
}
