package org.learn.mp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author LL
 * @Description: mp学习用的实体类
 */
@Getter
@Setter
@ToString
@TableName("mp_book")
public class Book implements Serializable {

    /**
     * value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
     */
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Long id;
    /**
     * 若没有开启驼峰命名，或者表中列名不符合驼峰规则，可
     * 通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
     *
     * @TableField(value = "last_name", exist = true)
     */
    private String name;
    /**
     * 书本数量
     */
    private int number;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 备注
     */
    private String remark;
}
