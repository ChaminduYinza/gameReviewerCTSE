package com.mobile.gamereviewer.model;

/**
 * Created by Rushan on 3/28/2018.
 */

public class GameModel {

    private int game_id;
    public String game_name;
    public String game_desc;
    public byte[] game_img;
//    public String game_img;
    public String game_genre;
    public String added_date;
    public String added_user;

    public GameModel(int game_id, String game_name, String game_desc, byte[] game_img, String game_genre, String added_date, String added_user) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.game_desc = game_desc;
        this.game_img = game_img;
        this.game_genre = game_genre;
        this.added_date = added_date;
        this.added_user = added_user;
    }

    public GameModel(String game_name, byte[] game_img, String game_genre) {
        this.game_name = game_name;
        this.game_img = game_img;
        this.game_genre = game_genre;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_desc() {
        return game_desc;
    }

    public void setGame_desc(String game_desc) {
        this.game_desc = game_desc;
    }

    public byte[] getGame_img() {
        return game_img;
    }

    public void setGame_img(byte[] game_img) {
        this.game_img = game_img;
    }

    public String getGame_genre() {
        return game_genre;
    }

    public void setGame_genre(String game_genre) {
        this.game_genre = game_genre;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }

    public String getAdded_user() {
        return added_user;
    }

    public void setAdded_user(String added_user) {
        this.added_user = added_user;
    }
}
