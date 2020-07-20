package org.learn.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.learn.mp.domain.Book;
import org.learn.mp.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: LL
 * @Description: book实体类的service
 * @Date:Create：in 2020/7/20 14:16
 */
@Service
public class BookService {

    /**
     * 继承了mp的basemapper的mapper类
     */
    @Autowired
    BookMapper bookMapper;

    /**
     * 获取所有的书本
     *
     * @return
     */
    public List<Book> getAllList() {
        return bookMapper.selectList(null);
    }

    /**
     * 添加书本
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(Book book) {
        book.setCreateTime(LocalDateTime.now());
        return bookMapper.insert(book);
    }

    /**
     * 根据id搜索书本
     *
     * @param id
     */
    public Book selectById(Long id) {
        return bookMapper.selectById(id);
    }

    /**
     * 根据key-value搜索
     *
     * @param map key为数据库表中的列名，value为值
     * @return
     */
    public List<Book> selectByMap(Map<String, Object> map) {
        return bookMapper.selectByMap(map);
    }

    /**
     * 根据id列表搜索
     *
     * @param idList
     * @return
     */
    public List<Book> selectBatchIds(List<Long> idList) {
        return bookMapper.selectBatchIds(idList);
    }

    /**
     * 根据名字搜索书本
     *
     * @param name
     * @return
     */
    public Book selectOne(String name) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return bookMapper.selectOne(wrapper);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int id) {
        bookMapper.deleteById(id);
    }

    /**
     * 根据id列表删除
     *
     * @param idList
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchIds(List<Long> idList) {
        bookMapper.deleteBatchIds(idList);
    }

    /**
     * 根据key-value删除
     *
     * @param map key为数据库表中的列名，value为值
     */
    public void deleteByMap(Map<String, Object> map) {
        bookMapper.deleteByMap(map);
    }
}
