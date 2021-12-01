package common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 注解使用
 */
public class AnnotationUtils {

    protected static <T> void validateParam(T param) throws Exception {
        List<Field> fields = getAllFields(param);
        Iterator var2 = fields.iterator();

        while (var2.hasNext()) {
            Field field = (Field) var2.next();
            if (field.isAnnotationPresent(Required.class)) {
                field.setAccessible(true);
                Object value = null;

                value = field.get(param);

                if (value == null) {
                    StringBuilder errorMsg = new StringBuilder();
                    errorMsg.append(field.getName());
                    errorMsg.append("不能为空");
                    throw new Exception(errorMsg.toString());
                }
            }
        }

    }

    protected static List<Field> getAllFields(Object object) {
        Class clazz = object.getClass();

        ArrayList fieldList;
        for (fieldList = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
            fieldList.addAll(new ArrayList(Arrays.asList(clazz.getDeclaredFields())));
        }

        return fieldList;
    }

}
