package com.lcb.imageloader.db;


import android.database.sqlite.SQLiteDatabase;

public class BaseDaoFactory {


    private static final BaseDaoFactory ourInstance = new BaseDaoFactory();


    private SQLiteDatabase sqLiteDatabase;

    private String sqliteDatabasePath;

    public static BaseDaoFactory getInstance() {
        return ourInstance;
    }

    public BaseDaoFactory() {
        sqliteDatabasePath = "/data/data/com.lcb.imageloader/mydb1.db";
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath, null);
    }

    public <T> BaseDao<T> getBaseDao(Class<T> entityClass) {
        BaseDao baseDao = null;
        try {
            baseDao= BaseDao.class.newInstance();
            baseDao.init(sqLiteDatabase, entityClass);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return baseDao;
    }

}
