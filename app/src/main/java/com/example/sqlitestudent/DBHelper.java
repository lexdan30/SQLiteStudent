package com.example.sqlitestudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper
{

    public DBHelper(Context c, String dbName, int dbVer)
    {
        super(c, dbName, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase dbC)
    {
        String sql="CREATE TABLE StudentFile(id integer primary key not null,first text,last text , middle text, course text, year text)";
        dbC.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase dbC,int oldVer,int newVer)
    {
        String sql="DROP TABLE IF EXISTS StudentFile";

        dbC.execSQL(sql);
        onCreate(dbC);
    }
}
