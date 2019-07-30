package com.wind.annotation.product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    public static void main(String args[]) {
        Filter f1 = new Filter();
        f1.setId(10);//查询id为10的用户

        Filter f2 = new Filter();
        f2.setUserName("username");//模糊查询用户名为username的用户

        Filter f3 = new Filter();
        f3.setEmail("email@sina.com,two@sina.com,777@qq.com");

        String sql1 = query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    private static String query(Filter f) {
        StringBuilder sb = new StringBuilder();
        //1.获取class
        Class c = f.getClass();
        //2.获取到table的名字
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        sb.append("select * from").append(tableName).append("where 1 = 1");
        //3.遍历所有的字段
        Field[] fArray = c.getDeclaredFields();
        for (Field field : fArray) {
            //4.处理每个字段对应的sql
            //4.1.拿到字段名
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            //4.2 拿到字段的值
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()
                    + filedName.substring(1);
            String fieldValue = null;
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue = (String) getMethod.invoke(f, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //4.3 拼装sql
            sb.append("and").append(filedName).append("=").append(fieldValue);
        }
        return sb.toString();
    }


}