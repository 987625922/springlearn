package com.learn.springboot.multiple.dao.mapper.second;

import com.learn.springboot.multiple.dao.bean.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    List<Teacher> selectList();
}
