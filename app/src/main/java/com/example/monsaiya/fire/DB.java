package com.example.monsaiya.fire;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Monsaiya on 21/2/2561.
 */

public class DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fire.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "fire";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_NUMBER = "number";
    public static final String COL_TIME = "time";
    public static final String COL_MONEY = "money";
    public static final String COL_SUM = "sum";
    public static final String COL_DATE = "date";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_NUMBER + " TEXT, "
            + COL_TIME + " TEXT, "
            + COL_MONEY + " TEXT, "
            + COL_SUM + " TEXT, "
            + COL_DATE + " TEXT)";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);

    }

    private void insertInitialData(SQLiteDatabase db) {

        ContentValues cv = new ContentValues();
        cv.put(DB.COL_TITLE, "คอมพิวเตอร์");
        cv.put(DB.COL_NUMBER, "1800");
        cv.put(DB.COL_TIME, "2");
        cv.put(DB.COL_MONEY, "4");
        cv.put(DB.COL_SUM, "14.4");
        cv.put(DB.COL_DATE, "22-ก.พ.-2561");
        db.insert(TABLE_NAME, null, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


}
