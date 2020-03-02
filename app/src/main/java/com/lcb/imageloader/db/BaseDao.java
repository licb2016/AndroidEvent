package com.lcb.imageloader.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.lcb.imageloader.annotation.DbField;
import com.lcb.imageloader.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseDao<T> implements IBaseDao<T> {


    /**
     * 持有数据库操作的引用
     */
    private SQLiteDatabase sqLiteDatabase;

    /**
     * 持有操作数据库所对应的java类型
     */
    private Class<T> entityClass;

    /**
     * 标记：用来表示是否做过初始化操作
     */
    private boolean isInit = false;

    /**
     * 数据库的表明
     */
    private String tableName;

    /**
     * 定义一个缓存空间 （key----字段名称，value：成员变量）
     */
    private HashMap<String, Field> cacheMap;


    protected void init(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;

        if (isInit) {
            return;
        }

        if (entityClass.getAnnotation(DbTable.class) == null) {
            // 反射到类名
            this.tableName = entityClass.getSimpleName();
        } else {
            this.tableName = entityClass.getAnnotation(DbTable.class).value();
        }

        // 执行建表操作
        String createTableSql = getCreateTableSql();
        this.sqLiteDatabase.execSQL(createTableSql);
        cacheMap = new HashMap<>();
        initCacheMap();
        isInit = true;
    }

    private void initCacheMap() {
        // 1、取得所有字段名
        String sql = "select * from " + tableName + " limit 1,0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        String[] columnFields = cursor.getColumnNames();

        // 2、去所有的成员变量
        Field[] declaredFields = entityClass.getDeclaredFields();

        for (Field field : declaredFields) {
            // 打开字段的访问权限
            field.setAccessible(true);
        }

        for (String columnName : columnFields) {
            Field columnField = null;
            for (Field field : declaredFields) {
                String fieldName = null;
                if (field.getAnnotation(DbField.class) != null) {
                    fieldName = field.getAnnotation(DbField.class).value();
                } else {
                    fieldName = field.getName();
                }

                if (columnName.equals(fieldName)) {
                    columnField = field;
                }
            }

            if (columnField != null) {
                cacheMap.put(columnName, columnField);
            }
        }
    }

    private String getCreateTableSql() {
        // create table if not exists tb_user( _id int , name varchar, age int);
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists ");
        sb.append(tableName).append(" (");

        //反射得到所有的成员变量
        Field[] declaredFields = entityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            String fieldName = null;


            if (field.getAnnotation(DbField.class) != null) {
                // 通过注解获取
                fieldName = field.getAnnotation(DbField.class).value();
            } else {
                // 通过反射获取
                fieldName = field.getName();
            }


            if (type == String.class) {
                sb.append(fieldName).append(" text,");
            } else if (type == Integer.class) {
                sb.append(fieldName).append(" integer,");
            } else if (type == int.class) {
                sb.append(fieldName).append(" int,");
            } else if (type == Long.class) {
                sb.append(fieldName).append(" bigint,");
            } else if (type == Double.class) {
                sb.append(fieldName).append(" double,");
            } else if (type == byte[].class) {
                sb.append(fieldName).append(" blob,");
            } else {
                //不支持的类型
                continue;
            }
        }

        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(")");

        return sb.toString();
    }

    @Override
    public long insert(T entity) {
        // 1、封装好ContentValue中需要的数据
        Map<String, String> map = getValues(entity);

        // 2、将Map中的数据转移到ContentValue中
        ContentValues values = getContentValues(map);

        // 3、插入数据
        return sqLiteDatabase.insert(tableName, null, values);
    }

    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            if (value != null) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }

    private Map<String, String> getValues(T entity) {
        HashMap<String, String> map = new HashMap<>();
        for (Field field : cacheMap.values()) {
            field.setAccessible(true);

            try {
                Object obj = field.get(entity);
                if (obj == null) {
                    continue;
                }

                String value = obj.toString();
                String key;
                if (field.getAnnotation(DbField.class) != null) {
                    key = field.getAnnotation(DbField.class).value();
                } else {
                    key = field.getName();
                }

                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


        return map;
    }

    @Override
    public long update(T entity, T where) {
        // sqLiteDatabase.update(tableName,contentValue,"id = ? and name = ?",new String[]{"1","lisi"});

        Map<String, String> map = getValues(entity);
        ContentValues values = getContentValues(map);

        // 条件
        Map<String, String> conditionMap = getValues(where);
        Condition condition = new Condition(conditionMap);


        return sqLiteDatabase.update(tableName, values, condition.whereClause, condition.whereArgs);
    }

    private class Condition {
        private String whereClause;
        private String[] whereArgs;

        public Condition(Map<String, String> map) {
            ArrayList list = new ArrayList();
            StringBuilder sb = new StringBuilder();

            sb.append("1=1");

            Set<String> keys = map.keySet();
            for (String key : keys) {
                String value = map.get(key);
                if (value != null) {
                    sb.append(" and ").append(key).append("=?");
                    list.add(value);
                }
            }

            this.whereClause = sb.toString();
            this.whereArgs = (String[]) list.toArray(new String[list.size()]);
        }

    }

    @Override
    public int delete(T where) {
        Map<String, String> map = getValues(where);
        Condition condition = new Condition(map);
        return sqLiteDatabase.delete(tableName, condition.whereClause, condition.whereArgs);
    }

    @Override
    public List<T> query(T where) {
        return query(where, null, null, null);
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        Map<String, String> map = getValues(where);

        String limitStr = "";
        if (startIndex != null && limit != null) {
            limitStr = startIndex + " , " + limit;
        }

        Condition condition = new Condition(map);

        Cursor cursor = sqLiteDatabase.query(tableName, null, condition.whereClause,
                condition.whereArgs, null, orderBy, limitStr);

        return getResult(cursor, where);
    }

    private List<T> getResult(Cursor cursor, T where) {
        ArrayList list = new ArrayList();
        Object item = null;
        while (cursor.moveToNext()) {
            try {
                item = where.getClass().newInstance();  //因为不知道 new  ? , 所以通过反射方式
                //cacheMap  (字段---成员变量的名字)
                for (Map.Entry<String, Field> entry : cacheMap.entrySet()) {
                    //取列名
                    String columnName = entry.getKey();

                    //以列名拿到列名在游标中的位置
                    int columnIndex = cursor.getColumnIndex(columnName);

                    Field value = entry.getValue();   //id
                    Class<?> type = value.getType();  //Integer
                    if (columnIndex != -1) {  //columnName = "age"
                        if (type == String.class) {
                            value.set(item, cursor.getString(columnIndex));//setid(1)
                        } else if (type == Double.class) {
                            value.set(item, cursor.getDouble(columnIndex));
                        } else if (type == Integer.class) {
                            value.set(item, cursor.getInt(columnIndex));
                        } else if (type == Long.class) {
                            value.set(item, cursor.getLong(columnIndex));
                        } else if (type == byte[].class) {
                            value.set(item, cursor.getBlob(columnIndex));
                        }
                    }
                }
                list.add(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cursor.close();

        return list;
    }


}
