package com.example.app2_blood_pressure_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "blood_pressure.db"; // Database Name
    private static final int DATABASE_VERSION = 1; // Database Version
    public static final String TITLE = "blood_pressure.db"; // Database ___
    public static final String VALUE = "value"; // Database ___

    ////////////////////
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_USERNAME = "user_username";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_GENDER = "user_gender";
    private static final String COLUMN_USER_BIRTH_DATE = "user_birth_date";
    // create user table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_USERNAME + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT NOT NULL,"
            + COLUMN_USER_NAME + " TEXT NOT NULL,"
            + COLUMN_USER_GENDER + " TEXT NOT NULL,"
            + COLUMN_USER_BIRTH_DATE + " TEXT NOT NULL"
            + ")";
    // drop user table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    ////////////////////
    // Pressure read table name
    private static final String TABLE_PRESSURE_READ = "pressure_read";
    // Pressure read Table Columns names
    private static final String COLUMN_PRESSURE_READ_ID = "pressure_read_id";
    private static final String COLUMN_PRESSURE_READ_USER = "pressure_read_user_id";
    private static final String COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE = "pressure_read_systolic_pressure";
    private static final String COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE = "pressure_read_diastolic_pressure";
    private static final String COLUMN_PRESSURE_READ_HEART_RATE = "pressure_read_heart_rate";
    private static final String COLUMN_PRESSURE_READ_TIME = "pressure_read_time";
    // create pressure read table sql query
    private String CREATE_PRESSURE_READ_TABLE = "CREATE TABLE " + TABLE_PRESSURE_READ + "("
            + COLUMN_PRESSURE_READ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PRESSURE_READ_USER + " INTEGER NOT NULL,"
            + COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE + " INTEGER NOT NULL,"
            + COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE + " INTEGER NOT NULL,"
            + COLUMN_PRESSURE_READ_HEART_RATE + " INTEGER NOT NULL,"
            + COLUMN_PRESSURE_READ_TIME + " TEXT NOT NULL"
            + ")";
    // drop pressure read table sql query
    private String DROP_PRESSURE_READ_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRESSURE_READ;

    ////////////////////
    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        ContentValues values=new ContentValues();

        values.put(COLUMN_USER_USERNAME, "user_1");
        values.put(COLUMN_USER_PASSWORD, "pass_1");
        values.put(COLUMN_USER_NAME, "name_test_1");
        values.put(COLUMN_USER_GENDER, "Male");
        values.put(COLUMN_USER_BIRTH_DATE, "1-1-2000");
        db.insert(TABLE_USER, TITLE, values);

        values.put(COLUMN_USER_USERNAME, "username_test_2");
        values.put(COLUMN_USER_PASSWORD, "password_test_2");
        values.put(COLUMN_USER_NAME, "name_test_2");
        values.put(COLUMN_USER_GENDER, "male");
        values.put(COLUMN_USER_BIRTH_DATE, "2-2-2000");
        db.insert(TABLE_USER, TITLE, values);

        db.execSQL(CREATE_PRESSURE_READ_TABLE);
        values=new ContentValues();

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 120);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 74);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 62);
        values.put(COLUMN_PRESSURE_READ_TIME, "2021-12-01 11:40:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 113);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 79);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 65);
        values.put(COLUMN_PRESSURE_READ_TIME, "2021-12-28 10:20:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 100);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 70);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 68);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-03 20:05:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 160);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 100);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 60);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-04 15:20:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "2");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 100);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 80);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 65);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-04 20:03:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 110);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 80);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 65);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-04 20:04:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 145);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 80);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 95);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-10 15:20:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 130);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 88);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 90);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-10 20:04:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 156);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 70);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 84);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-13 18:04:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);

        values.put(COLUMN_PRESSURE_READ_USER, "1");
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, 150);
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, 89);
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, 70);
        values.put(COLUMN_PRESSURE_READ_TIME, "2022-01-14 18:04:00");
        db.insert(TABLE_PRESSURE_READ, TITLE, values);
    }

    // Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        android.util.Log.w("", "Upgrading database, which will destroy all old data");
        //Drop User Table and Pressure Reads Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PRESSURE_READ_TABLE);
        // Create new tables again
        onCreate(db);
    }

    // add user
    public void addUser(User user) {
        // Connection with the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, user.getUsername());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_BIRTH_DATE, user.getBirthDate());

        // Inserting Row
        db.insert(TABLE_USER, null, values);

        // Close database
        db.close();
    }

    // add pressure read
    public void addPressureRead(PressureRead pressure_read) {
        // Connection with the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRESSURE_READ_USER, pressure_read.getPressureReadUserID());
        values.put(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE, pressure_read.getSystolicPressure());
        values.put(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE, pressure_read.getDiastolicPressure());
        values.put(COLUMN_PRESSURE_READ_HEART_RATE, pressure_read.getHeartRate());
        values.put(COLUMN_PRESSURE_READ_TIME, pressure_read.getTimeRead());
        // Inserting Row
        db.insert(TABLE_PRESSURE_READ, null, values);

        // Close database
        db.close();
    }
    // Get one user by username
    public User getUserByID(Integer user_id) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_USERNAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_NAME,
                COLUMN_USER_GENDER,
                COLUMN_USER_BIRTH_DATE
        };

        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = { user_id.toString() };

        // Open database just for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();

        User user_info = new User();
        user_info.setID(cursor.getInt(0));
        user_info.setUsername(cursor.getString(1));
        user_info.setPassword(cursor.getString(2));
        user_info.setName(cursor.getString(3));
        user_info.setGender(cursor.getString(4));
        user_info.setBirthDate(cursor.getString(5));

        cursor.close();
        db.close();
        return user_info;
    }

    // Get one user by username
    public User getUserByUsername(String username) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_USERNAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_NAME,
                COLUMN_USER_GENDER,
                COLUMN_USER_BIRTH_DATE
        };

        String selection = COLUMN_USER_USERNAME + " = ?";
        String[] selectionArgs = { username };

        // Open database just for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();

        User user_info = new User();
        user_info.setID(cursor.getInt(0));
        user_info.setUsername(cursor.getString(1));
        user_info.setPassword(cursor.getString(2));
        user_info.setName(cursor.getString(3));
        user_info.setGender(cursor.getString(4));
        user_info.setBirthDate(cursor.getString(5));

        cursor.close();
        db.close();
        return user_info;
    }



    // Get all the users
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_USERNAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_NAME,
                COLUMN_USER_GENDER,
                COLUMN_USER_BIRTH_DATE
        };
        // sorting orders by username
        String sortOrder =
                COLUMN_USER_ID+ " ASC";
        List<User> userList = new ArrayList<User>();

        // Open database just for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))));
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASSWORD)));
                user.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)));
                user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_GENDER)));
                user.setBirthDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_BIRTH_DATE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    // Get all the pressure reads
    public List<PressureRead> getAllPressureReads() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PRESSURE_READ_ID,
                COLUMN_PRESSURE_READ_USER,
                COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE,
                COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE,
                COLUMN_PRESSURE_READ_HEART_RATE,
                COLUMN_PRESSURE_READ_TIME
        };
        // sorting orders by read id
        String sortOrder =
                COLUMN_PRESSURE_READ_ID + " ASC";
        List<PressureRead> PressureReadList = new ArrayList<PressureRead>();

        // Open database just for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // query the pressure read table
        Cursor cursor = db.query(TABLE_PRESSURE_READ, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PressureRead pressureRead = new PressureRead();
                pressureRead.setPressureReadId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_ID))));
                pressureRead.setPressureReadUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_USER))));
                pressureRead.setSystolicPressure(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE))));
                pressureRead.setDiastolicPressure(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE))));
                pressureRead.setHeartRate(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_HEART_RATE))));
                pressureRead.setTimeRead(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_TIME)));

                // Adding pressure read record to list
                PressureReadList.add(pressureRead);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return PressureReadList;
    }

    // Check if username and password of user match
    public boolean checkUserPassword(String username, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_USERNAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_USERNAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // Check if username and password of user match
    public boolean checkUser(String username) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_USERNAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_USERNAME + " = ?" ;
        // selection arguments
        String[] selectionArgs = {username};
        // query user table with conditions
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // Get all the pressure reads
    public List<PressureRead> getAllReadsByUser(Integer id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_PRESSURE_READ_ID,
                COLUMN_PRESSURE_READ_USER,
                COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE,
                COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE,
                COLUMN_PRESSURE_READ_HEART_RATE,
                COLUMN_PRESSURE_READ_TIME
        };

        String selection = COLUMN_PRESSURE_READ_USER + " = ?";
        String[] selectionArgs = { id.toString() };

        // sorting orders by read id
        String sortOrder =
                COLUMN_PRESSURE_READ_ID + " ASC";
        List<PressureRead> PressureReadList = new ArrayList<PressureRead>();

        // Open database just for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // query the pressure read table

        Cursor cursor = db.query(TABLE_PRESSURE_READ,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                PressureRead pressureRead = new PressureRead();
                pressureRead.setPressureReadId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_ID))));
                pressureRead.setPressureReadUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_USER))));
                pressureRead.setSystolicPressure(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE))));
                pressureRead.setDiastolicPressure(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE))));
                pressureRead.setHeartRate(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_HEART_RATE))));
                pressureRead.setTimeRead(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_TIME)));

                // Adding pressure read record to list
                PressureReadList.add(pressureRead);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return PressureReadList;
    }

    // Get NDays before by user
    public List<PressureRead> getNdaysReadsofUser(Integer id,  String daysBefore, String currentDate) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_PRESSURE_READ_ID,
                COLUMN_PRESSURE_READ_USER,
                COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE,
                COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE,
                COLUMN_PRESSURE_READ_HEART_RATE,
                COLUMN_PRESSURE_READ_TIME
        };

        String selection = COLUMN_PRESSURE_READ_USER + " = ?" + " AND " + COLUMN_PRESSURE_READ_TIME + " > ?" + " AND " + COLUMN_PRESSURE_READ_TIME + " <= ?";
        // selection arguments
        String[] selectionArgs = {id.toString(), daysBefore, currentDate};

        // sorting orders by read id
        String sortOrder =
                COLUMN_PRESSURE_READ_ID + " ASC";
        List<PressureRead> PressureReadList = new ArrayList<PressureRead>();

        // Open database just for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // query the pressure read table

        Cursor cursor = db.query(TABLE_PRESSURE_READ,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                PressureRead pressureRead = new PressureRead();
                pressureRead.setPressureReadId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_ID))));
                pressureRead.setPressureReadUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_USER))));
                pressureRead.setSystolicPressure(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_SYSTOLIC_PRESSURE))));
                pressureRead.setDiastolicPressure(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_DIASTOLIC_PRESSURE))));
                pressureRead.setHeartRate(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_HEART_RATE))));
                pressureRead.setTimeRead(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESSURE_READ_TIME)));

                // Adding pressure read record to list
                PressureReadList.add(pressureRead);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return PressureReadList;
    }

}

