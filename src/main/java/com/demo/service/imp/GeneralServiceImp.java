package com.demo.service.imp;

import com.demo.Bean.General;
import com.demo.Dao.GeneralDao;
import com.demo.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralServiceImp implements GeneralService {

    @Autowired
    GeneralDao generalDao;

    @Override
    public List<General> selectAll() {
        List<General> generals = generalDao.selectAll();

        return generals;
    }
}
