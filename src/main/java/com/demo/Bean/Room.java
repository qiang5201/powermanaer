package com.demo.Bean;

import java.util.List;

public class Room {

    Integer room_number;//房间号
    User fangzhu;   //房主
    List<User> users;   //其他人(最多7人)

    @Override
    public String toString() {
        return "Room{" +
                "room_number=" + room_number +
                ", fangzhu=" + fangzhu +
                ", users=" + users +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getRoom_number() {
        return room_number;
    }

    public void setRoom_number(Integer room_number) {
        this.room_number = room_number;
    }

    public User getFangzhu() {
        return fangzhu;
    }

    public void setFangzhu(User fangzhu) {
        this.fangzhu = fangzhu;
    }
}
