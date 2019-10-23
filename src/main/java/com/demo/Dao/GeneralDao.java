package com.demo.Dao;

import com.demo.Bean.General;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GeneralDao {

    //得到所有武将
    List<General> selectAll();
}
