package common.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HttpURLConnection的使用
 * http和https
 */
public class HttpUtil {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 10000;
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public static final String DELETE_METHOD = "DELETE";
    private static final String CHARSET_UTF8 = "UTF-8";

    public HttpUtil() {
    }

    public static String doGet(String url) throws Exception {
        HttpURLConnection connection = createConnection(url);
        return doGetRequest(connection, url);
    }

    public static String doPost(String url, String requestBody) throws Exception {
        HttpURLConnection connection = createConnection(url);

        String var4;
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(requestBody);
            writer.close();
            var4 = handleResponse(connection, url, "POST", requestBody);
        } catch (Exception var8) {
            throw var8;
        } finally {
            closeConnection(connection);
        }

        return var4;
    }

    public static String doMethodRequest(String url, Map<String, Object> params, String method) throws Exception {
        HttpURLConnection connection = createConnection(url);

        String var14;
        try {
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            StringBuilder paramsStr = new StringBuilder();
            if (params != null && !params.isEmpty()) {
                Set<String> keys = params.keySet();
                int index = 1;

                for (Iterator var7 = keys.iterator(); var7.hasNext(); ++index) {
                    String key = (String) var7.next();
                    paramsStr.append(key);
                    paramsStr.append("=");
                    paramsStr.append(params.get(key));
                    if (index != keys.size()) {
                        paramsStr.append("&");
                    }
                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(paramsStr.toString());
                writer.close();
            }

            var14 = handleResponse(connection, url, method, paramsStr.toString());
        } catch (Exception var12) {
            throw var12;
        } finally {
            closeConnection(connection);
        }

        return var14;
    }

    public static String doHttpsGetAllowAllSSL(String url) throws Exception {
        HttpsURLConnection connection = createHttpsConnection(url);
        return doGetRequest(connection, url);
    }

    private static void closeConnection(HttpURLConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }

    }

    private static String doGetRequest(HttpURLConnection connection, String url) throws Exception {
        String var2;
        try {
            connection.setRequestMethod("GET");
            connection.connect();
            var2 = handleResponse(connection, url, "GET", (String) null);
        } catch (Exception var6) {
            throw new Exception(var6);
        } finally {
            closeConnection(connection);
        }

        return var2;
    }

    private static String handleResponse(HttpURLConnection connection, String url, String method, String paramStr)
            throws Exception {
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream is = connection.getInputStream();
            return io2String(is);
        } else {
            String errorResponse = io2String(connection.getErrorStream());
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("http request fail");
            errorMsg.append(System.lineSeparator());
            errorMsg.append("response code:");
            errorMsg.append(responseCode);
            errorMsg.append(System.lineSeparator());
            errorMsg.append("url:");
            errorMsg.append(url);
            errorMsg.append(System.lineSeparator());
            errorMsg.append("method:");
            errorMsg.append(method);
            errorMsg.append(System.lineSeparator());
            if (paramStr != null && !paramStr.isEmpty()) {
                errorMsg.append("param:");
                errorMsg.append(paramStr);
                errorMsg.append(System.lineSeparator());
            }

            errorMsg.append("response error message:");
            errorMsg.append(errorResponse);
            throw new Exception(errorMsg.toString());
        }
    }

    private static HttpsURLConnection createHttpsConnection(String url) throws Exception {
        try {
            HTTPSTrustManager.allowAllSSL();
        } catch (KeyManagementException | NoSuchAlgorithmException var4) {
            throw new Exception(var4);
        }

        HttpsURLConnection conn = null;

        try {
            conn = (HttpsURLConnection) (new URL(url)).openConnection();
        } catch (IOException var3) {
            closeConnection(conn);
        }

        conn.setConnectTimeout(5000);
        conn.setReadTimeout(10000);
        return conn;
    }

    private static HttpURLConnection createConnection(String url) throws Exception {
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) (new URL(url)).openConnection();
        } catch (IOException var6) {
            throw new Exception(var6);
        } finally {
            closeConnection(conn);
        }

        conn.setConnectTimeout(5000);
        conn.setReadTimeout(10000);
        return conn;
    }

    private static String io2String(InputStream inputStream) throws IOException {
        String result = null;
        InputStream is = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;
            StringBuffer sb = new StringBuffer();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            result = sb.toString();
            return result;
        } catch (IOException var16) {
            throw var16;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException var15) {
                br = null;
            }

            try {
                if (is != null) {
                    ((InputStream) is).close();
                }
            } catch (IOException var14) {
                is = null;
            }

        }
    }
}
