package com.demo;

import com.demo.Bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test {
    public static void main(String[] args) {

        String s = "{\"username\":\"boot\",\"fz_flag\":true,\"wujiang\":[{\"general_id\":\"\",\"general_name\":\"\",\"general_sex\":\"\",\"general_image\":\"http://localhost:8080/image/%E6%9B%B9%E4%B8%95.jpg\"},{\"general_id\":\"\",\"general_name\":\"\",\"general_sex\":\"\",\"general_image\":\"http://localhost:8080/image/%E8%AF%B8%E8%91%9B%E4%BA%AE.jpg\"}]}";
        String[] split = s.split(":");
        String[] split1 = split[2].split(",");
        System.out.println(split1[0]);
    }
}
