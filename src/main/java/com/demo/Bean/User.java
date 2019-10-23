package com.demo.Bean;

import java.util.List;

public class User {

    String username;
    String password;
    String image;
    Integer userReadymsg;   //-1没人  0没准备   1准备了
    Integer userRoomNumber;     //用户房间号
    boolean fz_flag;    //是否房主
    List<General> wujiang_list;    //一个用户有很多武将

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", userReadymsg=" + userReadymsg +
                ", userRoomNumber=" + userRoomNumber +
                ", fz_flag=" + fz_flag +
                ", wujiang_list=" + wujiang_list +
                '}';
    }

    public Integer getUserRoomNumber() {
        return userRoomNumber;
    }

    public void setUserRoomNumber(Integer userRoomNumber) {
        this.userRoomNumber = userRoomNumber;
    }

    public Integer getUserReadymsg() {
        return userReadymsg;
    }

    public void setUserReadymsg(Integer userReadymsg) {
        this.userReadymsg = userReadymsg;
    }

    public boolean isFz_flag() {
        return fz_flag;
    }

    public void setFz_flag(boolean fz_flag) {
        this.fz_flag = fz_flag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<General> getWujiang_list() {
        return wujiang_list;
    }

    public void setWujiang_list(List<General> wujiang_list) {
        this.wujiang_list = wujiang_list;
    }
}
