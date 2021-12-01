package common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    public BeanUtil() {
    }

    public static Map<String, Object> convert2map(Object bean, boolean onlySelfField) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> returnMap = new HashMap();
        Method[] methods;
        if (onlySelfField) {
            methods = bean.getClass().getDeclaredMethods();
        } else {
            methods = bean.getClass().getMethods();
        }

        Method[] var4 = methods;
        int var5 = methods.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method method = var4[var6];
            String methodName = method.getName();
            if (methodName.contains("get")) {
                Object value = method.invoke(bean);
                String key = methodName.substring(methodName.indexOf("get") + 3);
                if (!"Class".equals(key)) {
                    Object temp = key.substring(0, 1).toString().toLowerCase();
                    key = key.substring(1);
                    key = temp + key;
                    returnMap.put(key, value);
                }
            }
        }

        return returnMap;
    }

}
