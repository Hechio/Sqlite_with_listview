package com.stevehechio.course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CourseDatabase";
    public static final String TABLE_NAME = "CourseTable";
    public static final String col1 = "ID";
    public static final String col2 = "CourseCode";
    public static final String col3 = "CourseTitle";
    public static final String col4 = "CourseLec";


    public CourseDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable = "CREATE TABLE " +TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, CourseCode TEXT, CourseTitle TEXT, CourseLec TEXT)";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );

    }

    public boolean insertData(String CourseCode, String CourseTitle, String CourseLec){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,CourseCode);
        contentValues.put(col3,CourseTitle);
        contentValues.put(col4,CourseLec);

        long result = database.insert(TABLE_NAME,null,contentValues);
        if (result ==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getListDbContents(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor contents = database.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return contents;
    }
}
