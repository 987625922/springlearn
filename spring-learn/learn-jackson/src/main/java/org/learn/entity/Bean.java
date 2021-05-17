package org.learn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: LL
 * @Description: jackson测试用的实体类
 * @Date:Create：in 2020/6/29 13:32
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Bean {
    private String name;
    private Integer age;
}
