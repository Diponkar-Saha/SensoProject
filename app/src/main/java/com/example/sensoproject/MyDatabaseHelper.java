package com.example.sensoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "myTable";   // Table Name
    private static final int DATABASE_Version = 4;
    private static final String ID="_id";
    private static final String TIME="_created_time";   // Column I (Primary Key)
    private static final String NAME = "proximity_sensor";
    private static final String NAME1 = "acc_sensor";
    private static final String NAME2 = "gy_sensor";
    private static final String NAME3 = "light_sensor";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TIME+" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  "+NAME+" VARCHAR(255), "+NAME1+" VARCHAR(255), "+NAME2+" VARCHAR(255), "+NAME3+" VARCHAR(255) );";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;


    public MyDatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            //
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }
    public long insertData(String name,String name1,String name2,String name3)
    {
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(NAME1, name1);
        contentValues.put(NAME2, name2);
        contentValues.put(NAME3, name3);
      //  contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(TABLE_NAME, null , contentValues);
        return id;
    }
    Cursor readAllData(){
        String query="select * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;

    }

//    public String getData()
//    {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
//        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
//        StringBuffer buffer= new StringBuffer();
//        while (cursor.moveToNext())
//        {
//            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
//            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
//            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
//            buffer.append(cid+ "   " + name + "   " + password +" \n");
//        }
//        return buffer.toString();
//    }
}
