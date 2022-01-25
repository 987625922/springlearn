package com.learn.springboot.multiple.dao.mapper.master;

import com.learn.springboot.multiple.dao.bean.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    List<Course> selectList();
}
