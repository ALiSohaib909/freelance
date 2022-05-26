package com.example.freelance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {


    String DB_PATH = null;
    private static String DB_NAME = "freelancerDb.sqlite";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    Cursor c3, c2;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        //  myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public Cursor a(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, null, null, null, null, null, null);
    }

    public Cursor query3(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, null, selection, null, null, null, null);

    }

    public Cursor account(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query("Surah", columns, selection, null, null, null, null);

    }

    public Cursor api(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, columns, selection, selectionArgs, null, null, null, null);
    }

    public Cursor users(String table, String[] columns) {
        return myDataBase.rawQuery("Select * From tbl_Users ", null);
    }

    public Cursor get(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, columns, selection, selectionArgs, null, null, null);
    }

    public void createUserInterestTable(String s) {
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS tbl_interset(ID INTEGER PRIMARY KEY UNIQUE,loginId INTEGER, UserEmail TEXT, interest TEXT);");
    }

    public void createJobTable(String s) {
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS tbl_jobs(job_id INTEGER PRIMARY KEY UNIQUE, job_title TEXT, description TEXT);");
    }

    public void createLoginTable(String s) {
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS tbl_loginUser(id INTEGER, name TEXT,email TEXT, type TEXT);");
    }

//    public void create2(String s) {
//        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS Feelings(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, FeelingEnglish TEXT, FeelingUrdu TEXT );");
//    }

    public Cursor RangeBy(String table, String[] columns) {
        return myDataBase.rawQuery(table, columns);
    }

    public Cursor show(String table, String[] columns) {
        return myDataBase.rawQuery(table, columns);

    }

    public long insert(ContentValues contentValues) {

        return myDataBase.insert("", null, contentValues);
        // myDataBase.execSQL("INSERT INTO (ID , Emotion , EText );");
    }

    public long insertIntoTable(String table, ContentValues contentValues) {

        return myDataBase.insert(table, null, contentValues);
    }

    public Cursor update1(String table, String[] a) {

        return myDataBase.rawQuery("Update dbo.tbl_Users SET Id = 5 Where Name= 'Ali' "
                , null);
    }

    public int update(String TableName, ContentValues cv , String id) {
       return myDataBase.update(TableName, cv, "id = ?", new String[]{id});
    }

    public long insert2(String table, ContentValues contentValues) {

        return myDataBase.insert(table, null, contentValues);
    }

    public int delete() {
        return myDataBase.delete("Hospital",
                "Name = Family Health Hospital", null);
    }

    // delete given column or index
    public int deleteRow(String table_name, String where, String[] whereArgs) {
        return myDataBase.delete(table_name, where, whereArgs);
    }

    // delete table permanently
    public void drop() {
        myDataBase.execSQL("DROP TABLE IF EXISTs tbl_loginUser");
    }


//    public Cursor insert(String table , String[] col , ContentValues values){
//        return myDataBase.insert(table , col , values);
//    }


//    public static final String DB_NAME = "HQ.db";
// //   public static final String DB_
//    public DatabaseHelper( Context context) {
//        super(context, DB_NAME, null, 1);
//
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//    public Cursor getData(){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor c = db.rawQuery("select ID from Feeling",null);
//        return c;
//
//    }

}
