package com.mobile.gamereviewer.model;

/**
 * Created by Yinza on 3/30/2018.
 */

public class GameReview {


    int rate;
    String comments;
    String added_user;

    public GameReview(int rate, String comments, String added_user) {
        this.rate = rate;
        this.comments = comments;
        this.added_user = added_user;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAdded_user() {
        return added_user;
    }

    public void setAdded_user(String added_user) {
        this.added_user = added_user;
    }
}
