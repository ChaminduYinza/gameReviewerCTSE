package com.mobile.gamereviewer.model;

/**
 * Created by Rushan on 3/27/2018.
 */

public class Game {

    public static final String TABLE_NAME = "game_review";

    public static final String COLUMN_ID = "game_id";
    public static final String COLUMN_NAME = "game_name";
    public static final String COLUMN_DESC = "game_desc";
    public static final String COLUMN_IMG = "game_img";
    public static final String COLUMN_GENRE = "game_genre";
    public static final String COLUMN_ADDED_DATE = "added_date";
    public static final String COLUMN_ADDED_USER = "added_user";

    public static  String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_NAME+"TEXT,"
            +COLUMN_DESC+"TEXT,"
            +COLUMN_IMG+"TEXT,"
            +COLUMN_GENRE+"TEXT,"
            +COLUMN_ADDED_DATE+"DATETIME DEFAULT CURRENT_TIMESTAMP,"
            +COLUMN_ADDED_USER+"TEXT)";


    public Game() {
    }
    public Game(int id, String name, String desc, String img, String genre, String date, String user) {
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getColumnName() {
        return COLUMN_NAME;
    }

    public static String getColumnDesc() {
        return COLUMN_DESC;
    }

    public static String getColumnImg() {
        return COLUMN_IMG;
    }

    public static String getColumnGenre() {
        return COLUMN_GENRE;
    }

    public static String getColumnAddedDate() {
        return COLUMN_ADDED_DATE;
    }

    public static String getColumnAddedUser() {
        return COLUMN_ADDED_USER;
    }


}
