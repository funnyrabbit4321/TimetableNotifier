package com.group4.joseph.timetablenotifier;


import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "timetable.db";
    public static final String TABLE_MONDAY = "monday";
    public static final String TABLE_TUESDAY = "tuesday";
    public static final String TABLE_WEDNESDAY = "wednesday";
    public static final String TABLE_THURSDAY = "thursday";
    public static final String TABLE_FRIDAY = "friday";



    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_MODULE_LOCATION = "modulelocation";
    public static final String COLUMN_SPINNER_POS = "spinner_pos";
    public static final String COLUMN_SPINNER_POS2 = "spinner_pos_2";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryMonday = "create table monday (time integer primary key , " +
                "modulelocation text, spinner_pos int, spinner_pos_2 int);";
        db.execSQL(queryMonday);

        String queryTuesday = "create table tuesday (time integer primary key , " +
                "modulelocation text, spinner_pos int, spinner_pos_2 int);";
        db.execSQL(queryTuesday);

        String queryWednesday = "create table wednesday (time integer primary key , " +
                "modulelocation text, spinner_pos int, spinner_pos_2 int);";
        db.execSQL(queryWednesday);

        String queryThursday = "create table thursday (time integer primary key , " +
                "modulelocation text, spinner_pos int, spinner_pos_2 int);";
        db.execSQL(queryThursday);

        String queryFriday = "create table friday (time integer primary key , " +
                "modulelocation text, spinner_pos int, spinner_pos_2 int);";
        db.execSQL(queryFriday);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUESDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEDNESDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THURSDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIDAY);

        onCreate(db);
    }


    //Add a new row to the database
    public long addModuleMonday(String module_location, int time, int spinner_position, int spinner2_position){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_MODULE_LOCATION, module_location);
        values.put(COLUMN_SPINNER_POS, spinner_position);
        values.put(COLUMN_SPINNER_POS2, spinner2_position);

        SQLiteDatabase db = getWritableDatabase();
        long r = db.insert(TABLE_MONDAY, null, values);
        db.close();
        return r;
    }

    public long addModuleTuesday(String module_location, int time, int spinner_position, int spinner2_position){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_MODULE_LOCATION, module_location);
        values.put(COLUMN_SPINNER_POS, spinner_position);
        values.put(COLUMN_SPINNER_POS2, spinner2_position);

        SQLiteDatabase db = getWritableDatabase();
        long r = db.insert(TABLE_TUESDAY, null, values);
        db.close();
        return r;
    }

    public long addModuleWednesday(String module_location, int time, int spinner_position, int spinner2_position){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_MODULE_LOCATION, module_location);
        values.put(COLUMN_SPINNER_POS, spinner_position);
        values.put(COLUMN_SPINNER_POS2, spinner2_position);

        SQLiteDatabase db = getWritableDatabase();
        long r = db.insert(TABLE_WEDNESDAY, null, values);
        db.close();
        return r;
    }

    public long addModuleThursday(String module_location, int time, int spinner_position, int spinner2_position){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_MODULE_LOCATION, module_location);
        values.put(COLUMN_SPINNER_POS, spinner_position);
        values.put(COLUMN_SPINNER_POS2, spinner2_position);

        SQLiteDatabase db = getWritableDatabase();
        long r = db.insert(TABLE_THURSDAY, null, values);
        db.close();
        return r;
    }

    public long addModuleFriday(String module_location, int time, int spinner_position, int spinner2_position){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_MODULE_LOCATION, module_location);
        values.put(COLUMN_SPINNER_POS, spinner_position);
        values.put(COLUMN_SPINNER_POS2, spinner2_position);

        SQLiteDatabase db = getWritableDatabase();
        long r = db.insert(TABLE_FRIDAY, null, values);
        db.close();
        return r;
    }

    public void deleteModuleMonday(){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_MONDAY + " WHERE " + COLUMN_9 + "=\"" + column9 + "\";");
        db.delete(TABLE_MONDAY,null,null);
        db.close();
    }

    public void deleteModuleTuesday(){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_MONDAY + " WHERE " + COLUMN_9 + "=\"" + column9 + "\";");
        db.delete(TABLE_TUESDAY,null,null);
        db.close();
    }

    public void deleteModuleWednesday(){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_MONDAY + " WHERE " + COLUMN_9 + "=\"" + column9 + "\";");
        db.delete(TABLE_WEDNESDAY,null,null);
        db.close();
    }

    public void deleteModuleThursday(){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_MONDAY + " WHERE " + COLUMN_9 + "=\"" + column9 + "\";");
        db.delete(TABLE_THURSDAY,null,null);
        db.close();
    }

    public void deleteModuleFriday(){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_MONDAY + " WHERE " + COLUMN_9 + "=\"" + column9 + "\";");
        db.delete(TABLE_FRIDAY,null,null);
        db.close();
    }

    public Cursor readAllMonday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_MONDAY, null, null, null, null, null, null);
        return c;
    }

    public Cursor readAllTuesday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_TUESDAY, null, null, null, null, null, null);
        return c;
    }

    public Cursor readAllWednesday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_WEDNESDAY, null, null, null, null, null, null);
        return c;
    }

    public Cursor readAllThursday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_THURSDAY, null, null, null, null, null, null);
        return c;
    }

    public Cursor readAllFriday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_FRIDAY, null, null, null, null, null, null);
        return c;
    }


    /*public void deleteRow(String module) {
        //SQLiteDatabase db = getWritableDatabase();
        //return db.delete(TABLE_MONDAY, COLUMN_MODULE_LOCATION + "=" + time, null) > 0;
        //db.delete(TABLE_MONDAY, COLUMN_TIME + "=" + time + " and " + COLUMN_MODULE_LOCATION + "=" + module, null);


        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_MONDAY, "modulelocation = ?", new String[]{module});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }*/

}
