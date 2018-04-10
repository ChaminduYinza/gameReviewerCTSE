package com.mobile.gamereviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Gametechtrack.db";
    public static final String GAME_TABLE = "gameName";
    public static final String GAME_REVIEW_TABLE = "game_review";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GAME_NAME = "name";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_ADDED_DATE = "added_date";
    public static final String COLUMN_ADDED_USER = "added_user";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_REVIEW = "review";


    public DatabaseHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + GAME_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_GAME_NAME + " TEXT," + COLUMN_IMG + " BLOB," + COLUMN_GENRE + " TEXT," + COLUMN_ADDED_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE " + GAME_REVIEW_TABLE + "(" + COLUMN_GAME_NAME + " TEXT, " + COLUMN_REVIEW + " TEXT," + COLUMN_RATE + " INTEGER," + COLUMN_ADDED_USER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    // insertgame data into db
    public boolean insertGame(String name, String genre, byte[] image) {


        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(COLUMN_GAME_NAME, name.toUpperCase().trim());
        values.put(COLUMN_IMG, image);
        values.put(COLUMN_GENRE, genre);


        // insert row
        long id = db.insert(GAME_TABLE, null, values);

        // close db connection
        db.close();

        if (id == -1) {

            return false;
        } else {
            return true;
        }

//        // return newly inserted row id
//        return id;
    }

    // insert gamereview data into db
    public boolean insertGameReview(String name, int rate, String user, String review) {


        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_GAME_NAME, name.toUpperCase().trim());
        values.put(COLUMN_REVIEW, review);
        values.put(COLUMN_RATE, rate);
        values.put(COLUMN_ADDED_USER, user);

        // insert row
        long id = db.insert(GAME_REVIEW_TABLE, null, values);

        // close db connection
        db.close();

        if (id == -1) {

            return false;
        } else {
            return true;
        }


    }

    // this function will check gameName name which is already in db
    public boolean validateGameName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{name.toUpperCase().trim()};
        String sql = "SELECT id,name,img,genre,added_date FROM " + GAME_TABLE + " WHERE " + COLUMN_GAME_NAME + "= ?";
        Cursor cursor = db.rawQuery(sql, params);
        return (cursor.getCount() == 0);
    }

    // this function sort out gameName details by name
    public Cursor getGameByName(String name) {

        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{name.toUpperCase().trim()};
        String sql = "SELECT id,name,img,genre,added_date FROM " + GAME_TABLE + " WHERE " + COLUMN_GAME_NAME + "= ?";
        return db.rawQuery(sql, params);


    }

    // this function sort out gamereviews details by name
    public Cursor getGameReviewByName(String name) {

        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{name.toUpperCase().trim()};
        String sql = "SELECT name,review,rate,added_user FROM " + GAME_REVIEW_TABLE + " WHERE " + COLUMN_GAME_NAME + "= ?";
        return db.rawQuery(sql, params);


    }

    // this function sort out gameName details by genre
    public Cursor getGameByGenre(String genre) {

        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{genre};

        String sql = "SELECT id,name,img,genre,added_date FROM " + GAME_TABLE + " WHERE " + COLUMN_GENRE + "= ?";
        Cursor c = db.rawQuery(sql, params);

        return c;


    }

    // this function will return all gameName data
    public Cursor getAllGame() {

        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT  id,name,img,genre,added_date FROM " + GAME_TABLE + " ORDER BY id DESC";
        Cursor c = db.rawQuery(sql, null);

        return c;


    }
}
