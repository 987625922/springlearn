package jdbc.utils;


import jdbc.annotation.Column;
import jdbc.annotation.Id;
import jdbc.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * sql代码生成类
 *
 */
public class SqlUtil{


    public <T> String queryAll(Class<T> c) {
        String sqlStr = "";
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sqlStr = "SELECT * from " + tableName;
        return sqlStr;
    }

    public <T> String update(T t) {
        StringBuilder sbLeft = new StringBuilder();
        StringBuilder sbRight = new StringBuilder();
        Class c = t.getClass();
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sbLeft.append("UPDATE ").append(tableName).append(" SET ");
        sbRight.append(" WHERE 1=1");
        Field[] fArray = c.getDeclaredFields();
        for (Field field : fArray) {
            boolean fExists = field.isAnnotationPresent(Column.class);
            boolean iExists = field.isAnnotationPresent(Id.class);
            if (!fExists) {
                continue;
            }
            if (iExists) {
                Id id = field.getAnnotation(Id.class);
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                String filedName = field.getName();
                String getMethodName = "get" + filedName.substring(0, 1)
                        .toUpperCase() + filedName.substring(1);
                Object fieldValue = null;
                try {
                    Method getMethod = c.getMethod(getMethodName);
                    fieldValue = getMethod.invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fieldValue == null) {
                    return null;
                }
                sbRight.append(" and ").append(columnName).append(" = ").append(fieldValue);

            } else {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                String filedName = field.getName();
                String getMethodName = "get" + filedName.substring(0, 1)
                        .toUpperCase() + filedName.substring(1);
                Object fieldValue = null;
                try {
                    Method getMethod = c.getMethod(getMethodName);
                    fieldValue = getMethod.invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fieldValue == null) {
                    continue;
                }
                if (fieldValue instanceof String) {
                    sbLeft.append(columnName).append("='").append(fieldValue).append("',");
                } else if (fieldValue instanceof Integer) {
                    sbRight.append(fieldValue).append(",");
                } else if (fieldValue instanceof Long) {
                    sbRight.append(fieldValue).append(",");
                }
            }
        }
        sbLeft.replace(sbLeft.length() - 1, sbLeft.length(), "");
        sbLeft.append(sbRight);
        return sbLeft.toString();
    }

    public <T> String delete(T t) {
        StringBuilder sb = new StringBuilder();
        Class c = t.getClass();
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("DELETE FROM ").append(tableName).append(" where 1 = 1 ");
        Field[] fArray = c.getDeclaredFields();
        for (Field field : fArray) {
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1)
                    .toUpperCase() + filedName.substring(1);
            Object fieldValue = null;
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue = getMethod.invoke(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fieldValue == null) {
                continue;
            }
            sb.append(" and ").append(columnName);
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    String[] values = ((String) fieldValue).split(",");
                    sb.append("in(");
                    for (String v : values) {
                        sb.append("'").append(v).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append(" = ").append("'").append(fieldValue).append("'");
                }
            } else if (fieldValue instanceof Integer) {
                sb.append("=").append(fieldValue);
            } else if (fieldValue instanceof Long) {
                sb.append("=").append(fieldValue);
            }
        }
        return sb.toString();
    }

    public <T> String insert(T t) {
        StringBuilder sbLeft = new StringBuilder();
        StringBuilder sbRight = new StringBuilder();
        Class c = t.getClass();
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sbLeft.append("INSERT INTO ").append(tableName).append("(");
        sbRight.append(" VALUES(");
        Field[] fArray = c.getDeclaredFields();
        for (Field field : fArray) {
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1)
                    .toUpperCase() + filedName.substring(1);
            Object fieldValue = null;
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue = getMethod.invoke(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fieldValue == null) {
                continue;
            }
            sbLeft.append(columnName).append(",");
            if (fieldValue instanceof String) {
                sbRight.append("'").append(fieldValue).append("',");
            } else if (fieldValue instanceof Integer) {
                sbRight.append(fieldValue).append(",");
            } else if (fieldValue instanceof Long) {
                sbRight.append(fieldValue).append(",");
            }
        }
        sbLeft.replace(sbLeft.length() - 1, sbLeft.length(), ")");
        sbRight.replace(sbRight.length() - 1, sbRight.length(), ")");
        sbLeft.append(sbRight);
        return sbLeft.toString();
    }

    public <T> String query(T t) {
        StringBuilder sb = new StringBuilder();
        Class c = t.getClass();
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("select * from ").append(tableName).append(" where 1 = 1");
        Field[] fArray = c.getDeclaredFields();
        for (Field field : fArray) {
            boolean fExists = field.isAnnotationPresent(Column.class);
            boolean iExists = field.isAnnotationPresent(Id.class);
            if (!fExists) {
                continue;
            }
            if (iExists){
                Id id = field.getAnnotation(Id.class);
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                String filedName = field.getName();
                String getMethodName = "get" + filedName.substring(0, 1)
                        .toUpperCase() + filedName.substring(1);
                Object fieldValue = null;
                try {
                    Method getMethod = c.getMethod(getMethodName);
                    fieldValue = getMethod.invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fieldValue == null) {
                    return null;
                }
                sb.append(" and ").append(columnName).append(" = ").append(fieldValue);

            }else {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                String filedName = field.getName();
                String getMethodName = "get" + filedName.substring(0, 1)
                        .toUpperCase() + filedName.substring(1);
                Object fieldValue = null;
                try {
                    Method getMethod = c.getMethod(getMethodName);
                    fieldValue = getMethod.invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fieldValue == null) {
                    continue;
                }
                sb.append(" and ").append(columnName);
                if (fieldValue instanceof String) {
                    if (((String) fieldValue).contains(",")) {
                        String[] values = ((String) fieldValue).split(",");
                        sb.append("in(");
                        for (String v : values) {
                            sb.append("'").append(v).append("'").append(",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append(")");
                    } else {
                        sb.append("=").append("'").append(fieldValue).append("'");
                    }
                } else if (fieldValue instanceof Integer) {
                    sb.append("=").append(fieldValue);
                } else if (fieldValue instanceof Long) {
                    sb.append("=").append(fieldValue);
                }
            }
        }
        return sb.toString();
    }
}
