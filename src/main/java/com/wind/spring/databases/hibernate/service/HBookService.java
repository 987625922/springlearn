package com.wind.spring.databases.hibernate.service;

import com.wind.spring.databases.hibernate.bean.HBook;
import com.wind.spring.databases.hibernate.dao.HBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HBookService {

    @Autowired
    private HBookDao dao;

    public void save(HBook book){
        dao.save(book);
    }

    public List<HBook> list(){
        return dao.list();
    }
}
