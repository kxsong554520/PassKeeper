package com.example.passkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DBNAME ="passkeeper";
    private static final String TABLENAME ="pkeeper";
    private static final String COLID = "id";
    private static final String COLENTITI = "entiti";
    private static final String COLUSERNAME = "username";
    private static final String COLPASSWORD = "password";
    private static final String COLDESCRIPTION = "description";

    private static final int DBVERSION = 1;

    public DBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABLENAME + "("
                + COLID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLENTITI + " TEXT,"
                + COLUSERNAME + " TEXT,"
                + COLPASSWORD + " TEXT,"
                + COLDESCRIPTION + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS "+ TABLENAME;

        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList<password> getAllEntiti(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM "+TABLENAME,null);
        ArrayList<password> entitiList = new ArrayList<>();
        if (rs.moveToFirst()){
            do {
                entitiList.add(new password(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4) ));
            }while(rs.moveToNext());
        }
        rs.close();
        if(entitiList.size()<1){
            entitiList.add(new password("No Data", "Found", "in", "Database"));
        }
        return entitiList;
    }

    public long addNewEntiti (String entiti, String username, String password, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLENTITI, entiti);
        cv.put(COLUSERNAME, username);
        cv.put(COLPASSWORD, password);
        cv.put(COLDESCRIPTION, description);

        long rValue = db.insert(TABLENAME,null, cv);
        return rValue;
    }

    public long updateEntiti (String oldEntiti, String newEntiti, String newUsername, String newPassword, String newDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLENTITI, newEntiti);
        cv.put(COLUSERNAME, newUsername);
        cv.put(COLPASSWORD, newPassword);
        cv.put(COLDESCRIPTION, newDescription);

        long rValue = db.update(TABLENAME, cv,"entiti=?", new String[]{oldEntiti});
        return rValue;
    }

    public long delEntiti (String oldEntiti){
        SQLiteDatabase db = this.getWritableDatabase();

        long rValue = db.delete(TABLENAME,"entiti=?", new String[]{oldEntiti});
        return rValue;
    }
}
