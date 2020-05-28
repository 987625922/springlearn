package org.learn.mybatis.dao;

import org.learn.common.bean.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 基于注解的spring mybatis使用
 */
public interface BookAnnotationDao {

    @Select("SELECT id,name,number FROM book ORDER BY id LIMIT #{offset}, #{limit}")
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
