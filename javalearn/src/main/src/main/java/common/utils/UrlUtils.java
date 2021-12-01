package common.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UrlUtils {

    /**
     * 构建Get请求的参数
     *
     * @param url
     * @param paramMap
     * @return
     */
    protected static String buildUrl(StringBuilder url, Map<String, Object> paramMap) {
        url.append("?");
        Set<String> keySet = paramMap.keySet();
        int index = 1;

        for (Iterator var4 = keySet.iterator(); var4.hasNext(); ++index) {
            String key = (String) var4.next();
            Object value = paramMap.get(key);
            url.append(key);
            url.append("=");
            url.append(value);
            if (index != keySet.size()) {
                url.append("&");
            }
        }

        return url.toString();
    }
}
