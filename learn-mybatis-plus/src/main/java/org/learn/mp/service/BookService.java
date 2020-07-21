package org.learn.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     * 根据key-value搜索（根据map的键等于值搜索list）
     *
     * @param map key为数据库表中的列名，value为值
     * @return
     */
    public List<Book> selectByMap(Map<String, Object> map) {
        return bookMapper.selectByMap(map);
    }

    /**
     * 根据id列表搜索（根据id的list搜索list）
     *
     * @param idList
     * @return
     */
    public List<Book> selectBatchIds(List<Long> idList) {
        return bookMapper.selectBatchIds(idList);
    }

    /**
     * 根据名字搜索书本(搜索一个item)
     * <p>
     * 如果不止一个就会抛出异常
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
     * 根据书本名模糊搜索（模糊搜索list，QueryWrapper的使用）
     * @param name
     * @return
     */
    public List<Book> selectList(String name){
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return bookMapper.selectList(wrapper);
    }

    /**
     * 只需要搜索表中的几个列，就可以使用这个方法
     * @return
     */
    public List<Map<String, Object>> selectMaps(String name){
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.select("id","name").like("name", name);
        return bookMapper.selectMaps(wrapper);
    }

    /**
     * 输出聚合函数的值(selectMaps的场景)
     * @return
     */
    public List<Map<String, Object>> selectMaps2(String name){
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.select("name","MIN(number) min","MAX(number) max","AVG(number)").groupBy("id").having("name LIKE {0}","%"+name+"%");
        return bookMapper.selectMaps(wrapper);
    }

    /**
     * 返回总记录数
     * @param name
     * @return
     */
    public Integer selectCount(String name){
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return bookMapper.selectCount(wrapper);
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

    /**
     * 根据id更改一个课本
     */
    public void updateById(Book book) {
        bookMapper.updateById(book);
    }
}
