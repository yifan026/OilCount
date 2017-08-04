package com.example.yfc.stockcountapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YFC on 2016/6/11.
 */
public class MyDBHelper extends SQLiteOpenHelper {


    public static  String table_name = "OilData";
    // 資料庫名稱
    public static final String DATABASE_NAME = "MyDB";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    // 建構子，在一般的應用都不需要修改
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new MyDBHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


              // 建立應用程式需要的表格

           final String create = "CREATE TABLE IF NOT EXISTS "
                + table_name + " ("
                + "OIL" + " REAL PRIMARY KEY NOT NULL, "
                + "PRICE_SITUATION" + " REAL NOT NULL, "
                + "DISCOUNT_SITUATION" + " REAL NOT NULL, "
                + "RESULT_SITUATION" + " REAL NOT NULL"
                + ")";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格

        final String drop = "DROP TABLE IF EXISTS " + table_name;
        db.execSQL(drop);

        // 呼叫onCreate建立新版的表格
        onCreate(db);

    }



}
