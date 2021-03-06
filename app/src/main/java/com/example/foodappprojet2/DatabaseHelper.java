package com.example.foodappprojet2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String TAG= "DatabaseHelper";
    private static final String TABLE_NAME ="food_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "strCategory";
    private static final String COL3 = "strCategoryThumb";
    private static final String COL4 = "strCategoryDescription";

    public DatabaseHelper(Context context)
    {
        super(context, TABLE_NAME,null,1);
    }
    /*
    create a table to my database
   */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2 +" TEXT,"+COL3 +" TEXT,"+COL4 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
       onCreate(db);
    }
    /*
    add data to the database
    */
    public boolean addData(String strCategory, String strCategoryThumb, String strCategoryDescription)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,strCategory);
        contentValues.put(COL3,strCategoryThumb);
        contentValues.put(COL4,strCategoryDescription);
        Log.d(TAG,"addData: adding "+strCategory+"to "+TABLE_NAME);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        // if inserted incorrectly is will return -1
         if(result == -1){
             return false;
         }else {
             return true;
         }
    }
    /*
    return all the data from the database
     */
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
