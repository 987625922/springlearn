package org.learn.aop.xml;

import lombok.extern.slf4j.Slf4j;
import org.learn.aop.annotation.AnnotationPointCutDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class XmlDao {



    public void save(){
        log.info("Spring xml 配置的切点方法 XmlDao类的save方法");
    }
}
