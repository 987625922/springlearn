package com.learn.web;

import com.learn.web.bean.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * Swagger-UI的使用
 *
 * @Api：用于修饰Controller类，生成Controller相关文档信息
 * @ApiOperation：用于修饰Controller类中的方法，生成接口方法相关文档信息
 * @ApiParam：用于修饰接口中的参数，生成接口参数相关文档信息
 * @ApiModelProperty：用于修饰实体类的属性，当实体类是请求参数或返回结果时，直接生成相关文档信息
 */
@Api(tags = "欢迎查看接口", description = "商品品牌管理")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public Object listAll() {
        Book book = new Book();
        book.setId(2);
        book.setName("swagger1的使用");
        return book;
    }


    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object createBrand(@RequestBody Book book) {
        Book result = new Book();
        result.setId(2);
        result.setName("swagger1的使用");
        return result;
    }
}
