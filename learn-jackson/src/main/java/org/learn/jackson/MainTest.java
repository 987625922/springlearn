package org.learn.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.entity.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @Author: LL
 * @Description: jsckson的学习类
 * @Date:Create：in 2020/6/29 13:30
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/application-base.xml"})
public class MainTest {

    /**
     * 基于对象绑定的ObjectMapper使用示例
     * <p>
     * 备注：
     * ObjectMapper 通过 writeValue 系列方法将 java 对象序列化
     * 为 json，并 将 json 存 储成不同的格式，
     * String（writeValueAsString），Byte Array（writeValueAsString），
     * Writer， File，OutStream 和 DataOutput。
     * <p>
     * ObjectMapper 通过 readValue 系列方法从不同的数据源像 String ，
     * Byte Array， Reader，File，URL， InputStream 将
     * json 反序列化为 java 对象。
     */
    @Test
    public void test() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Bean bean = new Bean();
        bean.setName("小明").setAge(12);
        //writerWithDefaultPrettyPrinter 美化josnString
        String jsonString = mapper
//                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(bean);
        log.info("Bean转换成String：{}", jsonString);
        Bean deserializedBean = mapper.readValue(jsonString, Bean.class);
        log.info("String转换成Bean：{}", deserializedBean);
    }

    /**
     * jackson 配置信息使用示例
     */
    @Test
    public void testConfig() {
        ObjectMapper mapper = new ObjectMapper();
        //在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        //在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //在序列化时忽略值为 null 的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略值为默认值的属性
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
    }

}
